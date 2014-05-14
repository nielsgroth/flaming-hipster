package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclonChurn {

    /**
     * @param args
     * @throws IOException 
     */
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  
    		final InetAddress seedIP, final InetAddress statServerAddress, final int statServerPort, 
    		final InetAddress networkInterfaceIP) throws IOException {

        Runnable peerFactory = new Runnable() {
        	
        	
            private AtomicInteger portOffset = new AtomicInteger();
            private AtomicInteger currentlyRunning = new AtomicInteger();
            
            @Override
            public void run() {
        		for (int i=0;i<maxClients;i++) {
        			Thread runPeers = new Thread() {
        				 
        				@Override
        				public void run() {
        					 while(basePort + portOffset.get() < 20000) {
		                        try {
		                            Random ontime = new Random();
		                            CyclonPeer p = new CyclonPeer(networkInterfaceIP, basePort + (portOffset.incrementAndGet()), statServerAddress, statServerPort);
		                            if (portOffset.get() > 1) {
		                                p.addSeedNode(networkInterfaceIP, basePort + portOffset.get() -1);
		                            }
		                            else if (!isSeed) {
		                            	p.addSeedNode(seedIP, basePort);
		                            }
		                            Thread peerThread = new Thread(p);
		                            peerThread.start();
		                            currentlyRunning.incrementAndGet();
		                            Thread.sleep((300-ontime.nextInt(150))*1000);
		                            peerThread.interrupt();
		                            currentlyRunning.decrementAndGet();
		                            
		                        }catch (InterruptedException ie) {
		                        	interrupt();
		                        	ie.printStackTrace();
		                        }catch (Exception e) {
		                            e.printStackTrace();
		                        }
        					 }//while
        				};//run
        			};//runPeers
        			runPeers.start();
        			try {
                    	Random r = new Random();
                    	Thread.sleep(r.nextInt(1500));
                    } catch(InterruptedException e) {
                    	runPeers.interrupt();
                    }
            	}
        	}
        };
        new Thread(peerFactory).start();
    }
}
