package com.excilys.computerdatabase.webServices.Impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.webServices.CompanyWebService;

@Component
@Path("/CompanyWebService")
public class CompanyWebServiceImpl  implements CompanyWebService{
		
	@Autowired
	private CompanyService companyService;
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Company> getAll() {
		return  companyService.getAll();	
	}
}
