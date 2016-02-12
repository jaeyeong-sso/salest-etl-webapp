package com.salest.etl.adminconsole.model;

public class HdfsFileListingInfo {

	private String filePath;
	private long fileSize;
	private String updateDate;
	
	public HdfsFileListingInfo(String filePath, long fileSize, String updateDate){
		this.filePath = filePath;
		this.fileSize = fileSize; 
		this.updateDate = updateDate;
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public long getFileSize(){
		return this.fileSize;
	}
	public void setFileSize(long fileSize){
		this.fileSize = fileSize;
	}
	
	public String getUpdateDate(){
		return this.updateDate;
	}
	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}
}
