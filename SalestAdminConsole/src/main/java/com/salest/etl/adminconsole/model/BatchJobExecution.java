package com.salest.etl.adminconsole.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="batch_job_execution")
public class BatchJobExecution {
	
	@Id
	@Column(name="JOB_EXECUTION_ID")
	private long job_execution_id;
	
	@Column(name="JOB_INSTANCE_ID")
	private long job_instance_id;
	
	@Column(name="START_TIME")
	private Date start_time;
	
	@Column(name="END_TIME")
	private Date end_time;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="EXIT_CODE")
	private String exit_code;
	
	@Column(name="LAST_UPDATED")
	private Date last_updated;
	
	
	@Transient
	private long version;
	
	@Transient
	private String job_configuration_location;
	
	@Transient
	private Date create_time;
	
	@Transient
	private String exit_message;
	
	@OneToOne (cascade=CascadeType.ALL, mappedBy="batchJobExecution")
	private BatchJobInstance batchJobInstance;
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "batchJobExecution")
	@Fetch(FetchMode.SELECT)
	private Set<BatchStepExecution> batchStepExecutions;

	
	public BatchJobExecution(){
		batchStepExecutions = new HashSet<BatchStepExecution>();
	}
	
	
	public long getJob_execution_id(){
		return this.job_execution_id;
	}
	public void setJob_execution_id(long job_execution_id){
		this.job_execution_id = job_execution_id;
	}
	
	public long getJob_instance_id(){
		return this.job_instance_id;
	}
	public void setJob_instance_id(long job_instance_id){
		this.job_instance_id = job_instance_id;
	}
	
	public Date getStart_time(){
		return this.start_time;
	}
	public void setStart_time(Date start_time){
		this.start_time = start_time;
	}
	
	public Date getEnd_time(){
		return this.end_time;
	}
	public void setEnd_time(Date end_time){
		this.end_time = end_time;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getExit_code(){
		return this.exit_code;
	}
	public void setExit_code(String exit_code){
		this.exit_code = exit_code;
	}
	
	public Date getLast_updated(){
		return this.last_updated;
	}
	public void setLast_updated(Date last_updated){
		this.last_updated = last_updated;
	}

	public BatchJobInstance getBatchJobInstance(){
		return this.batchJobInstance;
	}


	public Set<BatchStepExecution> getBatchStepExecutions() {
		return this.batchStepExecutions;
	}


}
