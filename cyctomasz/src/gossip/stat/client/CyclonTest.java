package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CyclonTest {

    /**
     * @param args
     * @throws IOException 
     */
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  final InetAddress seedIP, final InetAddress statServerAddress) throws IOException {

        Runnable peerFactory = new Runnable() {

            private List<Integer> activePeers = new LinkedList<Integer>();
            //private int basePort = 9000;
            private int portOffset = 0;
            //private int maxClients = 1000;
            
            
            @Override
            public void run() {
                while (true && portOffset < maxClients) {
                	// TODO use specified Network interface!!!
                    try {
                    	System.out.println("I'm totally here now: CyclonTest:30");
                        Random r = new Random();
                        CyclonPeer p = new CyclonPeer(InetAddress.getLocalHost(), basePort + (portOffset++), statServerAddress);
                        if (portOffset > 1) {
                        	System.out.println("I'm totally here now: CyclonTest:34");
                            p.addSeedNode(InetAddress.getLocalHost(), basePort + r.nextInt(portOffset - 1));
                        }
                        else if (!isSeed) {
                        	System.out.println("I'm totally here now: CyclonTest:38");
                        	p.addSeedNode(seedIP, basePort + r.nextInt(portOffset - 1));
                        }
                        new Thread(p).start();
                        Thread.sleep(r.nextInt(1500));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        System.out.println("I'm totally here now: CyclonTest:50");
        new Thread(peerFactory).start();
    }
}
