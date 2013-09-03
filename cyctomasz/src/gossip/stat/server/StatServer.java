/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.stat.server;

import java.util.HashMap;
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
 * @author Maciek
 */
@WebService
public class StatServer {

    public Map<String, Node> list = new ConcurrentHashMap<String, Node>(1000);

    public Node getNode(String key) {
        if (this.list.containsKey(key)) {
            return this.list.get(key);
        } else {
            Node n = new Node(key);
            this.list.put(key, n);
            return n;
        }
    }

    /**
     * @param args the command line arguments
     */
    @WebMethod
    public void sendList(@WebParam(name = "id") String key, @WebParam(name = "edgeList") List<String> edges) {
        System.out.println(key);
        this.getNode(key).updateEdges(edges);
    }
    
    @WebMethod
    public String getXML() {
        Map<String, Node> currentList = new HashMap<String, Node>(list);
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
            endpointUrl = "http://localhost:8000/gossip";
        } 
        Endpoint endpoint = Endpoint.publish(endpointUrl, s);

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
