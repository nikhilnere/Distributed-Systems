/**
 * 
 */
package com.assign.perftest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.assign.disthashtable.DistributedHashTable;
import com.assign.disthashtable.DistributedHashTableImpl;

/**
 * @author Nikhil
 *
 */
public class DistributedHashTablePerfTest {
	
	static DistributedHashTable distributedHashTable;
	List<String> serverIPs;
	List<Integer> serverPorts;
	static final int NUM_OF_SERVERS = 8;
	private static Properties prop;
	
	public DistributedHashTablePerfTest (){
		serverIPs = new ArrayList<String>();
		serverPorts = new ArrayList<Integer>();
		
		prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < NUM_OF_SERVERS; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
			serverPorts.add(Integer.parseInt(prop.getProperty("server.port." + i)));
		}
		
		distributedHashTable = new DistributedHashTableImpl(serverIPs, serverPorts);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DistributedHashTablePerfTest test = new DistributedHashTablePerfTest();
		test.testPut(100000);
		test.testGet(100000);
		test.testDelete(100000);
		
	}

	private void testPut(int numOfReqs) {
		System.out.println("Put test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			String key = "k" + i;
			String value = "Value" + i;
			distributedHashTable.put(key, value);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" puts : " + (endTime - startTime) + "ms");
		
	}

	private void testGet(int numOfReqs) {
		System.out.println("Get test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			String key = "k" + i;
			distributedHashTable.get(key);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" get requests : " + (endTime - startTime) + "ms");
		
	}

	private void testDelete(int numOfReqs) {
		System.out.println("Get test started");
		System.out.println("Wait for the result...");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			String key = "k" + i;
			distributedHashTable.delete(key);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for "+numOfReqs+" delete requests : " + (endTime - startTime) + "ms");
		
	}
}
