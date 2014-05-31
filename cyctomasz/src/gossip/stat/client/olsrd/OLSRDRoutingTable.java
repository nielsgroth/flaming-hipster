package gossip.stat.client.olsrd;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class OLSRDRoutingTable implements IRoutingTable {
	private TxtInfo olsrdInfo = new TxtInfo(8088);
	private String neighbors[][] = olsrdInfo.neighbors();
	@Override
	public InetAddress getBootstrapNode() {
		Random r = new Random();
		InetAddress bootstrapNode = null;
		while(bootstrapNode==null) {
			try {
				if (neighbors.length>0) bootstrapNode = InetAddress.getByName(neighbors[r.nextInt(neighbors.length)][0]);
			}catch(UnknownHostException e){
				bootstrapNode = null;
				e.printStackTrace();
				System.out.println(Thread.currentThread().getName() + ": trying to get bootstrapNode again...");
			}
		}
		return bootstrapNode;
	}

	@Override
	public InetAddress[] getAllBootstrapNodes() {
//		System.out.println("<<<<<<<<<<<<<    " + this.toString());
//		System.out.println("neighbors[0].length: " + neighbors[0].length);
//		System.out.println("neighbors.length: " + neighbors.length);
		InetAddress bootstrapNodes[] = new InetAddress[neighbors.length];
		//InetAddress[] bootstrapNodes = new InetAddress[neighbors.length];
		if (neighbors.length>0) {
			for( int i=0;i<neighbors.length; i++) {
				//System.out.println("counting= " + i);
				try {
					if (neighbors[i].length>1)
					bootstrapNodes[i] =
							InetAddress.getByName(neighbors[i][0]);
				} catch(UnknownHostException e) {
					bootstrapNodes[i]= null;
					//TODO do something about this. Maybe do not append to bootstrapNodes
				}
			}
		}else {
			System.out.println("Something went wrong with getAllBootstrapNodes");
		}
		return bootstrapNodes;
	}
	@Override
	public String toString(){
		String out = "";
		for (int i=0;i<neighbors.length;i++) {
			for (int j=0;j<neighbors[i].length;j++) {
				out += "\t" + neighbors[i][j];
			}
			out += "\n\r";
		}
		
		return out;
		
	}

}
