package com.salest.etl.adminconsole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hdfs_cluster_info")
public class HdfsClusterInfo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String configured_capacity;
	private String present_capacity;
	private String dfs_used;
	
	public String getConfigured_capacity(){
		return this.configured_capacity;
	}
	public void setConfigured_capacity(String configured_capacity){
		this.configured_capacity = configured_capacity;
	}
	
	public String getPresent_capacity(){
		return this.present_capacity;
	}
	public void setPresent_capacity(String present_capacity){
		this.present_capacity = present_capacity;
	}
	
	public String getDfs_used(){
		return this.dfs_used;
	}
	public void setDfs_used(String dfs_used){
		this.dfs_used = dfs_used;
	}
}
