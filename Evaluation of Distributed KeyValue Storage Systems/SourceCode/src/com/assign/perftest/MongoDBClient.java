package com.assign.perftest;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBClient {

	int numOfServers;
	ArrayList<DBCollection> collectionList;
	ArrayList<BasicDBObject> dbObjList;
	
	DBCollection collection;
	public MongoDBClient(List<String> serverIPs, int numOfServers){
		this.numOfServers = numOfServers;
		collectionList = new ArrayList<DBCollection>();
		dbObjList = new ArrayList<BasicDBObject>();
		
		for(int i=0; i< numOfServers; i++){
			collectionList.add(new MongoClient(serverIPs.get(i)+":27017").getDB("TestDB").getCollection("table1"));
			collectionList.get(i).insert(new BasicDBObject("key","value"));
			//dbObjList.add((BasicDBObject)collectionList.get(i).find().next());
		}
	}
	
	public void put (String key, String value){
		int host = getHash(key);
		//dbObjList.get(host).put(key, value);
		BasicDBObject basicDBObject = (BasicDBObject)collectionList.get(host).find().next();
		basicDBObject.put(key, value);
		collectionList.get(host).save(basicDBObject);
		
	}
	public String get (String key){
		int host = getHash(key);
		BasicDBObject basicDBObject = (BasicDBObject)collectionList.get(host).find().next();
		return (String)basicDBObject.get(key);
		//return (String)dbObjList.get(host).get(key);
	}
	public void remove (String key){
		int host = getHash(key);
		BasicDBObject basicDBObject = (BasicDBObject)collectionList.get(host).find().next();
		basicDBObject.remove(key);
		collectionList.get(host).save(basicDBObject);
	}

	private int getHash(String key) {
		char ch[];
		ch = key.toCharArray();
		
		int sum = 0;
		
		for (int i = 0; i < key.length(); i++){
			sum += ch[i];
		}
		return sum % numOfServers;
	}
}
