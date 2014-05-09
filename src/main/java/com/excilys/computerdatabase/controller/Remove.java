package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping("/Remove")
public class Remove {

	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView post(ModelMap model, Long idComputer){
		System.out.println("id computer : "+idComputer);
		
		if ((idComputer !=null) && (!idComputer.equals(""))){
			System.out.println(idComputer);
			boolean success = computerService.delete(idComputer);
			return new ModelAndView("redirect:/dashboard?remove="+success);
		}
		else{
			System.out.println("vide");
		}	
		return new ModelAndView("redirect:/dashboard");
	}
}
