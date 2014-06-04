package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.model.Company;

@Service
@Transactional
public class CompanyService {
		
	@Autowired
	private CompanyDAO companyDao;
	
	@Secured(value={"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_ADMIN", "ROLE_USER"})
	@Transactional(readOnly = true)
	public List<Company> getAll() {
		return  companyDao.findAll();	
	}
}
