package gossip.stat.client;

import gossip.stat.client.soap.StatServerService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.namespace.QName;

import org.apache.commons.cli.*;

public class Main {

	public static void main(String[] args) {
		//create Options Object
		Options options = new Options();
		
		// specify options
		options.addOption("s", true, "StatServer Address. Default ???");
		options.addOption("p", true, "base port for client. Default 9000");
		options.addOption("c", true, "Number of clients. If more than one specified, will try to use base port++ as ports. Default 1");
		options.addOption("i", true, "bootstrap client, gives the first simulated client an existing client in the network. Default none" );
		options.addOption("t", true, "Test scenario. Default none");
		options.addOption("o", true, "start in output mode instead. Argument is name of output file. Default ./outputCyclon.xml");
		options.addOption("q", true, "StatServer port. Default 8000");
		options.addOption("h", false, "Display this message");

		
		// read Options from command line
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
			formatter.printHelp( "cyclonClient", options );
			System.exit(0);
		}
		//Initialize options for both modes
		int statServerPort = (cmd.hasOption("q") ? Integer.parseInt(cmd.getOptionValue("q")) : 8000);
		
		// run output mode instead
		if (cmd.hasOption("o")) {
			String fileName = ((cmd.getOptionValue("o").equals("")||cmd.getOptionValue("o").equals(null)) ? "outputCyclon.xml" : cmd.getOptionValue("o")); 
            try {
            	InetAddress statServerAddress = InetAddress.getByName(cmd.hasOption("s") ? cmd.getOptionValue("s"): "" );
                StatServerService _s = new StatServerService
    					(new URL("http://" + statServerAddress.getHostName() + ":"+ statServerPort + "/gossipStatServer?wsdl"),
    							new QName("http://server.stat.gossip/", "StatServerService"));
                gossip.stat.client.soap.StatServer s = _s.getStatServerPort();                    
                BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
                out.write(s.getXML());
                out.close();
                System.out.println("XML Output written to "+fileName);
                System.exit(0);
            } catch (UnknownHostException e) {
            	System.err.println("Unkown statistics server host: " + cmd.getOptionValue("s") + " Exiting.");
    			System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
		} else {
			//Initialize options for normal mode
			int basePort = (cmd.hasOption("p") ? Integer.parseInt(cmd.getOptionValue("p")) : 9000);
	        int maxClients = (cmd.hasOption("c") ? Integer.parseInt(cmd.getOptionValue("c")) : 1);
	        boolean seed = !cmd.hasOption("i");
	        InetAddress seedIP = null;
	    	InetAddress statServerAddress = null;
	        if (cmd.hasOption("s")) { 
	        	try {
				statServerAddress = InetAddress.getByName(cmd.getOptionValue("s"));
			} catch (UnknownHostException e) {
				System.err.println("Unkown statistics server host: " + cmd.getOptionValue("c") + " Exiting.");
				System.exit(1);
			}
	        }
	        if (!seed)
				try {
					seedIP = InetAddress.getByName(cmd.getOptionValue("i"));
				} catch (UnknownHostException e1) {
					System.err.println("Unkown seed host: " + cmd.getOptionValue("c") + " Exiting.");
					System.exit(1);
				}
	        // run normal mode
	        System.out.println("trying to use " + statServerAddress.getHostName() + " Port: " + statServerPort + " as statServer");
	        try {
	            CyclonTest.runCyclon(basePort, maxClients, seed, seedIP, statServerAddress, statServerPort);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		

	}

}
