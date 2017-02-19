package com.assign.perftest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Session;

public class DeleteCouchDB {
	List<String> serverIPs;

	private static Properties prop;
	int numOfServers;
	
	FileHandler fileHandler;
	SimpleFormatter simpleFormatter;
	ArrayList<Session> sessions;
	ArrayList<Database> databases;

	
	public DeleteCouchDB() throws SecurityException, IOException{
		prop = new Properties();
		
		serverIPs = new ArrayList<String>();
		
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.numOfServers = Integer.parseInt(prop.getProperty("number.of.servers"));
		
		for(int i = 0; i < numOfServers; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
		}
		
		sessions = new ArrayList<Session>();
		databases = new ArrayList<Database>(); 
		
		for(int i=0; i< numOfServers; i++){
			sessions.add(new Session(serverIPs.get(i), 5984));
			databases.add(sessions.get(i).getDatabase("mydb"));
		}
		
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		
		DeleteCouchDB test = new DeleteCouchDB();
		test.deleteDB();

	}
	
	private void deleteDB() {
		for (int i = 0; i < numOfServers; i++){
			sessions.get(i).deleteDatabase("mydb");
		}
	}

}
