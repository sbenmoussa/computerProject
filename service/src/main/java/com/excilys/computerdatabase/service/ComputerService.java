package com.excilys.computerdatabase.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.model.Computer;

@Service
@Transactional
public class ComputerService {
	
	final Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	@Resource
	@Autowired
	private ComputerDAO computerDao;
	
	@Resource
	@Autowired
	private CompanyDAO companyDao;
	
	
	public ComputerService(){	
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public boolean insert(Computer o) {	
		Computer c = computerDao.save(o);
		return c !=null;	
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public boolean update(Computer o) {
		Computer c = computerDao.save(o);
		return c !=null;
	}

	@Secured("ROLE_ADMIN")
	@Transactional
	public boolean delete(Long id) {
		computerDao.delete(id);
		logger.debug("suppression ok");
		return true;
	}

	@Secured(value={"ROLE_ADMIN", "ROLE_USER"})
	@Transactional (readOnly=true)
	public   Page<Computer> getAll(String order, int page) {	
		System.out.println("numéro de page "+page);
		return computerDao.findAll(new PageRequest(page, 10, Sort.Direction.ASC, order));	
	}

	@Secured(value={"ROLE_ADMIN", "ROLE_USER"})
	@Transactional (readOnly = true)
	public  Page<Computer> filterByName(String name, String order, int page) {
		return computerDao.findAllByName(name,new PageRequest(page, 10, Sort.Direction.ASC, order));	
	}

	@Secured(value={"ROLE_ADMIN", "ROLE_USER"})
	@Transactional (readOnly = true)
	public Computer find(Long id)  {
		return computerDao.findOne(id);
	}
	
}