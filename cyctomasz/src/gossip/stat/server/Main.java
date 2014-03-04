package gossip.stat.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.commons.cli.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Add options
		
		Options options = new Options();
		
		options.addOption("a",true,"TestOption with Argument");
		options.addOption("n",true,"Use this Network Interface's InetAdress as endpoint. Default eth0");
		options.addOption("p", true, "Use this port as endpoint. Default 8000");
		
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options,args);
		
		if (cmd.hasOption("a")) System.out.println("option a has following argument: " + cmd.getOptionValue("a"));
		else System.out.println("failed");
		
      	NetworkInterface serverInterface = NetworkInterface.getByName(cmd.hasOption("n") ? cmd.getOptionValue("n") : "eth0");
      	//get InetAddress IP of specified network interface
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
    	//print the IP-address if found
    	String serverPortString = (cmd.hasOption("p") ? cmd.getOptionValue("p"): "8000");
    	if (!serverPortString.matches("[1-9999]")) System.err.println("port must be between 1 and 9999");
     	if (addressFound)  System.out.println("Starting server at " + serverAddressString + " port: " + serverPortString);
    	else System.out.println("Starting server at default address");
        StatServer.startServer(addressFound ? ("http://" + serverAddressString + ":" + serverPortString + "/gossipStatServer") :null);;
	}

}
