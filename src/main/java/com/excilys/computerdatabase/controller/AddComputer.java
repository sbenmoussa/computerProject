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

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerDTO;

@Controller

public class AddComputer {
	
	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
	@Autowired
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService){
		this.companyService = companyService;
	}
	
	@RequestMapping(value="/addComputer", method = RequestMethod.GET)
	public Computer get(ModelMap model){	
		List<Company> companies = new ArrayList<Company>();
		companies = companyService.getAll(0);
		model.addAttribute("companies", companies);
		return new Computer.ComputerBuilder().build();
	}
	
	@RequestMapping(value="/save/addComputer", method = RequestMethod.POST)
	public ModelAndView post(ModelMap model, @ModelAttribute ComputerDTO computer){
		
		//ValidatorForm acf = new ValidatorForm();
		//newComputer = acf.addComputer(model);
		//if(acf.getResult().equals("Success")){
		boolean success = computerService.insert(computer.toDTO(""));	
		return new ModelAndView("redirect:/dashboard?add="+success);
	}

}
