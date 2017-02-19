package com.assign.perftest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;


public class CouchDBClient {

	int numOfServers;
	ArrayList<Session> sessions;
	ArrayList<Database> databases;

	public CouchDBClient(List<String> serverIPs, int numOfServers){
		this.numOfServers = numOfServers;
		
		sessions = new ArrayList<Session>();
		databases = new ArrayList<Database>(); 
		
		for(int i=0; i< numOfServers; i++){
			sessions.add(new Session(serverIPs.get(i), 5984));
			sessions.get(i).createDatabase("mydb");
			databases.add(sessions.get(i).getDatabase("mydb"));
		}
	}
	
	public void put (String key, String value){
		int host = getHash(key);
		Document doc = new Document();
		doc.setId(key);
		doc.put("value", value);
		databases.get(host).saveDocument(doc);
	}
	
	public String get (String key){
		int host = getHash(key);
		Document doc = databases.get(host).getDocument(key);
		return ((String)doc.get("value"));
	}
	
	public void remove (String key){
		int host = getHash(key);
		Document doc = databases.get(host).getDocument(key);
		databases.get(host).deleteDocument(doc);
	}

	public void deleteDB(){
		for (int i = 0; i < numOfServers; i++){
			sessions.get(i).deleteDatabase("mydb");
		}
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

