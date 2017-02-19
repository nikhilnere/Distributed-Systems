/**
 * 
 */
package com.assign.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nikhil
 *
 */
public class FilePeerServer implements Runnable{

	int port;
	String fileDir;
	ServerSocket fileServerSocket;
	
	public FilePeerServer(int port, String fileDir) {
		this.port = port;
		this.fileDir = fileDir;
	}
	
	/**
	 * Keeps on listening for file download requests and sends file to the requesting peers
	 * Handles multiple clients by starting independent thread for each client
	 */
	@Override
	public void run() {
		try {
			fileServerSocket = new ServerSocket(port);
			while (true){
				Socket client = fileServerSocket.accept();
				Thread clientThread = new Thread(new FileSender(client, fileDir));
				clientThread.start();
			}
		} catch (IOException e) {
			System.out.println("Could not start the file server..Please check if the port is available");
			e.printStackTrace();
		}
	}
	
}
