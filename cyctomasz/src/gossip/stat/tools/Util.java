package gossip.stat.tools;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Util {
	
	  /**
	   * Finds a local, non-loopback, IPv4 address
	   * 
	   * @param String name of NetworkInterface, f.e. wlan0 or eth0...
	   * @return The non-loopback IPv4 address found for the specified Network Interface, or
	   *         <code>null</code> if no such addresses found
	   * @throws SocketException
	   *            If there was a problem querying the network
	   *            interfaces
	   */
	  public static InetAddress getLocalAddressbyName(String nwName) throws SocketException
	  {
	      NetworkInterface iface = NetworkInterface.getByName(nwName);
	      Enumeration<InetAddress> addresses = iface.getInetAddresses();

	      while( addresses.hasMoreElements() )
	      {
	        InetAddress addr = addresses.nextElement();
	        if( addr instanceof Inet4Address && !addr.isLoopbackAddress() )
	        {
	          return addr;
	        }
	      }

	    return null;
	  }

}
