/**
 * 
 */
package com.assign.perftest;

import java.io.FileInputStream; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	//static final int NUM_OF_SERVERS = 8;
	private static Properties prop;
	int numOfServers;
	static int numOfReqs;
	
	Logger logger;
	FileHandler fileHandler;
	SimpleFormatter simpleFormatter;
	
	public DistributedHashTablePerfTest () throws SecurityException, IOException{
		prop = new Properties();
		logger = Logger.getLogger("PerfTestLog");
		fileHandler = new FileHandler("perfLog.log");
		logger.addHandler(fileHandler);
		simpleFormatter = new SimpleFormatter();
		fileHandler.setFormatter(simpleFormatter);
		
		serverIPs = new ArrayList<String>();
		serverPorts = new ArrayList<Integer>();
		
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.numOfServers = Integer.parseInt(prop.getProperty("number.of.servers"));
		this.numOfReqs = Integer.parseInt(prop.getProperty("no.requests"));
		
		for(int i = 0; i < numOfServers; i++ ){
			serverIPs.add(prop.getProperty("server.ip." + i));
			serverPorts.add(Integer.parseInt(prop.getProperty("server.port." + i)));
		}
		
		distributedHashTable = new DistributedHashTableImpl(serverIPs, serverPorts, numOfServers);
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		DistributedHashTablePerfTest test = new DistributedHashTablePerfTest();
		
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		//Random key-value generation
		int minimum = 1000;
		Random rn = new Random();
		for (int i = 0; i < numOfReqs; i++){
			int j = rn.nextInt() % 1000;
			keys.add("Key"+ (minimum + j) + (i%100));
			values.add(UUID.randomUUID().toString()+UUID.randomUUID().toString());
		}
		
		test.testPut(keys, values);
		test.testGet(keys);
		test.testDelete(keys);
		
	}

	private void testPut(ArrayList<String> keys, ArrayList<String> values) {
		logger.info("Put test started\n");
		logger.info("Wait for the result...\n");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			distributedHashTable.put(keys.get(i), values.get(i));
		}
		long endTime = System.currentTimeMillis();
		logger.info("Time taken for "+numOfReqs+" puts : " + (endTime - startTime) + " ms\n");
		
	}

	private void testGet(ArrayList<String> keys) {
		logger.info("Get test started\n");
		logger.info("Wait for the result...\n");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			distributedHashTable.get(keys.get(i));
		}
		long endTime = System.currentTimeMillis();
		logger.info("Time taken for "+numOfReqs+" get requests : " + (endTime - startTime) + " ms\n");
		
	}

	private void testDelete(ArrayList<String> keys) {
		logger.info("Get test started\n");
		logger.info("Wait for the result...\n");
		long startTime = System.currentTimeMillis();
		for (int i=0; i< numOfReqs; i++){
			distributedHashTable.delete(keys.get(i));
		}
		long endTime = System.currentTimeMillis();
		logger.info("Time taken for "+numOfReqs+" delete requests : " + (endTime - startTime) + " ms\n");
		
	}
}
