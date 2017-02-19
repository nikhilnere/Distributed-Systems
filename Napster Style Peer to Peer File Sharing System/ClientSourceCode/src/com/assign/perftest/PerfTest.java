/**
 * 
 */
package com.assign.perftest;

import java.io.IOException; 
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;

import com.assign.client.ClientFileSystem;
import com.assign.client.FileReceiver;
import com.assign.client.SocketClient;
import com.assign.dto.RegisterDTO;

/**
 * @author Nikhil
 *
 */
public class PerfTest {

	/**
	 * @param args
	 */
	
	static int noOfReq= 1000;
	SocketClient client;
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		PerfTest test = new PerfTest();
		test.testRegisterFiles();
		test.testSearchFile();
		test.testFileDownload();

	}

	private void testRegisterFiles() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		long startTime = System.currentTimeMillis();
		String fileName;
		RegisterDTO regDTO;
		ClientFileSystem clientFileSystem;

		System.out.println("\nSending 1000 register requests");
		System.out.println("Test started at : " + startTime);
		System.out.println("Wait for the requests to execute....");
		for (int i = 0; i < noOfReq; i++){
			regDTO = new RegisterDTO();
			fileName = "File"+i;
			//System.out.println("FileName sent : "+ fileName);
			regDTO.setNoOfFiles(1);
			regDTO.setPeerId(InetAddress.getLocalHost().getHostAddress());
			regDTO.getFileList().add(fileName);
		
			clientFileSystem = new ClientFileSystem();
			clientFileSystem.registerFile("192.168.1.144", 9999, regDTO);
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Test Ended at : " + endTime);
		System.out.println("Total time taken to register 1000 files : " + (endTime - startTime) + "ms");
		System.out.println("Test Done!!");
		System.out.println("---------------------------------------");
	}

	private void testSearchFile() {

		long startTime = System.currentTimeMillis();
		String fileName;
		RegisterDTO regDTO;
		ClientFileSystem clientFileSystem;
		System.out.println("\nSending 1000 search requests");
		System.out.println("Test started at : " + startTime);
		System.out.println("Wait for the requests to execute....");
		for (int i = 0; i < noOfReq; i++){
			fileName = "File"+i;
			//System.out.println("FileName sent to search: "+ fileName);
			clientFileSystem = new ClientFileSystem();
			ArrayList<String> peerList = clientFileSystem.searchFile("192.168.1.144", 9999, fileName);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Test Ended at : " + endTime);
		System.out.println("Total time taken to search 1000 files : " + (endTime - startTime) + "ms");
		System.out.println("Test Done!!");
		System.out.println("---------------------------------------");
	}
	
	private void testFileDownload(){

		long startTime = System.currentTimeMillis();
		System.out.println("\nSending request to download a 69.2MB file");
		System.out.println("Test started at : " + startTime);
		System.out.println("Wait for the file to download....");
		FileReceiver fileReceiver = new FileReceiver();
		fileReceiver.receiveFile("192.168.1.144|9990", "vd.avi", "C:\\Users\\Nikhil\\Desktop\\peer1\\downloads\\");
		long endTime = System.currentTimeMillis();
		System.out.println("Test Ended at : " + endTime);
		System.out.println("Total time taken to donload a 69.2MB file: " + (endTime - startTime) + "ms");
		System.out.println("Test Done!!");
		System.out.println("---------------------------------------");
	}
	
}
