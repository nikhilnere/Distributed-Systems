/**
 * 
 */
package com.assign.client;

import java.io.BufferedInputStream; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Nikhil
 *
 */
public class FileSender implements Runnable {

	Socket client;
	String fileDir;
	
	/**
	 * parameterized constructor
	 * @param client
	 * @param fileDir
	 */
	public FileSender(Socket client, String fileDir) {
		this.client = client;
		this.fileDir = fileDir;
	}
	
	/**
	 * Sends file to the requesting peer
	 */
	@Override
	public void run() {
	
		try{
			
			ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
			
			//Receive filename from peer
			String fileName = (String)inFromClient.readObject();
			
			//Start sending file
			File file = new File (fileDir + fileName);  
			byte[] byteArray = new byte[(int)file.length()]; 
			
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			dataInputStream.readFully(byteArray, 0, byteArray.length);
			
			dataOutputStream.writeUTF(fileName);
			dataOutputStream.writeLong(byteArray.length);
			dataOutputStream.write(byteArray, 0 ,byteArray.length);
			dataOutputStream.flush();
			dataInputStream.close();
		}catch (ClassNotFoundException e) {
			System.out.println("Data type from client not supported");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading data from client");
			//e.printStackTrace();
		} 
	}

	
}
