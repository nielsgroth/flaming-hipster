package gossip.stat.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Main {

	public static void main(String[] args) {
		
		Options options = new Options();
		
		options.addOption("n", true, "Use this Network Interface's InetAdress as endpoint. Default eth0");
		options.addOption("p", true, "Use this port as endpoint. Default 8000");
		options.addOption("h", false, "Display this message");
		
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options,args);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);;
		}
		
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		if (cmd.hasOption("h")) {
			formatter.printHelp("cyclonClient", options);
			System.exit(0);
		}
		
      	NetworkInterface serverInterface = null;
		try {
			serverInterface = NetworkInterface.getByName(cmd.hasOption("n") ? cmd.getOptionValue("n") : "eth0");
		} catch (SocketException e) {
			e.printStackTrace();
			formatter.printHelp("cyclonClient", options);
			System.exit(1);
		}
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
    	if (!serverPortString.matches("^[1-9][0-9]{0,5}$")){
    		System.err.println("port must be between 1 and 99999");
    		System.exit(0);
    	}
     	if (addressFound)  System.out.println("Starting server at " + serverAddressString + " port: " + serverPortString);
    	else System.out.println("Starting server at default address");
        StatServer.startServer(addressFound ? ("http://" + serverAddressString + ":" + serverPortString + "/gossipStatServer") :null);;
	}

}