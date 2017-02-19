package com.assign3;

import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class AWSSimpleQueueService {
	
	private AmazonSQS sqs;
	
	public AWSSimpleQueueService(String accessKey, String secretKey) {
    	sqs = new AmazonSQSClient(new BasicAWSCredentials(accessKey, secretKey));
    	Region usWest2 = Region.getRegion(Regions.US_EAST_1);
        sqs.setRegion(usWest2);
	}
	
	/**
	 * Creates AWS SQS
	 * @param qName
	 * @return
	 */
	public String creareSQS(String qName){
		
		try{
			String queueUrl = sqs.getQueueUrl(qName).getQueueUrl();
			return queueUrl;
		}catch (QueueDoesNotExistException e){
			//Create Queue
			CreateQueueRequest createQueueRequest = new CreateQueueRequest(qName);
	        return sqs.createQueue(createQueueRequest).getQueueUrl();
		}
	}
	
	/**
	 * Returns SQS url for the given qName
	 * @param qName
	 * @return
	 */
	public String getSQSUrl(String qName){
		return sqs.getQueueUrl(qName).getQueueUrl();
		//return sqs.listQueues(qName).getQueueUrls().get(0);
	}
	
	/**
	 * Adds message to SQS
	 * @param message
	 * @param myQueueUrl
	 */
	public void add(String message, String myQueueUrl){
		//add message to SQS
		sqs.sendMessage(new SendMessageRequest(myQueueUrl, message));
	}
	
	/**
	 * Poll messages from SQS
	 * @param myQueueUrl
	 * @return
	 */
	public String poll(String myQueueUrl){
		String msg = null ;
		//Get message from SQS
        List<Message> messages = sqs.receiveMessage(new ReceiveMessageRequest(myQueueUrl)).getMessages();
        if (null != messages && !messages.isEmpty()){
        	msg = messages.get(0).getBody();
        	//Deleting the message
        	sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messages.get(0).getReceiptHandle()));
        }
		return msg;
	}
	
	/**
	 * Deletes SQS
	 * @param myQueueUrl
	 */
	public void deleteQ(String myQueueUrl){
		sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
	}
}
