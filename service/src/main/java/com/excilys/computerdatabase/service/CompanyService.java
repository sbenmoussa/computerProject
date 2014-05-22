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
	private ComputerDAO computerDao;
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private LogDAO logDao;

	@Transactional
	public void insert(Company company) {	
		try {
			companyDao.create(company);
			logDao.logInfo(" inséré avec succés");
		} catch (SQLException e) {
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional
	public void update(Company o) {
		try {
			companyDao.update(o);
			logDao.logInfo("mis a jour avec succés");
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			companyDao.delete(id);
			logDao.logInfo("supprimé avec succés");
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional(readOnly = false)
	public List<Company> getAll(int order, int page) {
		try {
			return  companyDao.getAll(order, page);	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional(readOnly = true)
	public List<Company> filterByName(String name, int order, int page) {
		try {
			return  companyDao.filterByName(name,order, page);
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional(readOnly = true)
	public Company find(Long id)  {
		try {
			return  companyDao.find(id);
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}
}
