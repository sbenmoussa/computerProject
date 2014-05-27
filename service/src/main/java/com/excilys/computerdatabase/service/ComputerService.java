package com.excilys.computerdatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import com.excilys.computerdatabase.dao.CompanyDAO;
import com.excilys.computerdatabase.dao.ComputerDAO;
import com.excilys.computerdatabase.dao.LogDAO;
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
	
	@Autowired
	private LogDAO logDao;
	
	
	public ComputerService(){	
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}

	
	@Transactional
	public boolean insert(Computer o) {	
		try{
			computerDao.create(o);
			return true;	
		}catch(SQLException e){
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional
	public boolean update(Computer o) {
		try {
			computerDao.update(o);
			logger.info(" mis à jour avec succés");
			return true;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional
	public boolean delete(Long id) {
		try {
			computerDao.delete(id);
			logger.debug("suppression ok");
			return true;
		}  catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional (readOnly=true)
	public List<Computer> getAll(int order, int page) {
		logger.debug("transaction status dans le service ");
		List<Computer> list = null;
		try {
			list =  computerDao.getAll(order, page);		
			return list;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional (readOnly = true)
	public List<Computer> filterByName(String name, int order, int page) {
		List<Computer> list = new ArrayList<Computer>();
		try {
			list = computerDao.filterByName(name,order, page);	
			return list;	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}

	@Transactional (readOnly = true)
	public Computer find(Long id)  {
		Computer result = new Computer.ComputerBuilder().build();
		try {
			result =  computerDao.find(id);
			if(result == null ){
				System.out.println("resultat retournee par dao null");
			}
			return result;	
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}
	
	@Transactional (readOnly = true)
	public long getTotal(String name){
		try {
			return computerDao.count(name);
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			throw new RuntimeException("An error has occured",e);
		}
	}
}