package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerDTO;

@Controller
public class Dashboard {

	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
//	Locale locale = Locale.getDefault();
//	ResourceBundle res = ResourceBundle.getBundle("message"); 


	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public void get(ModelMap model, Integer order,  String search , Integer page){

		List<String> computers = new ArrayList<String>();
		ComputerDTO computerDto = new ComputerDTO();

		String name = search;
		if(order != null){
			if(name !=null){
				computers = computerDto.fromDTOList( computerService.filterByName(name, order));
				model.addAttribute("search", name);
			}
			else{
				computers = computerDto.fromDTOList( computerService.getAll(order));		
			}
			model.addAttribute("order", order);
		}

		else{
			if(name !=null){
				computers = computerDto.fromDTOList(computerService.filterByName(name, 0));
				model.addAttribute("search", name);
			}
			else{
				computers = computerDto.fromDTOList(computerService.getAll(0));		
			}
			model.addAttribute("order", 0);
		}

		model.addAttribute("computers", computers);
		if(page != null){
			model.addAttribute("page", page);
		}	
		else{
			model.addAttribute("page", 0);
		}
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.POST)
	public void post(ModelMap model, @ModelAttribute ComputerDTO computer){
		System.out.println("la méthode post du dashboard");
		//return new ModelAndView("redirect:/dashboard");
		get(model,null, null , 0);
	}
	
//	@RequestMapping(value="/switchLanguage")
//	public String changeLocale(String language){
//		Locale.setDefault(new Locale(language));
//		System.out.println(Locale.getDefault());
//		return "forward:/dashboard";
//	}
}