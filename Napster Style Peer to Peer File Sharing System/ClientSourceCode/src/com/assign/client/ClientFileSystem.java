/**
 * 
 */
package com.assign.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.assign.dto.RegisterDTO;

/**
 * @author Nikhil
 *
 */
public class ClientFileSystem {

	Socket socketClient;
	
	/**
	 * Sends request to the server to register the files
	 */
	public void registerFile(String hostname, int serverPort, RegisterDTO regDTO){
		
		try {
			socketClient = new Socket(hostname,serverPort);
			//System.out.println("Connection established!!");
			
			ObjectOutputStream objOutStream = new ObjectOutputStream(socketClient.getOutputStream());
			objOutStream.writeObject(regDTO);
			objOutStream.flush();
			
			socketClient.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> searchFile(String hostname, int serverPort,String fileName) {		
		try {
			socketClient = new Socket(hostname,serverPort);
			ObjectOutputStream objOutStream = new ObjectOutputStream(socketClient.getOutputStream());
			objOutStream.writeObject(fileName);
			objOutStream.flush();
			
			ObjectInputStream  objInStream = new ObjectInputStream(socketClient.getInputStream());
			Object obj = objInStream.readObject();
			
			if (obj instanceof ArrayList){
				ArrayList<String> peerList = (ArrayList<String>)obj;
				return peerList;
				
			}else{
				String msg = (String)obj;
				if("NotFound".equals(msg))
					System.out.println("\nFile Not found on server..");
			}
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
