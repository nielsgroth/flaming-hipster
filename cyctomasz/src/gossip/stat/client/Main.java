package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sound.sampled.Line;

//import org.apache.commons.cli.*;

public class Main {

	public static void main(String[] args) {
/*		// create Options
		Options options = new Options();
		
		// add Options
		Option help = new Option("help","print this message");
		Option seed = new Option("seed", "don't know yet, JACK!");
		Option statServerIP = OptionBuilder.withArgName("IP4 address")
										.hasArg()
										.withDescription("use given IP4 address as StatServer")
										.create("statServerIP");
		
		Option statServerPort = OptionBuilder.withArgName("port")
											.hasArg()
											.withDescription("use given port at Statserver")
											.create("statServerPort");
		
		Option basePort = OptionBuilder.withArgName("port")
									.hasArg()
									.withDescription("use given port as basePort")
									.create("basePort");
		Option maxClients = OptionBuilder.withArgName("number of nodes simulated")
										.hasArg()
										.withDescription("use as max number of nodes simulated")
										.create("maxClients");
		// create all Options
		options.addOption(statServerIP);
		options.addOption(statServerPort);
		options.addOption(basePort);
		options.addOption(maxClients);
		options.addOption(seed);
		options.addOption(help);
		//create the parser
		CommandLineParser parser = new GnuParser();
		try {
			//parse the command line arguments
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("basePort")) {
				
			}
		} catch (ParseException exp) {
			// something went wrong
			System.err.println("Parsing failed. Reason: " +exp.getMessage());
		}
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "cyclonClient", options );
		
		//read the options
*/		
		// TODO Auto-generated method stub
		int basePort = 9000;
        int maxClients = 20;
        boolean seed = true;
        InetAddress seedIP = null;
        if (args.length > 0) {
            basePort = Integer.parseInt(args[0]);
            if (args.length > 1) {
            	maxClients = Integer.parseInt(args[1]);
            	if (args.length > 2) {
            		seed=Boolean.parseBoolean(args[2]);
            		if (args.length > 3) {
            			try {
							seedIP = InetAddress.getByName(args[3]);
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

	}

}
