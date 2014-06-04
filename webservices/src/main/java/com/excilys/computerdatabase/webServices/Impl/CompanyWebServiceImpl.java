package com.excilys.computerdatabase.webServices.Impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.webServices.CompanyWebService;

@WebService
public class CompanyWebServiceImpl  implements CompanyWebService{
		
	@Autowired
	private CompanyService companyService;
	
	public List<Company> getAll() {
		return  companyService.getAll();	
	}
}
