/**
 * 
 */
package com.assign.disthashtable;

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
	static int BUFF_SIZE = 1024;
	
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
			long fileSize = file.length();
			//byte[] byteArray = new byte[(int)file.length()]; 
			
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			
			dataOutputStream.writeUTF(fileName);
			dataOutputStream.writeLong(fileSize);
			
			
			int bytesRead = 0; 
			byte[] dataBuffer = new byte[BUFF_SIZE];
			
			while (fileSize > 0 && (bytesRead = dataInputStream.read(dataBuffer, 0, (int) Math.min(dataBuffer.length, fileSize))) != -1){
				dataOutputStream.write(dataBuffer, 0 ,bytesRead);
				dataOutputStream.flush();
				fileSize -= bytesRead;
			}
			
			/*dataInputStream.readFully(byteArray, 0, byteArray.length);
			dataOutputStream.write(byteArray, 0 ,byteArray.length);
			dataOutputStream.flush();*/
			
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
