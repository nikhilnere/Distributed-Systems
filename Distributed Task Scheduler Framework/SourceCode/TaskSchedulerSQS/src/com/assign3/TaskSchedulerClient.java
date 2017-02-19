package com.assign3;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class TaskSchedulerClient {

	/**
	 * @param args
	 */
	
	AWSSimpleQueueService sqs;
	private Properties prop;
	private int noOfTask;
	private String inputSQSUrl;
	private String outputSQSUrl;
	AmazonDynamoDBTable table;
	
	public TaskSchedulerClient(String inputQ, String responseQ) throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("taskSchedulerProp.properties"));
		String accessKey = prop.getProperty("access.key");
		String secretKey = prop.getProperty("secret.key");
		
		sqs = new AWSSimpleQueueService(accessKey, secretKey);
		inputSQSUrl = sqs.getSQSUrl(inputQ);
		outputSQSUrl = sqs.getSQSUrl(responseQ);
		noOfTask = 0;
		table = new AmazonDynamoDBTable (accessKey, secretKey, "intermediateTable");
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String fileName = args[2];
		TaskSchedulerClient taskSchedulerClient = new TaskSchedulerClient(args[0], args[1]);
		long startTime = System.currentTimeMillis();
		//Submit tasks to queue
		taskSchedulerClient.submitTasks(fileName);
		
		//Read task responses
		taskSchedulerClient.readResponseQ();
		System.out.println("Total time taken : " + (System.currentTimeMillis() - startTime) + "ms");
	}
	
	/*
	 * Inserts tasks from workload in the task queue 
	 */
	public void submitTasks(String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		while (line != null){
			sqs.add(noOfTask + " " + line, inputSQSUrl);
			noOfTask++;
			line = reader.readLine();
		}
		reader.close();
	}

	//Reads responses from the response queue
	public void readResponseQ(){
		String msg = null;
		String taskId = null;
		Set<String> taskSet = new HashSet<String>();
		
		while (taskSet.size() < noOfTask){
			msg = sqs.poll(outputSQSUrl);
			
			if (null != msg){
				taskId = "Task" + msg.split(" ")[0];
				if (!taskSet.contains(taskId)){	//ensures responses are unique
					taskSet.add(taskId);
					//System.out.println("Task" + msg);
				}
			}
		}
		sqs.deleteQ(inputSQSUrl);
		sqs.deleteQ(outputSQSUrl);
		table.deleteTable();
	}
}
