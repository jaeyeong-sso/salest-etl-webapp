package com.salest.etl.adminconsole.web;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salest.etl.adminconsole.dao.DFSAdminReportDAO;
import com.salest.etl.adminconsole.model.DFSAdminReport;
import com.salest.etl.adminconsole.model.DailyTrSummary;
 
@Controller
public class DWManagementController {

	@Autowired
	DFSAdminReportDAO dfsAdminReportDAO;
	
	@RequestMapping("/dwhouse_man")
	public ModelAndView dwhouse_man(){
		
		List<DFSAdminReport> adminReportItems = dfsAdminReportDAO.list();

 		ModelAndView mv = new ModelAndView("dwhouse_man");
 		mv.addObject("NodeInfoItems", adminReportItems);
 		
		return mv;
	}

}