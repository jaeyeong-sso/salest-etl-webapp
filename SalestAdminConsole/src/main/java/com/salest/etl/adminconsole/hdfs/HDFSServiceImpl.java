package com.salest.etl.adminconsole.hdfs;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.tools.DFSAdmin;


public class HDFSServiceImpl implements HDFSService {

	private String HDFS_CONF_FS_DEFAULT_NAME = "hdfs://local-hadoop-namenode:9000";
	
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
				bufWriter = new BufferedWriter(new OutputStreamWriter(hdfs.create(trReceiptFilePath, (short)1)));
			}
			IOUtils.copy(fileInputStream, bufWriter);
			bufWriter.close();
			
			hdfs.close();
	    }
	    catch (IOException e){
	      e.printStackTrace();
	    }
	 }
	
	 public void getHDFSClusterStatus(){
		 
		 DFSAdmin dfsAdmin = new DFSAdmin(conf);
		 String [] args =  new String[]{"Name","DFS Remaining"};
		 try {
			 
			ByteArrayOutputStream pipeOut = new ByteArrayOutputStream();
			
			PrintStream old_out = System.out;
			System.setOut(new PrintStream(pipeOut));

			dfsAdmin.report(args, 0);

			System.setOut(old_out);
			String output = new String(pipeOut.toByteArray());
			
			if(output!=null){
				System.out.println(output);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 }
	
}