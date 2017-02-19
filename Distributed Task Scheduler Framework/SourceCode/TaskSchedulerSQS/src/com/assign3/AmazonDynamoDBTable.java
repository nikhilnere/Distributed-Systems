package com.assign3;

import java.util.ArrayList; 

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.Tables;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;

@SuppressWarnings("deprecation")
public class AmazonDynamoDBTable {

	private AmazonDynamoDBClient dynamoDBClinet;
	private DynamoDB dynamoDB;
	private Table table;
	
	public AmazonDynamoDBTable(String accessKey, String secretKey, String tableName) {
		dynamoDBClinet = new AmazonDynamoDBClient(new BasicAWSCredentials(accessKey, secretKey));
    	dynamoDBClinet.setRegion(Region.getRegion(Regions.US_EAST_1));
    	
    	dynamoDB = new DynamoDB(dynamoDBClinet);
    	try{
    		if (!Tables.doesTableExist(dynamoDBClinet, tableName)){
        		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
      		  attributeDefinitions.add(new AttributeDefinition()
      		      .withAttributeName("id")
      		      .withAttributeType("S"));
      		  
      		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
      		keySchema.add(new KeySchemaElement()
      		    .withAttributeName("id")
      		    .withKeyType(KeyType.HASH));
      		
      		CreateTableRequest request = new CreateTableRequest()
      		.withTableName(tableName)
      		.withKeySchema(keySchema)
      		.withAttributeDefinitions(attributeDefinitions)
      		.withProvisionedThroughput(new ProvisionedThroughput()
      		    .withReadCapacityUnits(100L)
      		    .withWriteCapacityUnits(100L));
      		
      		table = dynamoDB.createTable(request);
        	}else {
        		table = dynamoDB.getTable(tableName);
        	}
        	

    		try {
    			table.waitForActive();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}catch (ResourceInUseException e){
    		try {
				Tables.awaitTableToBecomeActive(dynamoDBClinet, tableName);
				table = dynamoDB.getTable(tableName);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
    	
	}
	
	//adds elements to the table
	public void addElement(String key, String value) {
		table.putItem(new Item().withPrimaryKey("id", key).withString("idValue", value));
	}
	
	//fetches element from the table 
	public String getElement(String key){
		Item item = table.getItem("id", key, "id, idValue", null);
		if (null != item)
			return (String) item.get("idValue");
		else 
			return null; 
	}
	
	//deletes table 
	public void deleteTable(){
		table.delete();
		try {
			table.waitForDelete();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}