/**
 * 
 */
package com.assign.disthashtable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikhil
 *
 */
public class DistributedHashTableImpl implements DistributedHashTable{

	static List<Socket> sockets;
	static List<ObjectOutputStream> objOutStreams;
	static List<ObjectInputStream> objInStreams;
	static final int NUM_OF_SERVERS = 8;

	public DistributedHashTableImpl(List<String> serverIPs, List<Integer> serverPorts){
		sockets = new ArrayList<Socket>();
		objOutStreams = new ArrayList<ObjectOutputStream>();
		objInStreams = new ArrayList<ObjectInputStream>();
		
		for (int i = 0; i < NUM_OF_SERVERS; i++){
			try {
				sockets.add(new Socket(serverIPs.get(i), serverPorts.get(i)));
				objOutStreams.add(new ObjectOutputStream(sockets.get(i).getOutputStream()));
				objInStreams.add(new ObjectInputStream(sockets.get(i).getInputStream()));
				
			} catch (IOException e) {
				System.out.println("One or more servers are down...Please ensure all the servers are up and running!!!");
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean put (String key, String value){
		int server = getHash(key);
		boolean res = false;
		try {
			
			String req = "0|"+ "|"+key +"|"+ value;
			
			//send request to the server
			objOutStreams.get(server).writeObject(req);
			objOutStreams.get(server).flush();
			
			//get response from server
			res = (boolean)objInStreams.get(server).readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public String get (String key){
		String value = null;
		int server = getHash(key);
		try {
			
			String req = "1|"+ "|"+key;
			
			//send request to the server
			objOutStreams.get(server).writeObject(req);
			objOutStreams.get(server).flush();
			
			//get response from server
			value = (String)objInStreams.get(server).readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public boolean delete (String key){
		int server = getHash(key);
		boolean res = false; 
		try {
			
			String req = "2|"+ "|"+key;
			
			//send request to the server
			objOutStreams.get(server).writeObject(req);
			objOutStreams.get(server).flush();
			
			//get response from server
			res = (boolean)objInStreams.get(server).readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getHash(String key) {
		char ch[];
		ch = key.toCharArray();
		
		int sum = 0;
		
		for (int i = 0; i < key.length(); i++){
			sum += ch[i];
		}
		return sum % NUM_OF_SERVERS;
	}
}
