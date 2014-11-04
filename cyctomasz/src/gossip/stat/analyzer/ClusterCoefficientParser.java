package gossip.stat.analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClusterCoefficientParser {
		
	public ClusterCoefficientParser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for(String arg : args){
			System.out.println(arg);
		}
		if (args[1].equals("cluster")){
			Map<Long,List<Float>> clusterCoeffs = new HashMap<Long, List<Float>>();
			
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[0]));
				boolean EOF=false;
				while (!EOF){
					String currentLine = in.readLine();
					if (currentLine==null) {EOF=true; continue;}
					// skip first Line
					if (currentLine.contains("Id")) {continue;}
					currentLine = currentLine.replaceAll("]; \\[", "#");
					String rawClusterCoefData = currentLine.split(";")[1];
					//remove delimiters and quotes "<>"
					rawClusterCoefData = rawClusterCoefData.replaceAll("\"<", "").replaceAll("<", "");
					rawClusterCoefData = rawClusterCoefData.replaceAll(">\"", "").replaceAll(">", "");
					String[] clusterCoefData = rawClusterCoefData.split("#");;
					for (String c : clusterCoefData) {
						c = c.replace("[", "");
						c = c.replace("]", "");
						String currentTime_raw = c.split(",")[0];
						if (currentTime_raw.contains("empty")) continue;
						Long currentTime = Double.valueOf(currentTime_raw).longValue() ;
						Float currentCoefValue = Float.parseFloat(c.split(",")[2]);
						
						List<Float> currentCoefs = new LinkedList<Float>();
						if (clusterCoeffs.containsKey(currentTime)) {
							currentCoefs = clusterCoeffs.remove(currentTime);
							}
						currentCoefs.add(currentCoefValue);
						clusterCoeffs.put(currentTime, currentCoefs);
						}
					}
				// All values are now read in
				in.close();
				// calculate averages
				Map <Long,Float> averageClustCoef = new HashMap<Long, Float>();
				for (Long currentTime : clusterCoeffs.keySet()) {
					List<Float> currentCoefs = clusterCoeffs.get(currentTime);
					for (Float currentCoef : currentCoefs) {
						if (averageClustCoef.containsKey(currentTime))
							averageClustCoef.put(currentTime, (averageClustCoef.get(currentTime) + currentCoef));
						else
							averageClustCoef.put(currentTime, currentCoef);
					}
					averageClustCoef.put(currentTime,averageClustCoef.get(currentTime)/currentCoefs.size());
				}
				// write averages to GNUPLOT-readable outputfile
				BufferedWriter out = new BufferedWriter(new FileWriter(new File(args[0]+".averages")));
				out.write("X \t Y \n");
				for (Long currentTime : averageClustCoef.keySet()) {
					String currentLine;
					currentLine = currentTime + " \t " + averageClustCoef.get(currentTime) + "\n";
					out.write(currentLine);
				}
				out.close();
				System.out.println("Done...");
			} catch(FileNotFoundException e) {
				System.out.println("File " + args[0] + " not found. Exiting...");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (true){
			System.out.println("Counting Degrees");
			Map<Long,List<Integer>> inDegrees = new HashMap<Long, List<Integer>>();
			Map<Long,List<Integer>> outDegrees = new HashMap<Long, List<Integer>>();
			try {
				BufferedReader in = new BufferedReader(new FileReader(args[0]));
				boolean EOF=false;
				while (!EOF){
					String currentLine = in.readLine();
					if (currentLine==null) {EOF=true; continue;}
					// skip first Line
					if (currentLine.contains("Id")) {continue;}
					currentLine = currentLine.replaceAll("]; \\[", "#");
					String rawInDegreeData = currentLine.split(";")[1];
					String rawOutDegreeData = currentLine.split(";")[2];
					//remove delimiters and quotes "<>"
					rawInDegreeData = rawInDegreeData.replaceAll("\"<", "").replaceAll("<", "");
					rawInDegreeData = rawInDegreeData.replaceAll(">\"", "").replaceAll(">", "");
					rawOutDegreeData = rawOutDegreeData.replaceAll("\"<", "").replaceAll("<", "");
					rawOutDegreeData = rawOutDegreeData.replaceAll(">\"", "").replaceAll(">", "");
					String[] inDegreeData = rawInDegreeData.split("#");
					String[] outDegreeData = rawOutDegreeData.split("#");
					// 1 - Read InDegrees
					for (String c : inDegreeData) {
						c = c.replace("[", "");
						c = c.replace("]", "");
						String currentTime_raw = c.split(",")[0];
						Long currentTime = Double.valueOf(currentTime_raw).longValue() ;
						Integer currentInDegreeData = Integer.parseInt(c.split(",")[2].trim());
						
						List<Integer> currentInDegree = new LinkedList<Integer>();
						if (inDegrees.containsKey(currentTime)) {
							currentInDegree = inDegrees.remove(currentTime);
							}
						currentInDegree.add(currentInDegreeData);
						inDegrees.put(currentTime, currentInDegree);
						}
					// 2 - Read OutDegrees
					for (String c : outDegreeData) {
						c = c.replace("[", "");
						c = c.replace("]", "");
						String currentTime_raw = c.split(",")[0];
						Long currentTime = Double.valueOf(currentTime_raw).longValue() ;
						Integer currentOutDegreeData = Integer.parseInt(c.split(",")[2].trim());
						
						List<Integer> currentOutDegree = new LinkedList<Integer>();
						if (outDegrees.containsKey(currentTime)) {
							currentOutDegree = outDegrees.remove(currentTime);
							}
						currentOutDegree.add(currentOutDegreeData);
						outDegrees.put(currentTime, currentOutDegree);
						}
					}
				
				// All values are now read in
				in.close();
				// calculate in-degree distribution
				Map <Long,Map<Integer,Integer>> countInDegrees = new HashMap<Long,Map<Integer,Integer>>();
				for (Long currentTime : inDegrees.keySet()) {
					List<Integer> currentInDegrees = inDegrees.get(currentTime);
					Map<Integer,Integer> currentCountInDegrees = new HashMap<Integer,Integer>(); 
					for (Integer currentInDegree : currentInDegrees) {
						if(!currentCountInDegrees.containsKey(currentInDegree)) {
							currentCountInDegrees.put(currentInDegree, 1);
						}
						else{
							currentCountInDegrees.put(currentInDegree,currentCountInDegrees.get(currentInDegree)+1);
						}
					}
					countInDegrees.put(currentTime,currentCountInDegrees);
				}
				// calculate out-degree distribution
				Map <Long,Map<Integer,Integer>> countOutDegrees = new HashMap<Long,Map<Integer,Integer>>();
				for (Long currentTime : outDegrees.keySet()) {
					List<Integer> currentOutDegrees = outDegrees.get(currentTime);
					Map<Integer,Integer> currentCountOutDegrees = new HashMap<Integer,Integer>(); 
					for (Integer currentOutDegree : currentOutDegrees) {
						if(!currentCountOutDegrees.containsKey(currentOutDegree)) {
							currentCountOutDegrees.put(currentOutDegree, 1);
						}
						else{
							currentCountOutDegrees.put(currentOutDegree,currentCountOutDegrees.get(currentOutDegree)+1);
						}
					}
					countOutDegrees.put(currentTime,currentCountOutDegrees);
				}
				// write InDegrees to GNUPLOT-readable outputfile
				BufferedWriter out = new BufferedWriter(new FileWriter(new File(args[0]+".inDegrees")));
				out.write("X \t Y \t Z \n");
				for (Long currentTime : countInDegrees.keySet()) {
					for(Integer currentInDegree : countInDegrees.get(currentTime).keySet()) {
						String currentLine = "";
						currentLine = currentTime + "\t" + currentInDegree + "\t";
						currentLine += countInDegrees.get(currentTime).get(currentInDegree) + "\n";
						out.write(currentLine);
					}
				}
				out.close();
				System.out.println("Done... In-Degrees");
				// write OutDegrees to GNUPLOT-readable outputfile
				BufferedWriter out_outDegree = new BufferedWriter(new FileWriter(new File(args[0]+".outDegrees")));
				out_outDegree.write("X \t Y \t Z \n");
				for (Long currentTime : countOutDegrees.keySet()) {
					for(Integer currentOutDegree : countOutDegrees.get(currentTime).keySet()) {
						String currentLine = "";
						currentLine = currentTime + "\t" + currentOutDegree + "\t";
						currentLine += countOutDegrees.get(currentTime).get(currentOutDegree) + "\n";
						out_outDegree.write(currentLine);
					}
				}
				out_outDegree.close();
				System.out.println("Done... Out-Degrees");
			} catch(FileNotFoundException e) {
				System.out.println("File " + args[0] + " not found. Exiting...");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		} 
		
}
