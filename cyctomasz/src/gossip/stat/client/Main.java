package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
		
		// read Options from command line
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options,args);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);;
		}
		
		//Initialize options with default or command line entry
		int basePort = (cmd.hasOption("p") ? Integer.parseInt(cmd.getOptionValue("p")) : 9000);
        int maxClients = (cmd.hasOption("c") ? Integer.parseInt(cmd.getOptionValue("c")) : 1);
        boolean seed = (cmd.hasOption("i") ? false : true);
        InetAddress seedIP = null;
    	InetAddress statServerAddress = null;
        if (cmd.hasOption("s")) { try {
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
        // Set StatServer
        
        try {
            CyclonTest.runCyclon(basePort, maxClients, seed, seedIP,statServerAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
