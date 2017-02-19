package com.assign.disthashtable;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Nikhil
 *
 */
public class Client {
	private int clientPort;
	private static Properties prop;
	Socket socketClient;
	DistributedHashTable distributedHashTable;
	List<String> serverIPs;
	List<Integer> serverPorts;
	//static final int NUM_OF_SERVERS = 8;
	int numOfServers;

	public Client() throws IOException {
		
		serverIPs = new ArrayList<String>();
		serverPorts = new ArrayList<Integer>();
		
		prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		
		this.numOfServers = Integer.parseInt(prop.getProperty("number.of.servers"));
		
		for(int i = 0; i < numOfServers; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
			serverPorts.add(Integer.parseInt(prop.getProperty("server.port." + i)));
		}
		
		this.clientPort = Integer.parseInt(prop.getProperty("client.port"));
		
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.clientMenus();;
	}

	/**
	 * Provides an interface
	 */
	private void clientMenus() {
		Scanner scan = new Scanner(System.in);
		String key, value;
		
		System.out.println("Starting server at " + clientPort);
		Thread server = new Thread(new Server(clientPort));
		server.start();
		
		//Options to the user
		while(true){
			System.out.println("\n*****************************");
			System.out.println("*  Distributed Hash Table *");
			System.out.println("*****************************");
			System.out.println("1. Put");
			System.out.println("2. Get");
			System.out.println("3. Delete");
			System.out.println("4. Shutdown Client");
			System.out.println("Enter your choice : ");
			String userChoice = scan.next();
			
			if(null == distributedHashTable){
				distributedHashTable = new DistributedHashTableImpl(serverIPs, serverPorts, numOfServers);
			}
			
			switch (userChoice){
				case "1": 
					System.out.println("Enter Key : ");
					key = scan.next();
					
					if (key.length() > 24){
						System.out.println("\nInvalid key...The length of the key should not be greater than 24");
						break;
					}
					
					System.out.println("Enter Value : ");
					value = scan.next();
					System.out.println("Key: " + key + ", Value" + value);
					if (distributedHashTable.put(key, value)){
						System.out.println("\nkey-value pair succefully stored on the server !!");
					}else{
						System.out.println("\nSomething went wrong...please try putting again");
					}
					break;
					
				case "2": 
					
					System.out.println("Enter Key : ");
					key = scan.next();
					if (key.length() > 24){
						System.out.println("\nInvalid key...The length of the key should not be greater than 24");
						break;
					}
					value = distributedHashTable.get(key);
					System.out.println("\nThe value retrived from the server : " + value);
					
					break;
					
				case "3":
					System.out.println("Enter Key : ");
					key = scan.next();
					if (key.length() > 24){
						System.out.println("\nInvalid key...The length of the key should not be greater than 24");
						break;
					}
					if (distributedHashTable.delete(key)){
						System.out.println("\nSuccefully deleted !!");
					}else{
						System.out.println("\nSomething went wrong...please try deleting again");
					}
						
					break;
					
				case "4":
					System.out.println("\nWith this is action the client will stop listening as a server.");
					System.out.println("Do you still want to exit? (Y/N)");
					if ("Y".equalsIgnoreCase(scan.next())){
						scan.close();
						System.exit(0);
					}
					break;
					
				default : 
					System.out.println("\nInvalid choice...try again");
			}
		}
	}
}
