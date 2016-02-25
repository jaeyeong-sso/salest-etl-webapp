package com.salest.etl.adminconsole.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salest.etl.adminconsole.hdfs.HDFSService;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Component
@Path("/upload")
public class RawFileUploadService {
	
	@Autowired
	private HDFSService hdfsService;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job dailyAggBatchJob;

	@Autowired
	Job menuDataETLBatchJob;
	
	@POST
	@Path("/tr_csv")
	@Consumes({"multipart/form-data"})
	public Response uploadTransactionDataCSVFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition contentDispositionHeader){
		if (contentDispositionHeader.getFileName() != null){
			
			hdfsService.appenToFileOnHDFS(fileInputStream, contentDispositionHeader.getFileName());

			return Response.status(Response.Status.OK).build();
			
			/*
			try {
				JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			    jobParametersBuilder.addLong("run.id", System.currentTimeMillis());
			    
				JobExecution jobExe = jobLauncher.run(dailyAggBatchJob, jobParametersBuilder.toJobParameters());
				return Response.status(Response.Status.OK).build();
				
			} catch (JobExecutionAlreadyRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobRestartException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobInstanceAlreadyCompleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
	    }
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@POST
	@Path("/menu_csv")
	@Consumes({"multipart/form-data"})
	public Response uploadMenuDataCSVFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition contentDispositionHeader){
		
		if (contentDispositionHeader.getFileName() != null){
			
			hdfsService.storeToFileOnHDFS(fileInputStream, contentDispositionHeader.getFileName());
			
			try {
				JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			    jobParametersBuilder.addLong("run.id", System.currentTimeMillis());
			    
				JobExecution jobExe = jobLauncher.run(menuDataETLBatchJob, jobParametersBuilder.toJobParameters());
				return Response.status(Response.Status.OK).build();
				
			} catch (JobExecutionAlreadyRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobRestartException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobInstanceAlreadyCompleteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return Response.status(Response.Status.OK).build();
		}
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
