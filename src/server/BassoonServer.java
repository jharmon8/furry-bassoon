package server;

import java.io.IOException;

public class BassoonServer {
	public BassoonServer(int portnum) {
		Thread t = new Thread(new ServerRunner());
		t.start();
	}
	
	private class ServerRunner implements Runnable {
		@Override
		public void run() {
			try {
				NetworkParent np = new NetworkParent();
				Receiver r = new Receiver(np);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
