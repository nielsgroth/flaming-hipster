package gossip.stat.client.olsrd;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class OLSRDRoutingTable implements IRoutingTable {
	private TxtInfo olsrdInfo = new TxtInfo("127.0.0.1", 8088);
	private String[][] neighbors = olsrdInfo.neighbors();
	@Override
	public InetAddress getBootstrapNode() {
		Random r = new Random();
		InetAddress bootstrapNode;
		try {
			bootstrapNode = InetAddress.getByName(neighbors[r.nextInt(neighbors.length)][1]);
		}catch(UnknownHostException e){
			bootstrapNode = null;
			e.printStackTrace();
		}
		return bootstrapNode;
	}

	@Override
	public InetAddress[] getAllBootstrapNodes() {
		InetAddress[] bootstrapNodes = new InetAddress[neighbors.length];
		for( int i=0;i<neighbors.length; i++) {
			try {
				bootstrapNodes[i] = InetAddress.getByName(neighbors[i][1]);
			} catch(UnknownHostException e) {
				e.printStackTrace();
				bootstrapNodes[i]= null;
				//TODO do something about this. Maybe do not append to bootstrapNodes
			}
		}
		return bootstrapNodes;
	}

}
