package com.salest.etl.adminconsole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="batch_job_instance")
public class BatchJobInstance {

	@Id
	@Column(name="JOB_INSTANCE_ID")
	private long job_instance_id;
	
	@Column(name="JOB_NAME")
	private String job_name;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BatchJobExecution batchJobExecution;
	
	@Transient
	private long version;
	
	@Transient
	private String job_key;
	
	public long getJob_instance_id(){
		return this.job_instance_id;
	}
	public void setJob_instance_id(long job_instance_id){
		this.job_instance_id = job_instance_id;
	}
	
	public String getJob_name(){
		return this.job_name;
	}
	public void setJob_name(String job_name){
		this.job_name = job_name;
	}
	
	public BatchJobExecution getBatchJobExecution(){
		return this.batchJobExecution;
	}
}
