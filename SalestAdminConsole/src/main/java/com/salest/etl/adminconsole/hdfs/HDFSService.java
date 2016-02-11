package com.salest.etl.adminconsole.hdfs;

import java.io.InputStream;
import java.util.HashMap;

public interface HDFSService {

	public static String dfsadmin_configured_capacity_key = "configured_capacity";
	public static String dfsadmin_present_capacity_key = "present_capacity";
	
	public static String dfsadmin_name_key = "name";
	public static String dfsadmin_hostname_key = "hostname";
	public static String dfsadmin_dfs_used_key = "dfs_used";
	public static String dfsadmin_dfs_remaining_key ="dfs_remaining";
	public static String dfsadmin_dfs_remaining_percent_key ="dfs_remaining%";
	
	public void appenToFileOnHDFS(InputStream fileInputStream, String apppendFileName);
	public void reportHDFSClusterStatus();
	public HashMap<String,String> getHdfsClusterInfoMap();
	public HashMap<String,String> getHdfsNodesInfoMap();
}