package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import driver.BassoonDriver;

public class BassoonClient {
	public SocketChannel client;
	public ByteBuffer buffer;
	
	public BassoonClient(String hostname, int portnum) {
		try {
			client = SocketChannel.open(new InetSocketAddress(BassoonDriver.hostname, BassoonDriver.portnum));
			buffer = ByteBuffer.allocate(BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		
		String line = "";
		System.out.print("Enter message>");
		while((line = scan.nextLine()) != null) {
			System.out.println("Client read: " + line);
			sendMessage(line);
		}
		
		
		
		
/*		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			System.out.println("beep");
			Socket socket = new Socket(BassoonDriver.hostname, BassoonDriver.portnum);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		
		String line = "";
		System.out.print("Enter message>");
		while((line = scan.nextLine()) != null) {
			ByteBuffer buf = ByteBuffer.allocate(BassoonDriver.MESSAGE_LENGTH_IN_BYTES);
			buf.put(line.getBytes());
			System.out.println(buf);
			out.println(line);
			out.flush();
		}*/
	}
	
	public void sendMessage(String msg) {
		buffer = ByteBuffer.wrap(msg.getBytes());
		//String response = null;
		try {
			client.write(buffer);

			/*
			buffer.clear();
			client.read(buffer);
			response = new String(buffer.array()).trim();
			System.out.println("response="+response);
			buffer.clear();
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
