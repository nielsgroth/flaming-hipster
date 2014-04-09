package gossip.stat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

public class CyclonChurn {

    /**
     * @param args
     * @throws IOException 
     */
    public static void runCyclon(final int basePort, final int maxClients, final boolean isSeed,  
    		final InetAddress seedIP, final InetAddress statServerAddress, final int statServerPort, 
    		final InetAddress networkInterfaceIP) throws IOException {

        Runnable peerFactory = new Runnable() {
        	
        	
            private volatile int portOffset = 0;
            private volatile int currentlyRunning = 0;
            
            @Override
            public void run() {
        		for (int i=0;i<maxClients;i++) {
        			Thread runPeers = new Thread() {
        				 
        				@Override
        				public void run() {
        					 while(basePort + portOffset < 20000) {
		                        try {
		                            Random ontime = new Random();
		                            CyclonPeer p = new CyclonPeer(networkInterfaceIP, basePort + (portOffset++), statServerAddress, statServerPort);
		                            if (portOffset > 1) {
		                                p.addSeedNode(networkInterfaceIP, basePort + portOffset - ontime.nextInt(currentlyRunning));
		                            }
		                            else if (!isSeed) {
		                            	p.addSeedNode(seedIP, basePort);
		                            }
		                            Thread peerThread = new Thread(p);
		                            peerThread.start();
		                            currentlyRunning++;
		                            Thread.sleep((300-(ontime.nextInt(150))*1000));
		                            peerThread.interrupt();
		                            currentlyRunning--;
		                            
		                        }catch (InterruptedException ie) {
		                        	interrupt();
		                        	ie.printStackTrace();
		                        }catch (Exception e) {
		                            e.printStackTrace();
		                        }
        					 }
        				};
        			};
        			runPeers.start();
        			try {
                    	Random r = new Random();
                    	Thread.sleep(r.nextInt(1500));
                    } catch(InterruptedException e) {
                    	//TODO
                    }
            	}
        	}
        };
        new Thread(peerFactory).start();
    }
}
