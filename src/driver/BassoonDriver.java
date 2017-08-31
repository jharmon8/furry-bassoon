package driver;
import java.util.ArrayList;
import java.util.Scanner;

import client.BassoonClient;
import server.BassoonServer;

public class BassoonDriver {

	public static String hostname = "localhost";
	public static int portnum = 8372;
	public static BassoonServer server;
	public static ArrayList<BassoonClient> clients;
	
	/**
	 * All this does is launch clients and a server.
	 * We'll wanna add command arguments, such as "c <hostname> <port>" eventually. TODO
	 */
	public static void main(String[] args) {
		clients = new ArrayList<BassoonClient>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to FurryBassoonDriver!");
		System.out.println("Enter 'c' to launch a client, or 's' to launch the server.");
		System.out.println("Enter 'q' to quit.");
		
		String line = "";
/*		while(!(line = scan.nextLine()).equals("q")) {
			if(line.equals("c")) {
				clients.add(new BassoonClient(hostname, portnum));
			}
			if(line.equals("s")) {
				if(server == null) {
					server = new BassoonServer(portnum);
				} else {
					System.out.println("Server is already running!");
				}
			}
		}*/
		
		server = new BassoonServer(portnum);
		clients.add(new BassoonClient(hostname, portnum));
		
	}
	
	/**
	 * Temp globals storage
	 */
	public static int MESSAGE_LENGTH_IN_BYTES = 128;
}
