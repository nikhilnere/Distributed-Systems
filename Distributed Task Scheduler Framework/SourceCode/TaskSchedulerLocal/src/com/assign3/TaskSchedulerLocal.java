package com.assign3;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class TaskSchedulerLocal {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int noOfThreads = Integer.parseInt(args[0]);
		String fileName = args[1];
		Queue<String> jobQueue = new ConcurrentLinkedQueue<String>();
		Queue<String> resultQueue = new ConcurrentLinkedQueue<String>();
		
		
		
		long startTime = System.currentTimeMillis();
		
		//Start client
		Thread client = new Thread(new SchedulerClient(fileName, jobQueue));
		client.start();
		client.join();
		int resultCount = 0;
		int totalTask = jobQueue.size();

		//Start Workers
		for (int i = 0; i < noOfThreads; i++){
			Thread taskWorker = new Thread(new TaskWorker(jobQueue, resultQueue));
			taskWorker.start();
		}
		
		String result = null;
		
		while (resultCount < totalTask){
			//System.out.println("waiting ");
			if (!resultQueue.isEmpty()){
				result = resultQueue.poll();
				resultCount++;
				//System.out.println("Task" + result);
			}
		}
		System.out.println("Time Taken with "+ noOfThreads +" workers: " + (System.currentTimeMillis() - startTime) + " ms");
	}
}
