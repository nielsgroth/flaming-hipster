/**
 * 
 */
package gossip.stat.client;

import java.net.InetAddress;

/**
 * @author Tomasz
 *
 */
public class CorruptedPeer extends CyclonPeer implements Runnable{

	public CorruptedPeer(InetAddress ip, int port, InetAddress statServerAddress) {
		super(ip, port, statServerAddress);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
