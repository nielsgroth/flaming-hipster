package gossip.stat.client;

import static gossip.stat.client.CyclonPeer.c;
import static gossip.stat.client.CyclonPeer.l;
import static gossip.stat.client.CyclonPeer.shufflePayloadSize;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Maciek Lukas
 *
 */
public class NeighborCache {

    List<Neighbor> neighbors = new ArrayList<Neighbor>(c);
    Neighbor self, currentTarget;
    private Random rand = new Random();

    List<String> buildStatList() {
        List<String> list = new LinkedList<String>();
        for (Neighbor n : neighbors) {
            list.add(n.getId());
        }
        return list;
    }

    /**
     * @param shuffleId
     * @return subset List of known neighbors to send as a shuffle request
     */
    List<Neighbor> buildRequestList(int shuffleId) {
        /**
         * Anfrageliste für eine Shuffleinitiierung erzeugen
         */
        incrementAllAges();
        currentTarget = getOldestNeighbor();
        currentTarget.tag();

        int leff = Math.min(l, neighbors.size());
        List<Neighbor> subset = new ArrayList<Neighbor>(l);
        subset.add(self);

        //Nachbarn durchschütteln
        Collections.shuffle(neighbors);

        int i = 0;
        //TODO: endlos
        //Hier war eine Endlosschleife
        //if (subset.size() >= leff && i >= neighbors.size()) {
        while (subset.size() < leff && i < neighbors.size()) { // TODO i < neighbor.size überflüssig
            Neighbor nb = neighbors.get(i++);
            if (nb != currentTarget) {
                nb.tag();
                subset.add(nb);
            }

        }
        // }

        return subset;
    }

    private Neighbor getOldestNeighbor() {
        Neighbor oldest = neighbors.get(0);
        for (Neighbor n : neighbors) {
            if (n.getAge() > oldest.getAge()) {
                oldest = n;
            }
        }
        return oldest;
    }

    private Neighbor findNeighbor(Neighbor nSearch) {
        /**
         * Knoten anhand von IP und Port finden, Alter ignorieren
         */
        for (Neighbor n : neighbors) {
            if (n.samePeer(nSearch)) {
                return n;
            }
        }
        return null;
    }

    /** 
     * Removes the already known peers from parameter in
     * @param in
     */
    // TODO Überlegungen zum Alter siehe Notes
    private void removeKnownFrom(List<Neighbor> in) {
        /* Löscht die in diesem Cache enthaltenen Duplikate IN DER EINGABE */
        Set<Neighbor.CompareNeighbor> uniqs = new HashSet<Neighbor.CompareNeighbor>();
        Set<Neighbor.CompareNeighbor> nodups = new HashSet<Neighbor.CompareNeighbor>();
        for (Neighbor n : neighbors) {
            uniqs.add(new Neighbor.CompareNeighbor(n));
        }
        for (Neighbor n : in) {
            nodups.add(new Neighbor.CompareNeighbor(n));
        }
        nodups.removeAll(uniqs);

        in.clear();
        for (Neighbor.CompareNeighbor n : nodups) {
            in.add(n.n);
        }
    }

    private static void removeDuplicates(List<Neighbor> in) {
        Set<Neighbor.CompareNeighbor> comps = new HashSet<Neighbor.CompareNeighbor>(); //durch das Hashset werden duplikate entfernt
        for (Neighbor n : in) {
            Neighbor.CompareNeighbor cn = new Neighbor.CompareNeighbor(n); //TODO überflüssig
            comps.add(new Neighbor.CompareNeighbor(n));
        }

        in.clear();

        for (Neighbor.CompareNeighbor cn : comps) {
            in.add(cn.n);
        }
    }

    /**
     * Processes the given shuffle neighbor list without sending a response.
     * @param request
     */
    void processResponseList(List<Neighbor> request) {
        /** Antwort auf eine früher von uns abgeschickte Anfrage erhalten.
         * Wir pflegen die Updates ein und schicken selbst keine Antwort.
         */
        //Den Anfrageknoten entfernen und Alter resetten
        findNeighbor(request.remove(0)).resetAge();

        //Bereits bekannte Knoten und Duplikate aus dem Request entfernen
        removeKnownFrom(request);
        removeDuplicates(request);

        //Freie Plätze auffüllen:
        while (!isFull() && !request.isEmpty()) {
            //Das erhaltene Alter direkt übernehmen!
            neighbors.add(request.remove(0));
        }

        //Platz für die restlichen Antworten machen, Tags entfernen:
        int rest = request.size();
        for (Neighbor n : neighbors) {
            if (n.isTagged()) {
                if (rest > 0) {
                    neighbors.remove(n);
                    rest--;
                } else {
                    n.untag();
                }
            }
        }

        //Die restlichen Antworten hinzufügen:
        for (Neighbor n : request) {
            neighbors.add(n);
            request.remove(n);
        }

    }

    List<Neighbor> processRequestList(List<Neighbor> request) {
        //Bereits bekannte Knoten aus dem Request entfernen
        removeKnownFrom(request);
        removeDuplicates(request);

        //Eine Antwort erstellen
        List<Neighbor> response = new ArrayList<Neighbor>(l);
        response.add(self);

        //Nachbarn gut mischen, um zufällige Antwortknoten zu bekommen:
        Collections.shuffle(neighbors);

        for (Neighbor n : neighbors) {
            if (response.size() < l) {
                response.add(n);
            } else {
                break;
            }
        }

        //Solange wir noch Platz in unserer Liste haben, Knoten einfach einfügen
        while (!isFull() && !request.isEmpty()) {
            neighbors.add(request.remove(0));
        }

        /* Jetzt ist unsere Liste voll. Einen der versendeten Knoten löschen;
         * den erhaltenen einfügen.
         */
        int i = 1;
        while (!request.isEmpty()) {
            neighbors.remove(response.get(i));
            neighbors.add(request.remove(0));
        }

        return response;
    }

    public boolean isFull() {
        return neighbors.size() >= c;
    }

    /**
     * @param in list of neighbors
     * @param id the id of the shuffle list
     * @return id, List of neighbors as array of bytes
     */
    static byte[] neighborListToShuffleBytes(List<Neighbor> in, int id) {
        ByteBuffer buf = ByteBuffer.allocate(shufflePayloadSize).putInt(id);
        for (Neighbor n : in) {
            for (byte b : n.toByteArray()) {
                buf.put(b);
            }
        }

        return buf.array();

    }

    static List<Neighbor> neighborListFromShuffleBytes(byte[] in) {
        ByteBuffer buf = ByteBuffer.wrap(in);
        buf.getInt(); //verschieben
        List<Neighbor> subset = new ArrayList<Neighbor>();
        while (buf.hasRemaining()) {
            byte[] neighborBuf = new byte[Neighbor.recordBytes];
            for (int i = 0; i < neighborBuf.length; i++) {
                neighborBuf[i] = buf.get();
            }
            Neighbor n = Neighbor.fromByteArray(neighborBuf);
            if (n != null) {
                subset.add(n);
            }
        }

        return subset;
    }

    static int shuffleIdFromShuffleBytes(byte[] in) {
        return ByteBuffer.wrap(in).getInt();
    }

    void incrementAllAges() {
        for (Neighbor n : neighbors) {
            n.increaseAge();
        }
    }

    public String toString() {
        return neighbors.toString();
    }
}
