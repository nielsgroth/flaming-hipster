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
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 * @author Tomasz3
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws SocketException 
     */
    public static void main(String[] args) throws SocketException {
        
        switch(args[0]) {
        case "": 
        	System.err.println("No arguments!");
        	break;
        case "server":
        	NetworkInterface serverInterface = NetworkInterface.getByName(args.length > 1 ? args[1] : "eth0");
        	boolean found=false;
        	InetAddress serverAddress= null;
        	final String IPV4_REGEX = "^/(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
        	Enumeration<InetAddress> serverAddresses = serverInterface.getInetAddresses();
        	while(serverAddresses.hasMoreElements() && !found) {
        		serverAddress = serverAddresses.nextElement();
        		
        		if (serverAddress.toString().matches(IPV4_REGEX)){
        			found = true;
        		}
        	}
        	String serverAddressString = serverAddress.toString().replace("/","");
        	System.out.println("Starting server as " + serverAddressString);
            StatServer.startServer(args.length > 2 ? args[2] : (found ? "http://" + serverAddressString + ":8000/wsdl" :null));
            /*
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface current = interfaces.nextElement();
                System.out.println(current);
                if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue;
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress current_addr = addresses.nextElement();
                    if (current_addr.isLoopbackAddress()) continue;
                    System.out.println(current_addr.getHostAddress());
                }
            }*/
            break;
        case "client" :
        	int basePort = 9000;
            int maxClients = 1000;
            boolean seed = true;
            InetAddress seedIP = null;
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
