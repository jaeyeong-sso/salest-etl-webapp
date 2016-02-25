package com.salest.etl.adminconsole.api;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.data.hadoop.mapreduce.ToolRunner;
import org.springframework.stereotype.Component;

import com.salest.etl.adminconsole.dao.BatchJobExecutionDAO;
import com.salest.etl.adminconsole.hdfs.HDFSService;
import com.salest.etl.adminconsole.model.BatchJobExecution;
import com.salest.etl.adminconsole.model.BatchJobInstance;
import com.salest.etl.adminconsole.model.BatchStepExecution;

@Component
@Path("/rawdata")
public class RawDataProcessService {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job dailyAggBatchJob;
	
	@Autowired
	Job joinTrReceiptMenuCodeJob;
	
	
	@Autowired
	BatchJobExecutionDAO batchJobExecutionDAO;
	
	@Autowired
	HDFSService hdfsService;
	
	@GET
	@Path("/agg_tr_daily")
	public Response test() {
		
		return Response.status(Response.Status.OK).entity("TEST OK").build();
	}
	
	@POST
	@Path("/agg_tr_daily")
	public Response execAggTrData() {
		
		try {
			
			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		    jobParametersBuilder.addLong("run.id", System.currentTimeMillis());
			JobExecution jobExe = jobLauncher.run(dailyAggBatchJob, jobParametersBuilder.toJobParameters());
			
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@POST
	@Path("/join_tr_receipt_menucode")
	public Response joinTrReceiptMenucode() {
		
		try {
			
			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		    jobParametersBuilder.addLong("run.id", System.currentTimeMillis());
			JobExecution jobExe = jobLauncher.run(joinTrReceiptMenuCodeJob, jobParametersBuilder.toJobParameters());
			
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@POST
	@Path("/print_result")
	public Response print_result() {
	
			List<BatchJobExecution> batchJobs =  batchJobExecutionDAO.list();
			
			if(batchJobs!=null){
				
				System.out.println("Size Of BatchJobExecution : " + batchJobs.size());
				
				for(BatchJobExecution batchJob : batchJobs){
					BatchJobInstance jobInstance = batchJob.getBatchJobInstance();
					Set<BatchStepExecution> stepExecutions = batchJob.getBatchStepExecutions();
					
					if(jobInstance!=null){
						System.out.println("BatchJobInstance Name: " + jobInstance.getJob_name());
					}
					
					System.out.println("Size Of StepExecutions : " + stepExecutions.size());
					
					for (Iterator iter = stepExecutions.iterator(); iter.hasNext();){
						BatchStepExecution step = (BatchStepExecution)iter.next();
						if(step!=null){
							System.out.println("   Step Name: " + step.getStep_name() +
									"     Status: " + step.getStatus() +
									"     Updated Time : " + step.getLast_updated().toString());
						}
					}
				}
			}
			
			return Response.status(Response.Status.OK).build();
	}
}