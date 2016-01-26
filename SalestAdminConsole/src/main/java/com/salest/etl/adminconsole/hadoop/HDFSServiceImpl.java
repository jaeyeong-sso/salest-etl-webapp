package com.salest.etl.adminconsole.hadoop;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

public class HDFSServiceImpl implements HDFSService {

	private String HDFS_CONF_FS_DEFAULT_NAME;
	
	private Configuration conf;
	private FileSystem hdfs;
	
	public HDFSServiceImpl(){		
		conf = new Configuration();
	    conf.set("fs.default.name", HDFS_CONF_FS_DEFAULT_NAME);
	    conf.set("ipc.client.connect.timeout", "30000");
	    conf.set("dfs.replication", "1");
	}
	
	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName) {
	    
		try {
			hdfs = FileSystem.get(conf);
	    	
			org.apache.hadoop.fs.Path trReceiptFilePath = new org.apache.hadoop.fs.Path("/salest/raw_data/transaction_receipt.data");
			BufferedWriter bufWriter = null;
			if (hdfs.exists(trReceiptFilePath)) {
				bufWriter = new BufferedWriter(new OutputStreamWriter(hdfs.append(trReceiptFilePath)));
			} else {
				bufWriter = new BufferedWriter(new OutputStreamWriter(hdfs.create(trReceiptFilePath, true)));
			}
			IOUtils.copy(fileInputStream, bufWriter);
			bufWriter.close();
			
			hdfs.close();
	    }
	    catch (IOException e){
	      e.printStackTrace();
	    }
	  }
	
}