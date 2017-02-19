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
	String fileDir;
	
	public Server(int port, String fileDir) {
		this.port = port;
		this.fileDir = fileDir; //used to download the replica in the share folder
	}
	
	@Override
	public void run() {
		try {
			indexServerSocket = new ServerSocket(port);
			while (true){
				Socket client = indexServerSocket.accept();
				
				Thread hashTable = new Thread(new ServerHashTable(client, fileDir));
				hashTable.start();
			}
		} catch (IOException e) {
			System.out.println("Could not start the server..Please check if the port is available");
			e.printStackTrace();
		}
		
	}

}
