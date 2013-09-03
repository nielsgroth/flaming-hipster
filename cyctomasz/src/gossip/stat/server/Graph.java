/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.util.LinkedList;
import java.util.List;
import com.thoughtworks.xstream.annotations.*;

/**
 *
 * @author Misio
 */
@XStreamAlias("graph")
public class Graph {
    @XStreamAsAttribute
    private String mode="dynamic";
    private List<Node> nodes = new LinkedList<Node>();
    private List<Edge> edges = new LinkedList<Edge>();   
    
    public void addNode(Node n){
        nodes.add(n);
        edges.addAll(n.getEdges());
    }
}
