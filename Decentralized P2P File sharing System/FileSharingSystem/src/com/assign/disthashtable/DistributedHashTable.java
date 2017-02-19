/**
 * 
 */
package com.assign.disthashtable;

/**
 * @author Nikhil
 *
 */
public interface DistributedHashTable {
	public boolean put (String key, String value);
	public String get (String key);
	public boolean delete (String key);
}
