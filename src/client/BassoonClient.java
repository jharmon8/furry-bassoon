package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

import driver.BassoonDriver;

public class BassoonClient {
	public SocketChannel client;
	public ByteBuffer buffer;
	
	public BassoonClient(String hostname, int portnum) {
		try {
			client = SocketChannel.open(new InetSocketAddress(BassoonDriver.hostname, BassoonDriver.portnum));
			buffer = ByteBuffer.allocate(BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
			client.configureBlocking(false);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		
		String line = "";
		System.out.print("Enter message>");
		while((line = scan.nextLine()) != null) {
			sendMessage(line);
			String msg = readMessage();
			if(msg != null) {
				System.out.println(msg);
			}
		}
	}
	
	public void sendMessage(String msg) {
		buffer = ByteBuffer.wrap(Arrays.copyOf(msg.getBytes(), BassoonDriver.MESSAGE_LENGTH_IN_BYTES));
		//String response = null;
		try {
			client.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readMessage() {
		buffer = ByteBuffer.allocate(BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
		//String response = null;
		try {
			client.read(buffer);
			String msg = new String(buffer.array()).trim();
			return msg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
