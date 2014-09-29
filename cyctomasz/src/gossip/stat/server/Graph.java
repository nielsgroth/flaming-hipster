/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;




import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.StaxDriver;

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
    public void addNode(Node n, long start, long end) {
    	if (n.getJoined()<end && n.getLeft()>start) {
        	nodes.add(new Node(n.getName(), Math.max(start, n.getJoined()),Math.min(end, n.getLeft())));
    	}
    }
    public void addEdge(Edge e, long start, long end){
    	this.edges.add(e.getSubEdge(start, end));
    }
    public List<Node> getNodes(){
    	return this.nodes;
    }
    
    public List<Edge> getEdges(){
    	return this.edges;
    }
     
    public int getPath(Node start, Node target) {
    	String startName = start.getName().split(":")[0];
    	String targetName = target.getName().split(":")[0];
    	return this.shortestPaths.get(startName+targetName);
    }
    public int getPath(Edge edge) {
    	String path =edge.getSource().substring(0, edge.getSource().indexOf(':'));
    	path += "_" + edge.getTarget().substring(0, edge.getTarget().indexOf(':'));
    	if (this.shortestPaths.get(path)==null) return 40000;
    	return this.shortestPaths.get(path);
    }
    public int longestShortestPath() {
    	int result=0;
    	Iterator<Integer> shoPaIter =  this.shortestPaths.values().iterator();
    	while(shoPaIter.hasNext()) {
    		result = Math.max(result, shoPaIter.next());
    	}
    	return result;
    }
    public int getNumberOfPaths(int length) {
    	int result = 0;
    	Iterator<Integer> shoPaIter =  this.shortestPaths.values().iterator();
    	while(shoPaIter.hasNext()) {
    		if(shoPaIter.next().intValue()==length) {
    			result++;
    		}
    	}
    	return result;    			
    }
    /**
     * 
     * @return starting time of the experiment in seconds based on first node
     */
    public long getStartingTime(){
    	long result=Long.MAX_VALUE;
    	for(int i=0; i<this.nodes.size();i++) {
    		result = Math.min(result,this.nodes.get(i).getJoined());
    	}
    	return result;
    }
    /**
     * 
     * @return ending time of the experiment in seconds based on last edge
     */
    public long getEndingTime(){
    	long result=0;
    	for(int i=0; i<this.edges.size();i++) {
    		result = Math.max(result,this.edges.get(i).getLeft());
    	}
    	return result;
    }
    public void normalize() {
    	long startingTime = this.getStartingTime();
    	for (int i=0; i<this.nodes.size();i++){
    		this.nodes.get(i).setJoined(this.nodes.get(i).getJoined()-startingTime);
    		this.nodes.get(i).setLeft(this.nodes.get(i).getLeft()-startingTime);
    	}
    	for (int i=0; i<this.edges.size();i++){
    		this.edges.get(i).normalize(startingTime);
    	}
    }
    public Graph getSubGraph(long start, long end){
    	Graph subGraph= new Graph();
    	for(int i=0;i<this.nodes.size();i++){
    		Node n = this.nodes.get(i);
    		subGraph.addNode(n, start, end);
    	}
    	for(int i=0;i<this.edges.size();i++){
    		Edge e = this.edges.get(i);
    		subGraph.addEdge(e, start, end);
    	}
    	return subGraph;
    } 
    public void caclulateShortestPaths(){
    	
    	Integer[][] paths = new Integer[this.nodes.size()][this.nodes.size()];
    	for (int i=0;i<this.nodes.size();i++){
    		for (int j=0;j<this.nodes.size();j++){
    			if (this.nodes.get(i).equals(this.nodes.get(j))){
    				paths[i][j]=0;
    			} else if (this.edges.contains(new Edge(this.nodes.get(j).getName(), this.nodes.get(i).getName()))) {
    				paths[i][j]=1;
    			} else paths[i][j]=this.nodes.size()+2;
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
    			this.shortestPaths.put(this.nodes.get(i).getName() + "_" +
    					this.nodes.get(j).getName(), paths[i][j]);
    		}
    	}
    }
    public void readAlternateResults(File fileName) {
    	try {
    		BufferedReader inXML = new BufferedReader(new FileReader(fileName));
    		XStream xstream = new XStream(new StaxDriver());
    		ObjectInputStream in = xstream.createObjectInputStream(inXML);
    		xstream.processAnnotations(Node.class);
    		xstream.processAnnotations(Edge.class);
    		xstream.processAnnotations(Spell.class);
    		xstream.processAnnotations(Graph.class);
    			    		
    		boolean EOF = false;
    		while(!EOF) {
    			try {
    				Object currentObject = in.readObject();
    				try{
    					Node currentNode = (Node) currentObject;
		    			this.nodes.add(currentNode);
    				} catch(ClassCastException notaNode){
    					try {
    						Edge currentEdge = (Edge) currentObject;
    						this.edges.add(currentEdge);
    					} catch(ClassCastException notanEdge){
    							this.nodes.addAll((LinkedList<Node>) currentObject);
    							currentObject = in.readObject();
    							this.edges.addAll((LinkedList<Edge>) currentObject);
    							for(int i=0;i<this.nodes.size();i++){
    								if (this.nodes.get(i).getName()==null) {
    									this.nodes.remove(i);
    									
    								}
    							}
    							int j=0;
    							for(int i=0;i+j<this.edges.size();i++){
    								if (this.edges.get(i).getId()==null || this.edges.get(i).getId()=="null_null"){
    									this.edges.remove(i);
    									j++;
    								}
    							}
    					}
    					
    				} 
    			} catch (ClassNotFoundException e) {
    				e.printStackTrace();
    			} catch(EOFException e) {
    	    		EOF = true;
    	    	}
    		}    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    }
    public String getAnalytics(long intervalStart, long intervalEnd, Graph graph , Graph topology){
    	long start = graph.getStartingTime() + intervalStart;
    	long end = graph.getStartingTime() + intervalEnd;
    	Graph subGraph = graph.getSubGraph(start, end);
    	Graph subTopology = topology.getSubGraph(start, end);
    	subTopology.caclulateShortestPaths();
    	Double[] results = new Double[subTopology.longestShortestPath()+1];
    	for(int i=0;i<results.length;i++) results[i] = 0.0;
    	for(int i = 0; i < subGraph.getEdges().size(); i++){
    		int hops = subTopology.getPath(subGraph.getEdges().get(i));
    		if(hops!=40000 && !subGraph.getEdges().get(i).getSource().equals(subGraph.getEdges().get(i).getTarget()))
    		results[hops] += (double)(subGraph.getEdges().get(i).totalTimeExisted());   		
    	}
    	for (int i=0;i<results.length;i++){
    		results[i]=results[i]/(((double)subTopology.getNumberOfPaths(i))*((double)(intervalEnd-intervalStart)));
    	}
    	String result="";
    	for (int i=0;i<results.length;i++) {
    		result += i + ";";
    	}
    	result += "\n";
    	for (int i=0;i<results.length;i++) {
    		result += results[i] + ";";
    	}
    	return result;
    }
}
