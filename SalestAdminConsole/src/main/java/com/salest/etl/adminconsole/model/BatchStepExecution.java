package com.salest.etl.adminconsole.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="batch_step_execution")
public class BatchStepExecution {

	@Id
	@Column(name="STEP_EXECUTION_ID")
	private long step_execution_id;
	
	@Column(name="STEP_NAME")
	private String step_name;
	
	@Column(name="JOB_EXECUTION_ID")
	private long job_execution_id;
	
	@Column(name="START_TIME")
	private Date start_time;
	
	@Column(name="END_TIME")
	private Date end_time;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="EXIT_CODE")
	private String exit_code;
	
	@Column(name="EXIT_MESSAGE")
	private String exit_message;
	
	@Column(name="LAST_UPDATED")
	private Date last_updated;

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "job_execution_id", referencedColumnName="job_execution_id")
	private BatchJobExecution batchJobExecution;
	
	@Transient
	private long version;
	@Transient
	private long commit_count;
	@Transient
	private long read_count;
	@Transient
	private long filter_count;
	@Transient
	private long write_count;
	@Transient
	private long read_skip_count;
	@Transient
	private long write_skip_count;
	@Transient
	private long process_skip_count;
	@Transient
	private long rollback_count;
	
	public long getStep_execution_id(){
		return this.step_execution_id;
	}
	public void setStep_execution_id(long step_execution_id){
		this.step_execution_id = step_execution_id;
	}
	
	public String getStep_name(){
		return this.step_name;
	}
	public void setStep_name(String step_name){
		this.step_name = step_name;
	}
	
	public long getJob_execution_id(){
		return this.job_execution_id;
	}
	public void setJob_execution_id(long job_execution_id){
		this.job_execution_id = job_execution_id;
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
	
	public String getExit_message(){
		return this.exit_message;
	}
	public void setExit_message(String exit_message){
		this.exit_message = exit_message;
	}
	
	public Date getLast_updated(){
		return this.last_updated;
	}
	public void setLast_updated(Date last_updated){
		this.last_updated = last_updated;
	}
	
	public BatchJobExecution getBatchJobExecution() {
		return this.batchJobExecution;
	}
}
