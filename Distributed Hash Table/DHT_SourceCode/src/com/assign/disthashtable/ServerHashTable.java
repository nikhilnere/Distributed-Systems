/**
 * 
 */
package com.assign.disthashtable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nikhil
 *
 */
public class ServerHashTable implements Runnable{

	Socket client;
	static Map<String, String> distHashTable = new ConcurrentHashMap<String, String>();;
	
	public ServerHashTable(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try{
			ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(client.getOutputStream());
			while(true){
				Object obj = inFromClient.readObject();
				if(obj instanceof String){
					String request = (String) obj;
					
					int index = request.indexOf('|');
					String reqType = request.substring(0, index);
					request = request.substring(index+2, request.length());
					
					if ("0".equals(reqType)){
						outToClient.writeObject(put(request));
						outToClient.flush();
					}else if ("1".equals(reqType)){
						outToClient.writeObject(get(request));
						outToClient.flush();
					}else if ("2".equals(reqType)){
						outToClient.writeObject(delete(request));
						outToClient.flush();
					}
				}else{
					System.out.println("Invalid request from client..\n");
				}
			}
		}catch (ClassNotFoundException e) {
			System.out.println("Data type from client not supported");
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} 
		
	}

	private boolean put(String request) {
		// TODO Auto-generated method stub
		int index = request.indexOf('|');
		String key = request.substring(0, index);
		String value = request.substring(index+1, request.length());
		//System.out.println(key);
		distHashTable.put(key, value);
		return true;
	}
	
	private String get(String key) {
		// TODO Auto-generated method stub
		return distHashTable.get(key);
	}
	
	private boolean delete(String key) {
		distHashTable.remove(key);
		return true;
	}

	

	

}
