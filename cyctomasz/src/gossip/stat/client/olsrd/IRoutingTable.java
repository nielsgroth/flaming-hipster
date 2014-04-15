package gossip.stat.client.olsrd;

import java.net.InetAddress;

public interface IRoutingTable {
	public InetAddress getBootstrapNode(); 
	public InetAddress[] getAllBootstrapNodes();
}
