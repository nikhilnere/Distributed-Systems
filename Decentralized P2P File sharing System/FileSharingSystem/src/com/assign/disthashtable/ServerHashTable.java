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
	String fileDir;
	
	public ServerHashTable(Socket client, String fileDir) {
		this.client = client;
		this.fileDir = fileDir;
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
					}else if ("3".equals(reqType)){
						outToClient.writeObject(replicateReq(request));
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
		int index = request.indexOf('|');
		String key = request.substring(0, index);
		String value = request.substring(index+1, request.length());
		String peerList;
		if (distHashTable.containsKey(key)){
			peerList = distHashTable.get(key);
			String peerStr[] = value.split("#");
			peerList = peerList + "#" + peerStr[0]; //new request without replica addresses as they would be already added since the same file is already present
			distHashTable.put(key, peerList);
		}else{
			distHashTable.put(key, value);
		}
		return true;
	}
	
	private String get(String key) {
		String peerList = distHashTable.get(key);
		return peerList;
	}
	
	private boolean delete(String key) {
		distHashTable.remove(key);
		return true;
	}

	private Object replicateReq(String request) {
		//register the file 
		System.out.println("\n*********Replication request*********");
		System.out.println("Replica of the file will be downloaded on this server");
		System.out.println("You will recieve message when the file gets downloaded.");
		System.out.println("You may proceed with the menus above");
		
		
		int index = request.indexOf('|');
		String key = request.substring(0, index);
		String value = request.substring(index+1, request.length());
		
		if (distHashTable.containsKey(key)){
			System.out.println("File already replicated");
			
			String peerStr[] = value.split("#");
			String newRequest = key + "|" + peerStr[0]; // new request without replica addresses as they would be already added since the same file is already present
			put(newRequest);
			
		}else{
			put(request);
			//make a replica of the file i.e. download it form the source of the file
			String peerStr[] = value.split("#");
			
			//fileDir is the file share folder. File will be downloaded in the share so that it will be available for others to download
			//Note : here we are not downloading the file in downloads folder
			
			Thread replicator = new Thread(new FileReplicator(peerStr[0], key, fileDir));
			replicator.start();
		}
		return true;
	}

	

}
