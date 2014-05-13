package gossip.stat.server;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
*
* @author Tomasz
*/
@XStreamAlias("spell")
public class EdgeSpell {
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
    public EdgeSpell(long joined) {
    	this.joined=joined;
    }
    public EdgeSpell(long joined, long left) {
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
