package com.assign3;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class TaskWorker {

	private Properties prop;
	AWSSimpleQueueService sqs;
	AmazonDynamoDBTable table;
	String inputSQSUrl;
	String outputSQSUrl;
	int noOfThreads;
	
	public TaskWorker(String inputQ, String outputQ, String noOfThreads) throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("taskSchedulerProp.properties"));
		String accessKey = prop.getProperty("access.key");
		String secretKey = prop.getProperty("secret.key");
		sqs = new AWSSimpleQueueService(accessKey, secretKey);
		inputSQSUrl = sqs.creareSQS(inputQ);
		outputSQSUrl = sqs.creareSQS(outputQ);
		this.noOfThreads = Integer.parseInt(noOfThreads);

		table = new AmazonDynamoDBTable (accessKey, secretKey, "intermediateTable");
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		TaskWorker worker = new TaskWorker(args[0], args[1], args[2]);
		
		//Submit tasks to queue
		worker.processTaskQueue();
		
	}
	public void processTaskQueue(){
		
		for (int i = 0; i < noOfThreads; i++){
			Thread workerThread = new Thread(new WorkerThread(sqs, inputSQSUrl, outputSQSUrl, table));
			workerThread.start();
		}
	}
}

class WorkerThread implements Runnable{

	String inputSQSUrl;
	String outputSQSUrl;
	AWSSimpleQueueService sqs;
	AmazonDynamoDBTable table;
	public WorkerThread(AWSSimpleQueueService sqs, String inputSQSUrl, String outputSQSUrl, AmazonDynamoDBTable table) {
		this.inputSQSUrl = inputSQSUrl;
		this.outputSQSUrl = outputSQSUrl;
		this.sqs = sqs;
		this.table = table;
	}
	
	@Override
	public void run() {
		String task;
		try {
			while (true){
				task = sqs.poll(inputSQSUrl);
				if (null != task){
					String[] tokens = task.split(" ");
					
					if (null == table.getElement(tokens[0])){
						table.addElement(tokens[0], tokens[1] + " " + tokens[2]);
						long sleepTime = Long.parseLong(tokens[2]);

						try {
							Thread.sleep(sleepTime);
							sqs.add(tokens[0]+" 0", outputSQSUrl); //0 for success
							
						} catch (Exception e) {
							sqs.add(tokens[0]+" 1", outputSQSUrl); //1 for failure
						}
					}
				}
			}
		}catch (Exception e){
			System.out.println("Exiting the Worker Thread!! Reasson : Task queues might be deleted");
			//e.printStackTrace();
		}
		
	}
	
}
