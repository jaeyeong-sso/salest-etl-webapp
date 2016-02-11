package com.salest.etl.adminconsole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hdfs_nodes_info")
public class HdfsNodesInfo {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	private String name;
	private String hostname;
	private String dfs_used;
	private String dfs_remaining;
	private String dfs_remaining_percent;

    public HdfsNodesInfo(){
    	
    }
    
    public String getName(){
    	return this.name;
    }
    public void setName(String name){
    	this.name = name;
    }
    
    public String getHostname(){
    	return this.hostname;
    }
    public void setHostname(String hostname){
    	this.hostname = hostname;
    }
    
    public String getDfs_used(){
    	return this.dfs_used;
    }
    public void setDfs_used(String dfs_used){
    	this.dfs_used = dfs_used;
    }
    
    public String getDfs_remaining(){
    	return this.dfs_remaining;
    }
    public void setDfs_remaining(String dfs_remaining){
    	this.dfs_remaining = dfs_remaining;
    }
    
    public String getDfs_remaining_percent(){
    	return this.dfs_remaining_percent;
    }
    public void setDfs_remaining_percent(String dfs_remaining_percent){
    	this.dfs_remaining_percent = dfs_remaining_percent;
    }

	@Override
    public String toString(){
    	return "name=" + name + ", hostname=" + hostname + 
    			", dfs_used=" + dfs_used + ", dfs_remaining=" + dfs_remaining +
    			", dfs_remaining_percent=" + dfs_remaining_percent;
    }
}
