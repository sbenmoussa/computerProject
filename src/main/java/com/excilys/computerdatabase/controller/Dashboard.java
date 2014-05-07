package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public void get(ModelMap model, Integer order,  String search  , Integer page){
		
		System.out.println("order = "+order+" et page = "+page);
		List<String> computers = new ArrayList<String>();
		ComputerDTO computerDto = new ComputerDTO();
		
		String name = search;
		if(order != null){
		//if(!(order <0)){
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
			System.out.println("le parametre age n'est pas null");
			model.addAttribute("page", page);
		}	
		else{
			model.addAttribute("page", 0);
		}
		
		if(model.get( "update" ) != null){
			System.out.println("un apdate effectue "+model.get( "update" ));
			model.addAttribute("update", model.get( "update" ));
		}	 	
		
		if(model.get( "add" ) != null){
			model.addAttribute("add", model.get( "add" ));
		}	
		System.out.println();
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward( request, response );
	}
}