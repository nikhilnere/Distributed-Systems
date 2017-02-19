package com.assign.disthashtable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nikhil
 *
 */
public class Server implements Runnable{

	int port;
	ServerSocket indexServerSocket;
	
	public Server(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			indexServerSocket = new ServerSocket(port);
			while (true){
				Socket client = indexServerSocket.accept();
				
				Thread hashTable = new Thread(new ServerHashTable(client));
				hashTable.start();
			}
		} catch (IOException e) {
			System.out.println("Could not start the server..Please check if the port is available");
			e.printStackTrace();
		}
		
	}

}
