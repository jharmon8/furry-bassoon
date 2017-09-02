package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import driver.BassoonDriver;

public class NetworkParent {
	public Selector selector;
	public ServerSocketChannel serverSocket;
	
	public NetworkParent() throws IOException {
		selector = Selector.open();
		
		serverSocket = ServerSocketChannel.open();
		InetSocketAddress hostAddress = new InetSocketAddress(BassoonDriver.hostname, BassoonDriver.portnum);
		serverSocket.bind(hostAddress);
		
		serverSocket.configureBlocking(false);
		
		
	}
}
