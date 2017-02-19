package com.assign3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;


public class SchedulerClient implements Runnable{

	String fileName;
	Queue<String> jobQueue;
	
	public SchedulerClient(String filename, Queue<String> jobQueue) {
		this.fileName = filename;
		this.jobQueue = jobQueue;
	}
	
	@Override
	public void run() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			int taskCounter = 0;
			while (line != null){
				jobQueue.add(taskCounter + " " + line);
				taskCounter++;
				line = reader.readLine();
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
