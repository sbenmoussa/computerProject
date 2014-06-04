package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computerdatabase.DTO.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

@Controller

public class AddComputer {
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	ComputerMapper mapperComputer ;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
	@Autowired
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService){
		this.companyService = companyService;
	}
	
	@RequestMapping(value="/addComputer", method = RequestMethod.GET)
	public ComputerDTO get(ModelMap model){	
		List<Company> companies = new ArrayList<Company>();
		companies = companyService.getAll();
		ComputerDTO computerdto = mapperComputer.toDTO(new Computer.ComputerBuilder().build());
		model.addAttribute("companies", companies);
		model.addAttribute("computerdto", computerdto);
		return computerdto; 
	}
	
	@RequestMapping(value="/addComputer", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute("computerdto") @Valid  ComputerDTO computerdto, BindingResult result){

		boolean success = false;
		if (result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					System.out.println(fieldError.getField() + ":"+ fieldError.getCode()+"  , "+fieldError.toString());
				}
			}
			return "/addComputer";
		} else {
			success = computerService.insert(mapperComputer.fromDTO(computerdto));				
		}
		model.addAttribute("add", success);
		return "forward:/dashboard";
	}

}
