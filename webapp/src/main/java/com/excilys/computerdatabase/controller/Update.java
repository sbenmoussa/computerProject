package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.DTO.CompanyDTO;
import com.excilys.computerdatabase.DTO.ComputerDTO;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
public class Update {

	@Autowired
	private ComputerService computerService;
       
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	ComputerMapper mapperComputer ;
	
	@Autowired
	CompanyMapper mapperCompany ;
	
	@RequestMapping(value="/updateComputer", method = RequestMethod.GET)
	public Computer get(ModelMap model, Long idUpdate){	
		System.out.println("l'id est "+idUpdate);
		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
		Computer cp = null;
		if((idUpdate != null) && (!idUpdate.equals(""))){
			cp = computerService.find(idUpdate);
			ComputerDTO comp  = mapperComputer.toDTO(cp);
			companies = mapperCompany.toDTOList( companyService.getAll(0,0));
			model.addAttribute("companies", companies);
			model.addAttribute("comp", comp);
			return cp;
		}
		else{
			System.out.println("l'id du computer n'a pas été reçu par la servlet");
		}
		return cp;
	}
	
	@RequestMapping(value="/updateComputer", method = RequestMethod.POST)
	public String post(ModelMap model, @ModelAttribute @Valid ComputerDTO computer, BindingResult result){
		boolean success = false;
		if (result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					System.out.println(fieldError.getField() + ":"+ fieldError.getCode()+"  , "+fieldError.getDefaultMessage());
				}
			}
		} else {
			success = computerService.update(mapperComputer.fromDTO(computer));			
		}
		
		model.addAttribute("update", success);
		return "forward:/dashboard";
		//return new ModelAndView("redirect:/dashboard?update="+success);
	}
}
