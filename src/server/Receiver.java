package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import driver.BassoonDriver;

public class Receiver {
	public Receiver(NetworkParent np) throws IOException {
		ServerSocketChannel serverSocket = np.serverSocket;
		Selector selector = np.selector;
		
		System.out.println("boop");
		
		int ops = serverSocket.validOps();
		
		SelectionKey selectKey = serverSocket.register(selector, ops);
		
		while(true) {
			int noOfKeys = selector.select();
			
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter = selectedKeys.iterator();
			
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				
				if (key.isAcceptable()) {
					// accept
					SocketChannel client = serverSocket.accept();
					if(client != null) {
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
						System.out.println("New client!");
					}
				} else {
					if (key.isReadable()) {
						System.out.println("Data Read!");
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buf = ByteBuffer.allocate(BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
						int bytesRead = client.read(buf);
						assert(bytesRead == BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
						System.out.println(new String(buf.array()));
					}
				}
			}
		}
	}
}
