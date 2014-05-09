package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerDTO;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void get(ModelMap model, Integer order,  String search , Integer page, String update, String add){

		System.out.println("order = "+order+" et page = "+page+" et update result= "+update);
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
			System.out.println("le parametre page n'est pas null");
			model.addAttribute("page", page);
		}	
		else{
			model.addAttribute("page", 0);
		}

		if(update != null){
			System.out.println("un apdate effectue "+update);
			model.addAttribute("update", update);
		}	 	

		if(add !=null){
			model.addAttribute("add", add);
		}	
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.POST)
	public ModelAndView post(ModelMap model, @ModelAttribute ComputerDTO computer){
		System.out.println("la méthode post du dashboard");
		return new ModelAndView("redirect:/dashboard");
	}
}