package com.assign.disthashtable;

import java.io.FileInputStream; 
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
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
	List<Integer> downloaderPorts;
	int numOfServers;
	int replicationFactor;
	private String fileDir;
	private String fileDwnldDir;
	int senderPort;

	public Client() throws IOException {
		
		serverIPs = new ArrayList<String>();
		serverPorts = new ArrayList<Integer>();
		downloaderPorts = new ArrayList<Integer>();
		
		prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		
		numOfServers = Integer.parseInt(prop.getProperty("number.of.servers"));
		replicationFactor = Integer.parseInt(prop.getProperty("replication.factor"));
		
		for(int i = 0; i < numOfServers; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
			serverPorts.add(Integer.parseInt(prop.getProperty("server.port." + i)));
			downloaderPorts.add(Integer.parseInt(prop.getProperty("downloader.port." + i)));
		}
		
		this.clientPort = Integer.parseInt(prop.getProperty("client.port"));
		this.senderPort = Integer.parseInt(prop.getProperty("sender.port"));
		this.fileDir = prop.getProperty("peer.share.fileDir");
		this.fileDwnldDir = prop.getProperty("peer.download.fileDir");
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
		int count = 0;
		
		System.out.println("Starting server at " + clientPort);
		Thread server = new Thread(new Server(clientPort, fileDir));
		server.start();
		
		Thread fileSender = new Thread(new FilePeerServer(senderPort, fileDir));
		fileSender.start();
		
		//Options to the user
		while(true){
			System.out.println("\n*****************************");
			System.out.println("*  Distributed Hash Table *");
			System.out.println("*****************************");
			System.out.println("1. Register file on server");
			System.out.println("2. Search and download file");
			//System.out.println("3. Delete ");
			System.out.println("3. Shutdown Client");
			System.out.println("Enter your choice : ");
			String userChoice = scan.next();
			
			if(null == distributedHashTable){
				distributedHashTable = new DistributedHashTableImpl(serverIPs, serverPorts, numOfServers, replicationFactor, downloaderPorts);
			}
			
			switch (userChoice){
				case "1": 
					System.out.println("\nEnter number of files to register :");
					try{
						int noOfFiles = scan.nextInt();
						value = getIPAddress() + "|" + senderPort;
						System.out.println("\nEnter file Name(s) :");
						for (int i = 0; i < noOfFiles ; i++){
							distributedHashTable.put(scan.next(), value);
						}
						System.out.println("File(s) registered on the distributed hash table");
						
					}catch (InputMismatchException e){
						System.out.println("\nInvalid number of files\n");
					}
					break;
					
				case "2": 
					
					System.out.println("\nEnter name of the file to search :");
					String fileName = scan.next();
					
					value = distributedHashTable.get(fileName);
					
					if (null == value){
						System.out.println("File Not Found");
						break;
						
					}
					
					ArrayList<String> peerList = new ArrayList<String>();
					String peerStr[] = value.split("#");
					
					for(int i = 0; i < peerStr.length; i++){
						peerList.add(peerStr[i]);
					}
					 
					if (null != peerList){
					
						System.out.println("\nFile found at these peers :");
						for (String peerId : peerList){
							System.out.println("Peer "+count+"  -  "+peerId);
							count++;
						}
						count = 0;
						System.out.println("\nDo you wish to download the file? (Y/N)");
						if("Y".equalsIgnoreCase(scan.next())){
							
							System.out.println("\nEnter peer number to download the file from : ");
							try{
								int index = scan.nextInt();
								if (index < peerList.size()){
									String peerId = peerList.get(index);
									System.out.println("\nConnecting to peer ("+peerId+") to download the file");
									//Receive file
									FileReceiver fileReceiver = new FileReceiver();
									fileReceiver.receiveFile(peerId, fileName, fileDwnldDir);
								}else{
									throw new InputMismatchException();
								}
							}catch(InputMismatchException e){
								System.out.println("Invalid peer number");
							}
						}
					}
					break;
					
				/*case "3":
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
						
					break;*/
					
				case "3":
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
	
	/**
	 * Returns IP address of the system
	 * @return
	 * @throws SocketException
	 */
	private String getIPAddress() {
		Enumeration<NetworkInterface> en;
		try {
			en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()){
				NetworkInterface nt = (NetworkInterface) en.nextElement();
				for (Enumeration<InetAddress> en2 = nt.getInetAddresses(); en2.hasMoreElements();){
					InetAddress addr = (InetAddress) en2.nextElement();
					if (!addr.isLoopbackAddress()){
						if (addr instanceof Inet4Address){
							return addr.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		return null;
	}
}
