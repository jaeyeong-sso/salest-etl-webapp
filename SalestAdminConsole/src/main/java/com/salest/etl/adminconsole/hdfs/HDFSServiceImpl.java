package com.salest.etl.adminconsole.hdfs;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.tools.DFSAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import com.salest.etl.adminconsole.dao.HdfsClusterInfoDAO;
import com.salest.etl.adminconsole.dao.HdfsNodesInfoDAO;
import com.salest.etl.adminconsole.model.HdfsClusterInfo;
import com.salest.etl.adminconsole.model.HdfsFileListingInfo;
import com.salest.etl.adminconsole.model.HdfsNodesInfo;


public class HDFSServiceImpl implements HDFSService {

	private String HDFS_CONF_FS_DEFAULT_NAME = "hdfs://local-hadoop-namenode:9000";
	
	private Configuration conf;
	private FileSystem hdfs;
	
	private HashMap<String,String> hdfsClusterInfoMap;
	private HashMap<String,String> hdfsNodesInfoMap;

	public HDFSServiceImpl(){
		conf = new Configuration();
	    conf.set("fs.default.name", HDFS_CONF_FS_DEFAULT_NAME);
	    conf.set("ipc.client.connect.timeout", "30000");
	    conf.set("dfs.replication", "1");
	    
	    hdfsClusterInfoMap = new HashMap<String,String>();
	    hdfsNodesInfoMap = new HashMap<String,String>();
	}
	
	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName) {
	    
		try {
			hdfs = FileSystem.get(conf);
	    	
			org.apache.hadoop.fs.Path trReceiptFilePath
						= new org.apache.hadoop.fs.Path(this.DIR_PATH_RAW_DATA + "/" + this.FILE_PATH_TR_RECEIPT);
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
	
	 public void reportHDFSClusterStatus(){
		 
		 DFSAdmin dfsAdmin = new DFSAdmin(conf);
		 String [] args =  new String[]{"Configured Capacity","Present Capacity","Name","DFS Remaining"};
		 try {
			 
			ByteArrayOutputStream pipeOut = new ByteArrayOutputStream();
			
			PrintStream old_out = System.out;
			System.setOut(new PrintStream(pipeOut));

			dfsAdmin.report(args, 0);

			System.setOut(old_out);
			String strReportOut = new String(pipeOut.toByteArray());
			
			if(strReportOut!=null){
				parseAndStoreReportResult(strReportOut);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 private void parseAndStoreReportResult(String strReportResult){
		 
		 String sections[] = strReportResult.split("-------------------------------------------------");
		 
		 if(sections.length>1){
			 
			 String clusterInfoLines[] = sections[0].split("\\r?\\n");
			 
			 for(String line : clusterInfoLines){
				 String[] items = line.split(":",2);
				 hdfsClusterInfoMap.put(items[0].trim().toLowerCase().replace(' ', '_'), items[1].trim());
			 }
			 
			 String nodeInfolines[] = sections[1].split("\\r?\\n");
			 
			 for(String line : nodeInfolines){
				 String[] items = line.split(":",2);
				 if(items.length > 1){
					 hdfsNodesInfoMap.put(items[0].trim().toLowerCase().replace(' ', '_'), items[1].trim());
				 }
			 }
		 }
	 }
	 
	 public HashMap<String,String> getHdfsClusterInfoMap(){
		 return this.hdfsClusterInfoMap;
	 }
	 public HashMap<String,String> getHdfsNodesInfoMap(){
		 return this.hdfsNodesInfoMap;
	 }
	 
	 public List<HdfsFileListingInfo> doFileListing(){
		 
		 try {
			 return getAllFilePath(new org.apache.hadoop.fs.Path("/salest"), FileSystem.get(conf));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<HdfsFileListingInfo> getAllFilePath(org.apache.hadoop.fs.Path filePath, FileSystem fs) throws FileNotFoundException, IOException {
	    List<HdfsFileListingInfo> fileList = new ArrayList<HdfsFileListingInfo>();
	    FileStatus[] fileStatus = fs.listStatus(filePath);
	    for (FileStatus fileStat : fileStatus) {
	        if (fileStat.isDirectory()) {
	            fileList.addAll(getAllFilePath(fileStat.getPath(), fs));
	        } else {
	        	fileList.add(
	        			new HdfsFileListingInfo(
	        					fileStat.getPath().toString(),
	        					fileStat.getLen(),
	        					convertTime(fileStat.getModificationTime())));
	        }
	    }
	    return fileList;
	}
	
	private String convertTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(date);
	}
	 
}