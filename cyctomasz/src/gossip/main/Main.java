/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gossip.main;

import gossip.stat.server.StatServer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
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
        
      	NetworkInterface serverInterface = NetworkInterface.getByName(args.length > 1 ? args[1] : "eth0");
        	boolean addressFound=false;
        	InetAddress serverAddress= null;
        	final String IPV4_REGEX = "^/(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
        	Enumeration<InetAddress> serverAddresses = serverInterface.getInetAddresses();
        	while(serverAddresses.hasMoreElements() && !addressFound) {
        		serverAddress = serverAddresses.nextElement();
        		
        		if (serverAddress.toString().matches(IPV4_REGEX)){
        			addressFound = true;
        		}
        	}
        	String serverAddressString = serverAddress.toString().replace("/","");
        	System.out.println("Starting server as " + serverAddressString);
            StatServer.startServer(args.length > 2 ? args[2] : (addressFound ? "http://" + serverAddressString + ":8000/gossipStatServer" :null));
    }
}
