/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Maciek, Tomasz
 */
@WebService
public class StatServer {

    public Map<String, Node> list = new ConcurrentHashMap<String, Node>(1000);
    public Map<String, Node> topology = new ConcurrentHashMap<String, Node>(1000);

    public Node getNode(String key) {
        if (this.list.containsKey(key)) {
            return this.list.get(key);
        } else {
            Node n = new Node(key);
            this.list.put(key, n);
            return n;
        }
    }
    public Node getTopoNode(String key) {
    	if (this.topology.containsKey(key)) {
    		return this.topology.get(key);
    	} else{ 
    		Node n = new Node(key);
    		this.topology.put(key, n);
    		return n;
    	}
    	
    }

    /**
     * @param args the command line arguments
     */
    @WebMethod
    public void sendTopology(@WebParam(name = "id") String key, @WebParam(name = "edgeList") List<String> edges){
    	this.getTopoNode(key).updateEdges(edges);
    }
    @WebMethod
    public void sendList(@WebParam(name = "id") String key, @WebParam(name = "edgeList") List<String> edges) {
        System.out.println(key);
        this.getNode(key).updateEdges(edges);
    }
    @WebMethod
    public void leave(@WebParam(name = "id")String key) {
    	//System.out.println("Node " + key + "has left the building!");
    	this.getNode(key).leave();
    }
    /**
     * Delete statistics data from Server to start a new experiment
     */
    @WebMethod
    public void cleanup(){
    	this.list.clear();
    	this.topology.clear();
    }
    @WebMethod
    public String getXML() {
        Map<String, Node> currentList = new HashMap<String, Node>(list);
        Set<String> nodeNames = currentList.keySet();
        
        XStream xs = new XStream();        
        xs.processAnnotations(Node.class);
        xs.processAnnotations(Edge.class);
        xs.processAnnotations(Graph.class);
                
        Graph graph = new Graph();
        for (String key : nodeNames) {
            Node current = currentList.get(key);
            graph.addNode(current);
        } 
        String xml = xs.toXML(graph);
        return xml;
    }
    @WebMethod
    public void writeXML(File fileName) {
        Map<String, Node> currentList = new HashMap<String, Node>(list);
        Set<String> nodeNames = currentList.keySet();
        
        XStream xs = new XStream();        
        xs.processAnnotations(Node.class);
        xs.processAnnotations(Edge.class);
        xs.processAnnotations(Graph.class);
                
        Graph graph = new Graph();
        for (String key : nodeNames) {
            Node current = currentList.get(key);
            graph.addNode(current);
        } 
        try {
	    	BufferedWriter out = new BufferedWriter(new FileWriter(fileName + ".gexf"));
	        out.write(xs.toXML(graph));
	        out.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
       
    }
    @WebMethod
    public String getTopoXML(){
    	Map<String, Node> currentList = new HashMap<String, Node>(topology);
        Set<String> nodeNames = currentList.keySet();
        
        XStream xs = new XStream(new DomDriver());        
        xs.processAnnotations(Node.class);
        xs.processAnnotations(Edge.class);
        xs.processAnnotations(Graph.class);
                
        Graph graph = new Graph();
        for (String key : nodeNames) {
            Node current = currentList.get(key);
            graph.addNode(current);
        }               
        String xml = xs.toXML(graph);
        return xml;
    }
    @WebMethod
    public void writeTopoXML(File fileName){
    	Map<String, Node> currentList = new HashMap<String, Node>(topology);
        Set<String> nodeNames = currentList.keySet();
        
        XStream xs = new XStream();
        xs.setMode(XStream.NO_REFERENCES);
        xs.processAnnotations(Node.class);
        xs.processAnnotations(Edge.class);
        xs.processAnnotations(Graph.class);
                
        Graph graph = new Graph();
        for (String key : nodeNames) {
            Node current = currentList.get(key);
            graph.addNode(current);
        }               
        try {
	        BufferedWriter outTopo = new BufferedWriter(new FileWriter(fileName + ".topo.gexf"));
	        outTopo.write(xs.toXML(graph));
	        outTopo.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * alternative to writeXML(File fileName) for big result files
     * @param args the command line arguments
     */
    @WebMethod
    public void writeAlternativeXML(File fileName){
    	Map<String, Node> currentList = new HashMap<String, Node>(list);
        Set<String> nodeNames = currentList.keySet();
        XStream xs = new XStream();
        
        
        xs.setMode(XStream.NO_REFERENCES);
        xs.processAnnotations(Node.class);
        xs.processAnnotations(Edge.class);
        xs.processAnnotations(Graph.class);
        Graph graph = new Graph();
        for (String key : nodeNames) {
            Node current = currentList.get(key);
            graph.addNode(current);
        } 
        try {
        	BufferedWriter outXML = new BufferedWriter(new FileWriter(fileName + ".gexf"));
        	ObjectOutputStream out = xs.createObjectOutputStream(outXML, "graph");
        	List<Node> nodes = graph.getNodes();
        	List<Edge> edges = graph.getEdges();
        	for (int i=0;i<nodes.size();i++) {
        		out.writeObject(nodes.get(i));
        	}
        	for (int i =0;i<edges.size();i++){
        		out.writeObject(edges.get(i));
        	}
        	out.close();
        	outXML.close();
        } catch(IOException e) {
    		e.printStackTrace();
    	}
       	

    }

//    <edge target="172.16.17.2:9010" source="172.16.17.66:9010" id="172.16.17.66:9010_172.16.17.2:9010">
//    <spells class="linked-list">
//      <spell start="1403722909" end="1403722961"/>
//      <spell start="1403722979" end="1403723037"/>
//    </spells>
//  </edge>
    
    @WebMethod
    public void writeResults(File fileName) {
    	this.writeAlternativeXML(fileName);
    	//this.writeXML(fileName);
    	this.writeTopoXML(fileName);
    }
    
    public static void runTest(final StatServer s) {
        final Vector<Vector<String>> nodes = new Vector<Vector<String>>();
        final int max_nodes = 100;
        final Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < max_nodes; i++) {
            Vector<String> edges = new Vector<String>();
            for (int j = 0; j < 5; j++) {
                edges.add(String.valueOf(r.nextInt(max_nodes)));
            }
            nodes.add(i, edges);
        }

        Runnable testUpdater = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    for (Vector<String> node : nodes) {
                        int nodes_to_change = r.nextInt(node.size());
                        for (int i = 0; i < nodes_to_change; i++) {
                            node.set(r.nextInt(node.size()), String.valueOf(r.nextInt(max_nodes)));
                        }
                        s.sendList(String.valueOf(nodes.indexOf(node)), node);
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        new Thread(testUpdater).start();


    }

    public static void startServer(String endpointUrl) {
        final StatServer s = new StatServer();
        if (endpointUrl==null){
            endpointUrl = "http://87.77.4.63:8000/gossip";
        } 
        Endpoint.publish(endpointUrl, s);

//        runTest(s);

        Runnable validator = new Runnable() {

            @Override
            public void run() {
                while (true) {

                    Set<String> keys = s.list.keySet();
                    for (String key : keys) {
                        s.list.get(key).validate();
                    }
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                    }
                    //System.out.println(s.getXML());
                }
            }
        };
        new Thread(validator).start();
    }
}
