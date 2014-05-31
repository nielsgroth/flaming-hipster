package gossip.stat.client;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Comparator;

/**
 * @author Tomasz
 *
 */
public class Neighbor {

    private final InetAddress ip;
    private final short port;
    private int age;
    private boolean tagged;
    final static NeighborAgeComparator ageComp;

    static {
        ageComp = new Neighbor.NeighborAgeComparator();
    }
    public final static int ipLength = 4;
    public final static int portLength = 2;
    public final static int ageLength = 4;
    public final static int recordBytes = ipLength + portLength + ageLength;

    public Neighbor(InetAddress ip, short port, int age) {
        this.ip = ip;
        this.port = port;
        this.age = age;
    }

    public Neighbor(InetAddress ip, int port) {
        this(ip, (short) port, 0);
    }

    /**
     * Vergleiche ohne Beachtung des Alters auf gleiche IP und Port.
     */
    public boolean samePeer(Neighbor n) {
        return (n.ip.equals(this.ip) && n.port == this.port);
    }

    //TODO: Konstruktor von String-IPs
    public static Neighbor fromByteArray(byte[] in) {
        try {
            if (in.length != recordBytes) {
                System.err.println("Falsche Länge in fromByteArray!");
                return null;
            }
            ByteBuffer bytes = ByteBuffer.wrap(in);
            bytes.order(ByteOrder.BIG_ENDIAN);
            byte[] ipBytes = new byte[ipLength];
            bytes.get(ipBytes);
            InetAddress ip = InetAddress.getByAddress(ipBytes);
            if (ip.equals(InetAddress.getByName("0.0.0.0"))) {
                return null;
            }

            return new Neighbor(ip, bytes.getShort(), bytes.getInt());
        } catch (UnknownHostException e) {
            // Kann eigentlich nie passieren - wir übergeben immer genau 4 Byte.
            e.printStackTrace();
            return null;
        }

    }
    
    public String getId(){
        return this.getIp().getHostAddress()+":"+String.valueOf(this.getPort());
    }

    public InetAddress getIp() {
        return ip;
    }

    public short getPort() {
        return port;
    }

    public int getAge() {
        return age;
    }
    
    

    public InetSocketAddress getInetSocketAddress() {
        return new InetSocketAddress(ip, port);
    }

    /**
     * Increase Age
     * @return the current age, increased by one
     */
    public int increaseAge() {
        return ++age;
    }

    public void resetAge() {
        age = 0;
    }

    public void tag() {
        tagged = true;
    }

    public void untag() {
        tagged = false;
    }

    public boolean isTagged() {
        return tagged;
    }

    /**
     * @return IP address, port and age information of a peer as an array of bytes 
     */
    public byte[] toByteArray() {
        ByteBuffer bytes = ByteBuffer.allocate(recordBytes);
        bytes.order(ByteOrder.BIG_ENDIAN);
        bytes.put(ip.getAddress());
        bytes.putShort(port);
        bytes.putInt(age);
        return bytes.array();
    }

    private static class NeighborAgeComparator implements Comparator<Neighbor> {

        @Override
        public int compare(Neighbor n1, Neighbor n2) {
            if (n1.getAge() < n2.getAge()) {
                return -1;
            } else if (n1.getAge() == n2.getAge()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static class CompareNeighbor {

        Neighbor n;

        CompareNeighbor(Neighbor n) {
            this.n = n;
        }

        public boolean equals(Object other) {
            if (!(other instanceof CompareNeighbor)) {
                return false;
            }
            return n.samePeer(((CompareNeighbor) other).n);
        }

        public int hashCode() {
            return n.ip.hashCode() + n.port;
        }
    }

    public String toString() {
        return "[" + ip + ":" + port + "(" + age + ")]";
    }
}
