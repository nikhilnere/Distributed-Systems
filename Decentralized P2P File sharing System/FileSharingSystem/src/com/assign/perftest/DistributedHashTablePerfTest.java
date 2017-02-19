/**
 * 
 */
package com.assign.perftest;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.assign.disthashtable.DistributedHashTable;
import com.assign.disthashtable.DistributedHashTableImpl;
import com.assign.disthashtable.FileReceiver;

/**
 * @author Nikhil
 *
 */
public class DistributedHashTablePerfTest {
	
	static DistributedHashTable distributedHashTable;
	List<String> serverIPs;
	List<Integer> serverPorts;
	List<Integer> downloaderPorts;
	static final int NUM_OF_SERVERS = 8;
	static final int replicationFactor = 0;
	private static Properties prop;
	int senderPort;
	String fileDwnldDir;
	
	public DistributedHashTablePerfTest (){
		serverIPs = new ArrayList<String>();
		serverPorts = new ArrayList<Integer>();
		downloaderPorts = new ArrayList<Integer>();
		
		prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < NUM_OF_SERVERS; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
			serverPorts.add(Integer.parseInt(prop.getProperty("server.port." + i)));
			downloaderPorts.add(Integer.parseInt(prop.getProperty("downloader.port." + i)));
		}
		this.senderPort = Integer.parseInt(prop.getProperty("sender.port"));
		this.fileDwnldDir = prop.getProperty("peer.download.fileDir");
		distributedHashTable = new DistributedHashTableImpl(serverIPs, serverPorts,NUM_OF_SERVERS, replicationFactor, downloaderPorts);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DistributedHashTablePerfTest test = new DistributedHashTablePerfTest();
		test.testRegister(10000);
		test.testSearch(10000);
		//test.testDownload(10000);
		
	}

	private void testRegister(int numOfReqs) {
		System.out.println("File Register test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			String key = "filea" + i + ".txt";
			String value = getIPAddress() + "|" + senderPort;;
			distributedHashTable.put(key, value);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" puts : " + (startTime - endTime) + "ms");
		
	}

	private void testSearch(int numOfReqs) {
		System.out.println("File search test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			String key = "filea" + i + ".txt";
			distributedHashTable.get(key);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" get requests : " + (startTime - endTime) + "ms");
		
	}

	private void testDownload(int numOfReqs) {
		System.out.println("File download test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		FileReceiver fileReceiver = new FileReceiver();
		for (int i=0; i< numOfReqs; i++){
			String fileName = "filea" + i + ".txt";
			fileReceiver.receiveFile("192.168.1.136|9981", fileName, fileDwnldDir);
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" delete requests : " + (startTime - endTime) + "ms");
		
	}
	
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
