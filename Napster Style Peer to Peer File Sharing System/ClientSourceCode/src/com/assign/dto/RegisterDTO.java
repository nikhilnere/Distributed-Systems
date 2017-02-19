package com.assign.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class RegisterDTO implements Serializable {

	int noOfFiles;
	List<String> fileList;
	String peerId;
	String portNo;
	
	/**
	 * @return the noOfFiles
	 */
	public int getNoOfFiles() {
		return noOfFiles;
	}
	/**
	 * @param noOfFiles the noOfFiles to set
	 */
	public void setNoOfFiles(int noOfFiles) {
		this.noOfFiles = noOfFiles;
	}
	/**
	 * @return the fileList
	 */
	public List<String> getFileList() {
		if (fileList == null)
			fileList = new ArrayList<String>();
		return fileList;
	}
	/**
	 * @param fileList the fileList to set
	 */
	/*public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}*/
	/**
	 * @return the peerId
	 */
	public String getPeerId() {
		return peerId;
	}
	/**
	 * @param peerId the peerId to set
	 */
	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}
	/**
	 * @return the portNo
	 */
	public String getPortNo() {
		return portNo;
	}
	/**
	 * @param portNo the portNo to set
	 */
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	
}
