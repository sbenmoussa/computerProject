package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.dao.LogDAO;
import com.excilys.computerdatabase.model.Company;

@Service
@Transactional
public class CompanyService {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private LogDAO logDao;
	
	@Transactional(readOnly = true)
	public List<Company> getAll(int order, int page) {
		try {
			return  companyDao.getAll(order, page);	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}
}
