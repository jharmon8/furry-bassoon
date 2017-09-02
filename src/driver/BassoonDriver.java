package driver;
import java.util.ArrayList;

import client.BassoonClient;
import server.BassoonServer;

public class BassoonDriver {

	public static String hostname = "localhost";
	public static int portnum = 8372;
	
	/**
	 * All this does is launch clients and a server.
	 * We'll wanna add command arguments, such as "c <hostname> <port>" eventually. TODO
	 * IntelliJ Commit Test
	 */
	public static void main(String[] args) {
		for(String str : args) {
			System.out.println(str);
		}
		
		System.out.println("Welcome to FurryBassoonDriver!");
		
		if(args.length < 1) {
			printUsage();
		} else if (args[0].equals("s")) {
			if(args.length > 1) {
				portnum = Integer.parseInt(args[1]);
			}
			System.out.println("Launching server on port " + portnum + "...");
			BassoonServer server = new BassoonServer(portnum);
		} else if (args[0].equals("c")) {
			if(args.length > 1) {
				hostname = args[1];
				portnum = Integer.parseInt(args[2]);
			}
			System.out.println("Launching client to connect to" + hostname + ":" + portnum + "...");
			BassoonClient client = new BassoonClient(hostname, portnum);
		} else {
			printUsage();
		}
	}
	
	/**
	 * Temp globals storage
	 */
	public static int MESSAGE_LENGTH_IN_BYTES = 128;
	
	private static void printUsage() {
		System.out.println("Usage for server: s portnum");
		System.out.println("Usage for client: c hostname portnum");
	}
}
