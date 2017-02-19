/**
 * 
 */
package com.assign.client;

import java.io.FileInputStream; 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import com.assign.dto.RegisterDTO;

/**
 * @author Nikhil
 *
 */
public class SocketClient {

	private String serverHostname;
	private int serverPort;
	private String fileDir;
	private String fileDwnldDir;
	private int peerPort;
	private static Properties prop;
	Socket socketClient;

	public SocketClient() throws IOException {
		prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		this.serverHostname = prop.getProperty("server.ipAddress");
		this.serverPort = Integer.parseInt(prop.getProperty("server.port"));
		this.peerPort = Integer.parseInt(prop.getProperty("peer.port"));
		this.fileDir = prop.getProperty("peer.share.fileDir");
		this.fileDwnldDir = prop.getProperty("peer.download.fileDir");
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		SocketClient client = new SocketClient();
		client.clientMenus();;
	}

	/**
	 * Provides an interface
	 */
	private void clientMenus() {
		Scanner scan = new Scanner(System.in);
		ClientFileSystem clientFileSystem = new ClientFileSystem();
		int count = 0;
		//Start the file sharing first
		Thread fileSender = new Thread(new FilePeerServer(peerPort, fileDir));
		fileSender.start();
		
		//Options to the user

		
		while(true){
			System.out.println("*****************************");
			System.out.println("*  Distributed File System  *");
			System.out.println("*****************************");
			System.out.println("1. Register file on server");
			System.out.println("2. Search and download file");
			System.out.println("3. Shutdown Client");
			System.out.println("Enter your choice : ");
			String userChoice = scan.next();
			
			switch (userChoice){
				case "1": 
					System.out.println("\nEnter number of files to register :");
					try{
						int noOfFiles = scan.nextInt();
						RegisterDTO regDTO = new RegisterDTO();
						regDTO.setNoOfFiles(noOfFiles);
						regDTO.setPeerId(getIPAddress());
						//regDTO.setPeerId(InetAddress.getLocalHost().getHostAddress());
						regDTO.setPortNo(prop.getProperty("peer.port"));
						System.out.println("\nEnter file Names :");
						for (int i = 0; i < noOfFiles ; i++){
							 regDTO.getFileList().add(scan.next());
						}
						System.out.println("\nConnecting to the server at IP:"+ serverHostname + ", Port:" + serverPort);
						clientFileSystem.registerFile(serverHostname, serverPort, regDTO);
						System.out.println("\nRequest sent to the server to register files.. \n");
					}catch (InputMismatchException e){
						System.out.println("\nInvalid number of files\n");
					}
					
					
					break;
					
				case "2": 
					System.out.println("\nEnter name of the file to search :");
					String fileName = scan.next();
					clientFileSystem = new ClientFileSystem();
					ArrayList<String> peerList = clientFileSystem.searchFile(serverHostname, serverPort, fileName);
					if (null != peerList){
					
						System.out.println("\nFile found at these peers :");
						for (String peerId : peerList){
							System.out.println("Peer "+count+"  -  "+peerId);
							count++;
						}
						count = 0;
						System.out.println("\nDo you wish to download the file? (Y/N)");
						if("Y".equalsIgnoreCase(scan.next())){						
							String peerId = peerList.get(0);
							System.out.println("\nConnecting to the first peer ("+peerId+") to download the file");
							//Receive file
							FileReceiver fileReceiver = new FileReceiver();
							fileReceiver.receiveFile(peerId, fileName, fileDwnldDir);
						}
					}
					break;
					
				case "3":
					System.out.println("With this is action the files registered by you will not be available to other users to download.");
					System.out.println("Do you still want to exit? (Y/N)");
					if ("Y".equalsIgnoreCase(scan.next())){
						scan.close();
						System.exit(0);
					}
					break;
					
				default : 
					System.out.println("Invalid choice...try again");
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
