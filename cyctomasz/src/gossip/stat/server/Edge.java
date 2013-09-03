/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.io.Serializable;
import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("edge")
public class Edge implements Serializable {
    
    @XStreamOmitField
    private char status = 0; 
    
    @XStreamAsAttribute
    private String target;
    
    @XStreamAsAttribute
    private String source;
    
    @XStreamAsAttribute
    private String id;
    
    public static final char ACTIVE = 0;
    public static final char GONE = 1;
    
    @XStreamAsAttribute
    @XStreamAlias(value="start")
    private long joined;
    
    @XStreamAsAttribute
    @XStreamAlias(value="end")
    private Long left = null;

    public long getJoined() {
        return joined;
    }

    public long getLeft() {
        return left;
    }

    public String getSource() {
        return source;
    }

    public char getStatus() {
        return status;
    }

    public String getTarget() {
        return target;
    }

    public String getId() {
        return id;
    }

    public Edge() {
    }

    public Edge(String name, String source) {
        this.target = name;
        this.source = source;
        this.id = this.source+"_"+this.target;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj instanceof String) {
//            System.out.println("Comparing this: "+this.name+" with "+obj);
//            System.out.println("STR Returning: "+(this.name == obj));
            return this.target == obj;
        } else {
//            System.out.println("Comparing this: "+this.name+" with "+((Edge) obj).name);
//            System.out.println("OBJ Returning: "+(this.name.equals(((Edge) obj).name)));
            return this.target.equals(((Edge) obj).target);
        }
    }
    

    public void activate() {
        this.joined = System.currentTimeMillis() / 1000;
        this.status = Edge.ACTIVE;
    }

    public void deactivate() {
        this.left = Long.valueOf(System.currentTimeMillis() / 1000);
        this.status = Edge.GONE;
    }

    public boolean isGone() {
        return this.status == Edge.GONE;
    }

    @Override
    public String toString() {
        return this.target + " Joined: " + this.joined + " Left: " + this.left;
    }
}
