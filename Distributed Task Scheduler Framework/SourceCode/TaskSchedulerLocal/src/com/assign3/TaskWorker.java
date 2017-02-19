package com.assign3;
import java.util.Queue;


public class TaskWorker implements Runnable{

	Queue<String> jobQueue;
	Queue<String> resultQueue;
	
	public TaskWorker(Queue<String> jobQueue, Queue<String> resultQueue) {
		this.jobQueue = jobQueue;
		this.resultQueue = resultQueue;
	}

	@Override
	public void run() {
		String task = null;
		while (!jobQueue.isEmpty()){
			task = jobQueue.poll();
			if (task!=null){
				String[] tokens = task.split(" ");
				long sleepTime = Long.parseLong(tokens[2]);
				try {
					//Execute Task
					Thread.sleep(sleepTime);
					resultQueue.add(tokens[0]+" 0"); //0 for success
					
				} catch (InterruptedException e) {
					resultQueue.add(tokens[0]+" 1"); //1 for failure
				}
			}
		}
	}
}
