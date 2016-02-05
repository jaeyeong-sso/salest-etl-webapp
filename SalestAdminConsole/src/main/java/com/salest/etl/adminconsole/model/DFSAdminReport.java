package com.salest.etl.adminconsole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dfsadmin_report")
public class DFSAdminReport {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	private String name;
	private String hostname;
	private String dfs_used;
	private String dfs_remaining;

    public DFSAdminReport(){
    	
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

	@Override
    public String toString(){
    	return "name=" + name + ", hostname=" + hostname + 
    			", dfs_used=" + dfs_used + ", dfs_remaining=" + dfs_remaining;
    }
}
