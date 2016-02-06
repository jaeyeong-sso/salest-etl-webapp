package com.salest.etl.adminconsole.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class BatchJobStatusController {
	
	@RequestMapping("/batchjob_status")
	public ModelAndView batchjob_status(){
		ModelAndView mv = new ModelAndView("batchjob_status");
		return mv;
	}
}
