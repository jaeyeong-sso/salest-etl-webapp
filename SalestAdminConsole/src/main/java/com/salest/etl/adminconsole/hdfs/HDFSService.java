package com.salest.etl.adminconsole.hdfs;

import java.io.InputStream;
import java.util.HashMap;

public interface HDFSService {

	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName);
	public HashMap<String,String> reportHDFSClusterStatus();
}