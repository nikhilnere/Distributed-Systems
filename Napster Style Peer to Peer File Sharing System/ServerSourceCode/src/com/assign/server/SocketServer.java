package com.assign.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Nikhil
 *
 */
public class SocketServer {

	private int port;
	private ServerSocket serverSocket;
	private static Properties prop;
	
	public SocketServer() throws FileNotFoundException, IOException {
		//port = portNo;
		prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		this.port = Integer.parseInt(prop.getProperty("server.port"));
	}
	
	/**
	 * Keeps on listening for the file register and search request
	 * Handles multiple clients by creating independent thread for each client
	 * @throws IOException
	 */
	public void startServer () throws IOException{
		
		System.out.println("Server starting at : " + port);
		serverSocket = new ServerSocket(port);
		
		System.out.println("Server is up!!\n");
		
		while (true){
			Socket client = serverSocket.accept();
			Thread clientThread = new Thread(new HandleClient(client));
			clientThread.start();
		}
	}
	
	public static void main(String[] args) throws IOException {
		SocketServer socketServer = new SocketServer();
		socketServer.startServer();
	}
}
