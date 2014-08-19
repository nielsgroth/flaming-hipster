/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.io.Serializable;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
    
    private Deque<Spell> spells = new LinkedList<Spell>();
       

    public long getJoined() {
        return this.spells.getLast().getJoined();
    }

    public long getLeft() {
        return this.spells.getLast().getLeft();
    }
    public long getFirstJoined() {
    	return this.spells.getFirst().getJoined();
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

    public Edge(String target, String source) {
        this.target = target;
        this.source = source;
        this.id = this.source+"_"+this.target;
    }
    public Edge getSubEdge(long start, long end) {
    	Edge subEdge = new Edge(this.getTarget(), this.getSource());
    	Iterator<Spell> spellit = this.spells.iterator();
    	while(spellit.hasNext()) {
    		Spell currentSpell=spellit.next();
    		if(currentSpell.getJoined()<end && currentSpell.getLeft()>start){
    			subEdge.spells.add(new Spell(Math.max(currentSpell.getJoined(), start),
    					Math.min(currentSpell.getLeft(), end)));
    		}
    	}
    	return subEdge;
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
            return this.target.equals(((Edge) obj).target) && this.source.equals(((Edge) obj).source);
        }
    }
    
    public long totalTimeExisted(){
    	long result = 0;
    	Iterator<Spell> spellit = this.spells.iterator();
    	while(spellit.hasNext()){
    		Spell currentSpell=spellit.next();
    		result += currentSpell.getLeft() - currentSpell.getJoined();
    	}
    	return result;
    }
    public void activate(long updatetime) {
        this.spells.add(new Spell(updatetime));
    	//this.joined = System.nanoTime() / 1000;
        this.status = Edge.ACTIVE;
    }

    public void deactivate(long updatetime) {
        this.spells.getLast().setLeft(updatetime);
    	//this.left = Long.valueOf(System.nanoTime() / 1000);
        this.status = Edge.GONE;
    }

    public boolean isGone() {
        return this.status == Edge.GONE;
    }
    
    @Override
    public String toString() {
        return this.target + " Joined: " + this.getJoined() + " Left: " + this.getLeft();
    }
}
