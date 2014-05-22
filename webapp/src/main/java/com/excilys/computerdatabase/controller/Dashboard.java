package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public void get(ModelMap model, Integer order,  String search , Integer page){

		List<String> computers = new ArrayList<String>();
		ComputerDTO computerDto = new ComputerDTO();
		int count = computerService.getTotal("");
		
		if(order == null)
			order =0;
		
		if(page == null)
			page=0;
		
		if(search !=null){
			System.out.println("search not null");
			computers = computerDto.fromDTOList( computerService.filterByName(search, order, page));
			model.addAttribute("search", search);
			count = computerService.getTotal(search);
		}
		else{
			System.out.println("search null");
			computers = computerDto.fromDTOList( computerService.getAll(order, page));		
		}
		
		model.addAttribute("count",count);
		model.addAttribute("order", order);
		model.addAttribute("computers", computers);
		model.addAttribute("page", page);
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.POST)
	public void post(ModelMap model, @ModelAttribute ComputerDTO computer){
		System.out.println("la méthode post du dashboard");
		//return new ModelAndView("redirect:/dashboard");
		get(model,null, null , 0);
	}
}