/**
 * 
 */
package com.assign.client;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Nikhil
 *
 */
public class FileReceiver {
	
	static int BUFF_SIZE = 1024;

	/**
	 * Receives file from the file server
	 * @param peerAddress
	 * @param fileName
	 * @param fileDir
	 */
	public void receiveFile(String peerAddress, String fileName, String fileDir){
		//Split the address string to get ip and port
		int index = peerAddress.indexOf('|');
		String peerIp = peerAddress.substring(0, index);
		int peerPort = Integer.parseInt(peerAddress.substring(index+1, peerAddress.length()));
		
		try {
			Socket fileClient = new Socket(peerIp,peerPort);
			System.out.println("Connected with the file peer at IP:" + peerIp + ", peerPort:" + peerPort);
			System.out.println("\nDownloading file. Please wait....");
			//Sending fileName
			ObjectOutputStream objOutStream = new ObjectOutputStream(fileClient.getOutputStream());
			objOutStream.writeObject(fileName);
			objOutStream.flush();
			
			//Receiving file
			InputStream inputStream = fileClient.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(fileClient.getInputStream());
			
			String receivedFileName = dataInputStream.readUTF();
			//System.out.println("File Name received from FileServer:" + receivedFileName);
			OutputStream outputStream = new FileOutputStream((fileDir + receivedFileName));
			
			long fileSize = dataInputStream.readLong();
			int bytesRead = 0; 
			byte[] dataBuffer = new byte[BUFF_SIZE];
			
			while (fileSize > 0 && (bytesRead = dataInputStream.read(dataBuffer, 0, (int) Math.min(dataBuffer.length, fileSize))) != -1){
				outputStream.write(dataBuffer, 0 , bytesRead);
				fileSize -= bytesRead;
			}
			outputStream.close();
			System.out.println("\nFile Downloaded Successfully !!\n");
			dataInputStream.close();
			fileClient.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
	}
}
