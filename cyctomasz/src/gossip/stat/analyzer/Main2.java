package gossip.stat.analyzer;

import gossip.stat.server.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Main2 {

	public Main2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Graph physicalTopo = new Graph();
		Graph topology = new Graph();
		String outputfile = "";
		
		Scanner in = new Scanner(System.in);
		boolean nextStep = false;
		while(!nextStep) {
			System.out.println("");
			System.out.println("Enter path/name of topology files and output file:");
			String s = in.nextLine();
			File physicalTopoFile = new File(s + ".topo.gexf");
			File topologyFile = new File(s + ".gexf");
			if (!physicalTopoFile.isFile()) System.out.println("File does not exist.");
			else {
				System.out.print("Reading file as physical topology: " + physicalTopoFile.getName() + "...");
				physicalTopo.readAlternateResults(physicalTopoFile);
				System.out.println("...finished.");
				System.out.print("Reading file as virtual topology: " + topologyFile.getName() + "...");
				topology.readAlternateResults(topologyFile);
				System.out.println("...finished.");
				outputfile = s;
				System.out.println("Outputfile prefix set to: " + s);
				nextStep=true;
			}
		}
		nextStep=false;
		while(!nextStep){
			System.out.println("");
			System.out.println("Enter calculation mode:");
			String s = in.nextLine();
			if(s.startsWith("exit")){
				System.out.println("exiting...");
				System.exit(0);
			} else if(s.startsWith("probsX")) {
				int intervalStart = 2500000;
				int intervalEnd = 2530000;
				System.out.println("calculating probs with fixed interval. Interval set to: from " + intervalStart + " to " + intervalEnd);
				String outputfileName = outputfile + ".probs_" + intervalStart + "_" + intervalEnd + ".txt";
				try {
					Writer output = new FileWriter(outputfileName);
					output.write(physicalTopo.getAnalytics(intervalStart, intervalEnd, topology, physicalTopo));
					output.close();
					System.out.println("results written to " + outputfileName);
				} catch(IOException e) {
					e.printStackTrace();
				}				
			} else if (s.startsWith("probs")){
				System.out.println("set interval start: ");
				s = in.nextLine();
				int intervalStart = Integer.parseInt(s);
				System.out.println("set interval end:");
				s=in.nextLine();
				int intervalEnd = Integer.parseInt(s);
				String outputfileName = outputfile + ".probs_" + intervalStart + "_" + intervalEnd + ".txt";
				System.out.println("results will be written to " + outputfileName);
				try {
					Writer output = new FileWriter(outputfileName);
					output.write(physicalTopo.getAnalytics(intervalStart, intervalEnd, topology, physicalTopo));
					output.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				System.out.println("Finished!");
				
				
			}else {
				System.out.println("Unkown calculation mode. Known modes are:");
				System.out.println("probs - calculate probability for a link to exist within a timeframe depending on phisical hops.");
				System.out.println("exit - exits the program.");
			}
		}

	}

}
