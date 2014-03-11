package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

public class CyclonTest {

    /**
     * @param args
     * @throws IOException 
     */
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  
    		final InetAddress seedIP, final InetAddress statServerAddress, final int statServerPort, 
    		final InetAddress networkInterfaceIP) throws IOException {

        Runnable peerFactory = new Runnable() {
        	
        	
            private int portOffset = 0;
            
            @Override
            public void run() {
                while (true && portOffset < maxClients) {
                	// TODO use specified Network interface!!!
                    try {
                        Random r = new Random();
                        CyclonPeer p = new CyclonPeer(networkInterfaceIP, basePort + (portOffset++), statServerAddress, statServerPort);
                        if (portOffset > 1) {
                            p.addSeedNode(InetAddress.getLocalHost(), basePort + r.nextInt(portOffset - 1));
                        }
                        else if (!isSeed) {
                        	p.addSeedNode(seedIP, basePort);
                        }
                        new Thread(p).start();
                        Thread.sleep(r.nextInt(1500));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        new Thread(peerFactory).start();
    }
}
