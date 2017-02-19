package com.assign.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.assign.dto.RegisterDTO;

/**
 * @author Nikhil
 *
 */
public class HandleClient implements Runnable {
	
	Socket client;
	Map<String, ArrayList<String>> map;
	static Map<String, ArrayList<String>> fileIndex;
	
	/**
	 * Parameterized constructor
	 * @param client
	 */
	public HandleClient(Socket client) {
		
		if (null == fileIndex)
			fileIndex = new ConcurrentHashMap<String, ArrayList<String>>();
		this.client = client;
	}

	/**
	 * Reads request from client and identifies if the request is file register request or search file request
	 */
	@Override
	public void run() {
		try {
			//Read client request and distinguish
			ObjectInputStream inFromClient = new ObjectInputStream(client.getInputStream());
			Object obj = inFromClient.readObject();
			
			if(obj instanceof RegisterDTO){
				RegisterDTO regDTO = (RegisterDTO)obj;
				registerFiles(regDTO);
			}else if(obj instanceof String){
				String fileName = (String)obj;
				searchFile(fileName);
			}else{
				System.out.println("Invalid request from client..\n");
			}
				
		} catch (ClassNotFoundException e) {
			System.out.println("Data type from client not supported");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading data from client");
			//e.printStackTrace();
		} finally{
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Registers file on the indexing server
	 * @param regDTO
	 */
	private void registerFiles(RegisterDTO regDTO) {
		System.out.println("\nFile register request from :" + regDTO.getPeerId());
		System.out.println("Number of files registered: " + regDTO.getNoOfFiles());
		ArrayList<String> peerList;
		
		for(int i = 0; i< regDTO.getNoOfFiles(); i++){
			String fileName = regDTO.getFileList().get(i);
			if (fileIndex.containsKey(fileName)) {
				peerList = fileIndex.get(fileName);
				peerList.add(regDTO.getPeerId()+"|"+regDTO.getPortNo());
			}else{
				peerList = new ArrayList<String>();
				peerList.add(regDTO.getPeerId()+"|"+regDTO.getPortNo());
				fileIndex.put(fileName, peerList);
			}
		}
	}

	/**
	 * Searches given filename
	 * @param fileName
	 */
	private void searchFile(String fileName) {
		System.out.println("\nFile search request");
		if (null == fileIndex){
			System.out.println("There are no files registered on the server");
		}else{
			ArrayList<String> peerList = fileIndex.get(fileName);
			ObjectOutputStream objOutStream;
			try {
				
				objOutStream = new ObjectOutputStream(client.getOutputStream());
				if (null != peerList){
					System.out.println("File found..");
					objOutStream.writeObject(peerList);
					objOutStream.flush();
					
				}else{
					objOutStream.writeObject("NotFound");
					objOutStream.flush();
					System.out.println("File not found\n");	
				}
				//objOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}