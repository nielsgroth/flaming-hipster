/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("node")
public class Node implements Serializable {
    //Timestamps

    public static final char NEW = 1;
    public static final char NO_LINKS = 2;
    public static final char CONNECTED = 3;
    public static final char LEFT = 4;
    @XStreamOmitField
    private List<Edge> edges = new Vector<Edge>();
    
    @XStreamOmitField
    private long firstSeen;
    
    @XStreamAsAttribute
    @XStreamAlias(value="start")
    private long joined;
    
    @XStreamAsAttribute
    @XStreamAlias(value="end")
    private Long left = null; //TODO warum object statt simple type
    
    @XStreamOmitField
    private long lastUpdate;
    
    @XStreamAsAttribute
    @XStreamAlias(value="id")
    private String name;
    
    
    @XStreamAsAttribute
    @XStreamAlias(value = "label")
    private String label;
    
    @XStreamOmitField
    public char status;

    public List<Edge> getEdges() {
        return edges;
    }

    public long getJoined() {
        return joined;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public long getLeft() {
        return left.longValue();
    }

    public String getName() {
        return name;
    }

    public char getStatus() {
        return status;
    }

    public Node() {
        this.joined = System.currentTimeMillis() / 1000;
        this.status = Node.NEW;
    }

    public Node(String name) {
        this();
        this.name = name;
        this.label = name;

    }

    public void leave() {
        this.left = Long.valueOf(System.currentTimeMillis() / 1000);
        this.status = Node.LEFT;
    }

    public void validate() {
        long currentTimestamp = System.currentTimeMillis() / 1000;
        if (this.status != Node.LEFT && (currentTimestamp - this.lastUpdate) > 40) {
            System.out.println("Invalidating node: " + this.name);
            this.leave();
        }
    }

    public void updateEdges(List<String> strings) {
        List<Edge> edges = new Vector<Edge>();
        for (String s : strings) {
            edges.add(new Edge(s, this.name));
        }
        this.left=null;
        this.status = Node.CONNECTED;
        this.lastUpdate = System.currentTimeMillis() / 1000;
        for (Edge e : edges) {
            if (!this.edges.contains(e)) { // new edge
                e.activate();
                this.edges.add(e);

            } else { // edge already known, will be activated if necessary
            	if(this.edges.get(this.edges.lastIndexOf(e)).isGone())
            	this.edges.get(this.edges.lastIndexOf(e)).activate();
            }
        }

        for (Edge e : this.edges) {
            if (!edges.contains(e) && !e.isGone()) {
                //  System.out.println("Deactivating "+e);
                e.deactivate();
            }
        }

    }

    @Override
    public String toString() {
        String ret = "Node " + this.name + "\n\n";
        for (Edge e : this.edges) {
            ret += e.toString() + "\n";
        }
        return ret;
    }
}
