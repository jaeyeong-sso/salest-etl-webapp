package com.salest.etl.adminconsole.batch;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

public class DailyTrSumJobExecutionListener implements JobExecutionListener {

	public void beforeJob(JobExecution jobExec) {
		// TODO Auto-generated method stub
		
	}
	
	public void afterJob(JobExecution jobExec) {
		// TODO Auto-generated method stub
		System.out.println("[JOB STATUS] : " + jobExec.getStatus().toString());
		
		Collection<StepExecution> steExec = jobExec.getStepExecutions();
		Iterator iterStepExec = steExec.iterator();
		
		while(iterStepExec.hasNext()) {
			StepExecution step = (StepExecution) iterStepExec.next();
			System.out.println("[STEP STATUS] : " + step.getStepName() + "  " + step.getStatus().toString());
		}
	}

}
