package com.salest.etl.adminconsole.web;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salest.etl.adminconsole.dao.BatchJobExecutionDAO;
import com.salest.etl.adminconsole.dao.DailyTrSummaryDAO;
import com.salest.etl.adminconsole.model.BatchJobExecution;
import com.salest.etl.adminconsole.model.BatchJobInstance;
import com.salest.etl.adminconsole.model.BatchStepExecution;
import com.salest.etl.adminconsole.model.DailyTrSummary;
 
@Controller
public class BatchJobStatusController {
	
	@Autowired
	BatchJobExecutionDAO batchJobExecutionDAO;
	
	@Autowired
	DailyTrSummaryDAO dailyTrSummaryDAO;
	
	
	@RequestMapping("/batchjob_status")
	public ModelAndView batchjob_status(){
		
		List<BatchJobExecution> batchJobs =  batchJobExecutionDAO.list();
		
		if(batchJobs!=null){
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
		
		DailyTrSummary lastDateItem = dailyTrSummaryDAO.getLatestDateItem();
		DailyTrSummary earliestDateItem = dailyTrSummaryDAO.getEarliestDateItem();
		
		if(lastDateItem!=null){
			lastDateItem.getDate();
		}
		
		ModelAndView mv = new ModelAndView("batchjob_status");
		mv.addObject("batchJobs", batchJobs);
		mv.addObject("earliestDateItem", earliestDateItem);
		mv.addObject("lastDateItem", lastDateItem);
		
		return mv;
	}
}
