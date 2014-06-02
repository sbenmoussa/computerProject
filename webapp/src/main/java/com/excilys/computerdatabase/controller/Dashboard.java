package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.DTO.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
public class Dashboard {

	@Autowired
	private ComputerService computerService;

	@Autowired
	ComputerMapper mapperComputer ;

	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public void get(ModelMap model, String order,  String search , Integer page){

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Page<Computer> pages;
		long count ;
		
		if(order == null)
			order ="name";
		
		if(page == null)
			page=0;
		
		if(search !=null){
			System.out.println("search not null");
			pages = computerService.filterByName(search, order, page);
			computers = mapperComputer.toDTOList(pages.getContent() );
			model.addAttribute("search", search);
			count = pages.getTotalElements();
		}
		else{
			System.out.println("search null");
			pages = computerService.getAll(order, page);
			computers = mapperComputer.toDTOList( pages.getContent());	
			count = pages.getTotalElements();
		}		
		model.addAttribute("count",count);
		model.addAttribute("order", order);
		model.addAttribute("computers", computers);
		model.addAttribute("page", page);
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.POST)
	public void post(ModelMap model, @ModelAttribute ComputerDTO computer){
		System.out.println("la méthode post du dashboard");
		get(model,null, null , 0);
	}
}