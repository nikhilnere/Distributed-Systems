/**
 * 
 */
package com.assign.disthashtable;

/**
 * @author nikhil
 *
 */
public class FileReplicator implements Runnable {

	String peerId;
	String fileDir;
	String fileName;
	
	public FileReplicator(String peerId,String fileName, String fileDir) {
		this.peerId = peerId;
		this.fileDir = fileDir;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		FileReceiver fileReceiver = new FileReceiver();
		fileReceiver.receiveFile(peerId, fileName, fileDir);
	}
}