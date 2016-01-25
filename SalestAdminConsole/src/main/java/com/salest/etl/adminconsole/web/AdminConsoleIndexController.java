package com.salest.etl.adminconsole.web;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
public class AdminConsoleIndexController {

	@RequestMapping("/")
	public ModelAndView index(){
 		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

}