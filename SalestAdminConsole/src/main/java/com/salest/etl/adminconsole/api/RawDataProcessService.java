package com.salest.etl.adminconsole.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salest.etl.adminconsole.hdfs.HDFSService;

//import com.salest.etl.adminconsole.dao.DailyTrSummaryDAO;
//import com.salest.etl.adminconsole.model.DailyTrSummary;

@Component
@Path("/rawdata")
public class RawDataProcessService {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job dailyAggBatchJob;
	    
	//@Autowired
	//private DailyTrSummaryDAO dailyTrSummaryDAO;
	
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
	
			hdfsService.getHDFSClusterStatus();
			
			//JobExecution jobExe = jobLauncher.run(dailyAggBatchJob, new JobParameters());
			//return Response.status(Response.Status.OK).build();

			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Hibernate.xml");
		
		DailyTrSummaryDAO daoObj = context.getBean(DailyTrSummaryDAO.class);
         
		DailyTrSummary item = new DailyTrSummary();
		item.setDate(new Date());
		item.setNumOfOrder((byte)99);
		item.setTotalAmount((long)25600);
		daoObj.save(item);
	     
		context.close();    
*/		
		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}