package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import com.excilys.computerdatabase.dao.DAOFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import com.excilys.computerdatabase.model.Computer;
import com.jolbox.bonecp.BoneCPDataSource;

@Service
@Transactional
public class ComputerService {

	@Autowired
	private BoneCPDataSource datasource;
	
	@Autowired
	private DAOFactory daofactory;
	
	final Logger logger = LoggerFactory.getLogger(ComputerService.class);
	public ComputerService(){	
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// print logback's internal status
		StatusPrinter.print(lc);
		//logger.info("Entering application.");
	}

	
	@Transactional(readOnly = false)
	public boolean insert(Computer o) {	
		boolean success = false;		
		//utildao.beginTransaction();
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getComputerDAO().create(o, connection);
			UtilService.logInfo(" inséré avec succés", connection);
			success = true;
			return success;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			return success;
		}
	}

	@Transactional()
	public boolean update(Object o) {
		boolean success = false;
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getComputerDAO().update((Computer)o, connection);
			UtilService.logInfo(" mis à jour avec succés", connection);
			logger.info(" mis à jour avec succés");
			success = true;
			return success;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			return success;
		}
	}

	@Transactional()
	public boolean delete(Long id) {
		boolean success = false;
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getComputerDAO().delete(id, connection);
			UtilService.logInfo(" supprimé avec succés", connection);
			logger.debug("suppression ok");
			success = true;
			return success;
		}  catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			return success;
		}
	}

	@Transactional(readOnly = true)
	public ArrayList<Computer> getAll(int order) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			list=  daofactory.getComputerDAO().getAll(order, connection);
			return list;			
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly = true)
	public ArrayList<Computer> filterByName(String name, int order) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			list = daofactory.getComputerDAO().filterByName(name,order, connection);	
			return list;	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		return list;
	}

	@Transactional(readOnly = true)
	public Computer find(Long id)  {
		Computer result = new Computer.ComputerBuilder().build();
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			result =  daofactory.getComputerDAO().find(id, connection);
			if(result == null ){
				System.out.println("resultat retournee par dao null");
			}
			return result;	
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		return result;
	}
}