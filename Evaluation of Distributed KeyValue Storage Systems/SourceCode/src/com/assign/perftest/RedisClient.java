package com.assign.perftest;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;


public class RedisClient {

	int numOfServers;
	ArrayList<Jedis> redisClients = new ArrayList<Jedis>();

	public RedisClient(List<String> serverIPs, int numOfServers){
		this.numOfServers = numOfServers;
		for(int i=0; i< numOfServers; i++){
			redisClients.add(new Jedis(serverIPs.get(i)));
		}
	}
	
	public void put (String key, String value){
		int host = getHash(key);
		redisClients.get(host).set(key, value);
	}
	
	public String get (String key){
		int host = getHash(key);
		return (redisClients.get(host).get(key));
	}
	
	public void remove (String key){
		int host = getHash(key);
		redisClients.get(host).del(key);
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
