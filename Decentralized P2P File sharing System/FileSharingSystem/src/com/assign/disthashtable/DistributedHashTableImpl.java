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
	int numOfServers;
	int replicationFactor;
	List<String> serverIPs;
	List<Integer> serverPorts;
	List<Integer> downloaderPorts;
	int replicaCount;

	public DistributedHashTableImpl(List<String> serverIPs, List<Integer> serverPorts, int numOfServers, int replicationFactor, List<Integer> downloaderPorts){
		sockets = new ArrayList<Socket>();
		objOutStreams = new ArrayList<ObjectOutputStream>();
		objInStreams = new ArrayList<ObjectInputStream>();
		this.numOfServers = numOfServers;
		this.replicationFactor = replicationFactor;
		this.serverIPs = serverIPs;
		this.serverPorts = serverPorts;
		this.downloaderPorts = downloaderPorts;
		
		for (int i = 0; i < numOfServers; i++){
			try {
				sockets.add(new Socket(serverIPs.get(i), serverPorts.get(i)));
				objOutStreams.add(new ObjectOutputStream(sockets.get(i).getOutputStream()));
				objInStreams.add(new ObjectInputStream(sockets.get(i).getInputStream()));
				
			} catch (IOException e) {
				System.out.println("One or more servers are down...Please ensure all the servers are up and running!!!");
				//e.printStackTrace();
			}
		}
	}
	
	
	public boolean put (String key, String value){
		int server = getHash(key);
		boolean res = false;
		int index = 0;
		
		index = server;
		for (int i = 0; i < replicationFactor; i++){
			index = (index + 1) % numOfServers;
			value = value + "#" + serverIPs.get(index) + "|" + downloaderPorts.get(index); 
		}
		String req = "0|"+ "|"+key +"|"+ value;
		String replicationReq = "3|"+ "|"+key +"|"+ value;
		res = (boolean)sendRequest(req, server);
		
		//Replicate on replicas
		for (int i = 0; i < replicationFactor; i++){
			server = (server + 1) % numOfServers;
			sendRequest(replicationReq, server);
		}
		return res;
	}
	
	public String get (String key){
		String value = null;
		int server = getHash(key);
		
		String req = "1|"+ "|"+key;
		
		value = (String)sendRequest(req, server);
		
		return value;
	}
	
	public boolean delete (String key){
		int server = getHash(key);
		boolean res = false;
		
		String req = "2|"+ "|"+key;
		res = (boolean)sendRequest(req, server);
		
		return res;
	}
	
	private Object sendRequest(String req, int server) {
		Object res = null;
		
		try {
			//send request to the server
			objOutStreams.get(server).writeObject(req);
			objOutStreams.get(server).flush();
			
			//get response from server
			res = objInStreams.get(server).readObject();
			replicaCount = 0;
			
		} catch (IOException e) {
			System.out.println("Server down!! calling replica");
			
			server = (server + 1) % numOfServers;
			replicaCount++;
			
			if (replicaCount<=replicationFactor){
				return sendRequest(req, server);
			}else{
				System.out.println("Primary server and all the replicas are down!!");
			}
			
			//e.printStackTrace();
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
		return sum % numOfServers;
	}
}
