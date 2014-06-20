/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 *
 * @author Misio, Tomasz
 */
@XStreamAlias("graph")
public class Graph {
    @XStreamAsAttribute
    private String mode="dynamic";
    private List<Node> nodes = new LinkedList<Node>();
    private List<Edge> edges = new LinkedList<Edge>();
    @XStreamOmitField
    private Map<String,Integer> shortestPaths = new HashMap<String, Integer>();
    
    public void addNode(Node n){
        nodes.add(n);
        edges.addAll(n.getEdges());
    }
    
    public List<Node> getNodes(){
    	return this.nodes;
    }
    
    public List<Edge> getEdges(){
    	return this.edges;
    }
    
    public void caclulateShortestPaths(){
    	
    	Integer[][] paths = new Integer[this.nodes.size()][this.nodes.size()];
    	for (int i=0;i<this.nodes.size();i++){
    		for (int j=0;j<this.nodes.size();j++){
    			if (this.nodes.get(i).equals(this.nodes.get(j))){
    				paths[i][j]=0;
    			} else if (this.nodes.get(i).getEdges().contains(this.nodes.get(j))) {
    				paths[i][j]=1;
    				
    			} else paths[i][j]=Integer.MAX_VALUE;
    		}
    	}
    	for (int k=0; k<this.nodes.size();k++){
    		for(int i=0;i<this.nodes.size();i++){
    			for(int j=0;j<this.nodes.size();j++){
    				paths[i][j]= Math.min(paths[i][j],paths[i][k] + paths[k][j]);
    			}
    		}
    	}
    	for(int i=0;i<this.nodes.size();i++){
    		for (int j=0;j<this.nodes.size();j++){
    			this.shortestPaths.put(this.nodes.get(i).getName() +
    					this.nodes.get(j).getName(), paths[i][j]);
    		}
    	}
    }
    
    public int getPath(Node start, Node target) {
    	String startName = start.getName().split(":")[0];
    	String targetName = target.getName().split(":")[0];
    	return this.shortestPaths.get(startName+targetName);
    }
    /**
     * 
     * @return starting time of the experiment in seconds based on first edge
     */
    private long getStartingTime(){
    	long result=Long.MAX_VALUE;
    	for(int i=0; i<this.edges.size();i++) {
    		result = Math.min(result,this.edges.get(i).getFirstJoined());
    	}
    	return result;
    }
    /**
     * 
     * @return ending time of the experiment in seconds based on last edge
     */
    private long getEndingTime(){
    	long result=0;
    	for(int i=0; i<this.edges.size();i++) {
    		result = Math.max(result,this.edges.get(i).getLeft());
    	}
    	return result;
    }
    
}
