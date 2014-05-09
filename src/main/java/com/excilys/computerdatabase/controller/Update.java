package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.CompanyDTO;
import com.excilys.computerdatabase.validator.ComputerDTO;

@Controller
public class Update {

	@Autowired
	private ComputerService computerService;
       
	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerDTO computerDto;
	
	@Autowired
	CompanyDTO companyDto;
	
	@RequestMapping(value="/updateComputer", method = RequestMethod.GET)
	public Computer get(ModelMap model, Long idUpdate){	
		System.out.println("l'id est "+idUpdate);
		List<String> companies = new ArrayList<String>();
		Computer cp = null;
		if((idUpdate != null) && (!idUpdate.equals(""))){
			cp = computerService.find(idUpdate);
			String comp  = computerDto.fromDTO(cp);
			companies = companyDto.fromDTOList( companyService.getAll(0));		
			model.addAttribute("companies", companies);
			model.addAttribute("comp", comp);
			return cp;
		}
		else{
			System.out.println("l'id du computer n'a pas été reçu par la servlet");
		}
		return cp;
	}
	
	@RequestMapping(value="/save/updateComputer", method = RequestMethod.POST)
	public ModelAndView post(ModelMap model, @ModelAttribute ComputerDTO computer){
		boolean success = computerService.update(computer.toDTO(""));
		return new ModelAndView("redirect:/dashboard?update="+success);
	}
}
