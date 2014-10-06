package gossip.stat.server;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
*
* @author Tomasz
*/
@XStreamAlias("spell")
public class Spell {
    @XStreamAsAttribute
    @XStreamAlias(value="start")
    private Long joined;
    
    @XStreamAsAttribute
    @XStreamAlias(value="end")
    private Long left = null;
    
    public Long getJoined() {
        return joined;
    }

    public Long getLeft() {
        return left;
    }
    public Spell(long joined) {
    	this.joined=joined;
    }
    public Spell(long joined, long left) {
    	this.joined=joined;
    	this.left=left;
    }
    public void setJoined(long timestamp) {
    	this.joined=timestamp;
    }
    
    public void setLeft(long timestamp) {
    	this.left=timestamp;
    }
}
