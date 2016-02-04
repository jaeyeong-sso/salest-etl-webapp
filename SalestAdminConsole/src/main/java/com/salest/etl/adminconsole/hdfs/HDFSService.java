package com.salest.etl.adminconsole.hdfs;

import java.io.InputStream;

public interface HDFSService {
	
	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName);
	public void getHDFSClusterStatus();
}