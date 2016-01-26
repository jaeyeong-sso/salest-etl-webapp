package com.salest.etl.adminconsole.hadoop;

import java.io.InputStream;

public interface HDFSService {
	
	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName);
}