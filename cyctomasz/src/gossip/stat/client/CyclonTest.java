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
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  final InetAddress seedIP) throws IOException {

        Runnable peerFactory = new Runnable() {

            private List<Integer> activePeers = new LinkedList<Integer>();
            //private int basePort = 9000;
            private int portOffset = 0;
            //private int maxClients = 1000;
            
            
            @Override
            public void run() {
                while (true && portOffset < maxClients) {
                    try {
                        Random r = new Random();
                        CyclonPeer p = new CyclonPeer(InetAddress.getLocalHost(), basePort + (portOffset++));
                        if (portOffset > 1) {
                            p.addSeedNode(InetAddress.getLocalHost(), basePort + r.nextInt(portOffset - 1));
                        }
                        else if (!isSeed) {
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
        new Thread(peerFactory).start();


//        CyclonPeer p1 = new CyclonPeer(InetAddress.getLocalHost(), 8888);
//        CyclonPeer p2 = new CyclonPeer(InetAddress.getLocalHost(), 8887);
//        CyclonPeer p3 = new CyclonPeer(InetAddress.getLocalHost(), 8886);
//        CyclonPeer p4 = new CyclonPeer(InetAddress.getLocalHost(), 8885);
//        CyclonPeer p5 = new CyclonPeer(InetAddress.getLocalHost(), 8884);
//
//        p1.addSeedNode(InetAddress.getLocalHost(), 8887);
//        p1.addSeedNode(InetAddress.getByName("1.2.3.4"), 180);
//        p1.addSeedNode(InetAddress.getByName("8.8.8.8"), 1234);
//
//        p2.addSeedNode(InetAddress.getLocalHost(), 8888);
//        p2.addSeedNode(InetAddress.getByName("5.6.7.8"), 27);
//        p2.addSeedNode(InetAddress.getByName("6.7.8.9"), 2089);
//
//        p3.addSeedNode(InetAddress.getLocalHost(), 8888);
//        p4.addSeedNode(InetAddress.getLocalHost(), 8888);
//        p5.addSeedNode(InetAddress.getLocalHost(), 8888);
//
//        new Thread(p1).start();
//        new Thread(p2).start();
//        new Thread(p3).start();
//        new Thread(p4).start();
//        new Thread(p5).start();



        //p1.shuffleInit();
    }
}
