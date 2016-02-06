package com.salest.etl.adminconsole.web;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class DWManagementController {

	@RequestMapping("/dwhouse_man")
	public ModelAndView dwhouse_man(){
 		ModelAndView mv = new ModelAndView("dwhouse_man");
		return mv;
	}

}