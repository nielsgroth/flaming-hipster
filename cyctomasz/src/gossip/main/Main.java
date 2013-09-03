/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.main;

import gossip.stat.client.CyclonTest;
import gossip.stat.client.soap.StatServerService;
import gossip.stat.server.StatServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Tomasz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        switch(args[0]) {
        case "": 
        	System.err.println("No arguments!");
        	break;
        case "server":
        	System.out.println("Starting StatServer at: " + (args.length > 1 ? args[1] : "hard coded usually localhost:8000/gossip"));
            StatServer.startServer(args.length > 1 ? args[1] : null);
            break;
        case "client" :
        	int basePort = 9000;
            int maxClients = 1000;
            boolean seed = true;
            InetAddress seedIP = null;
            String statUrl = "http://localhost:8000/gossip";
            if (args.length > 1) {
                basePort = Integer.parseInt(args[1]);
                if (args.length > 2) {
                	maxClients = Integer.parseInt(args[2]);
                	if (args.length > 3) {
                		seed=Boolean.parseBoolean(args[3]);
                		if (args.length > 4) {
                			try {
								seedIP = InetAddress.getByName(args[4]);
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		}
                    }
                }
            }
            try {
                CyclonTest.runCyclon(basePort, maxClients, seed, seedIP);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "output" :
        	String fileName = "output.xml";
            if (args.length > 1) {
                fileName = args[1];
            }
            try {
                StatServerService _s = new StatServerService();
                gossip.stat.client.soap.StatServer s = _s.getStatServerPort();                    
                BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
                out.write(s.getXML());
                out.close();
                System.out.println("XML Output written to "+fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        default : System.err.println("Unknown argument " + args[0]);
        }
    }
}
