package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import com.excilys.computerdatabase.connection.ConnectionManager;
import com.excilys.computerdatabase.dao.DAOFactory;
import com.excilys.computerdatabase.dao.UtilDAO;
import com.excilys.computerdatabase.modele.Computer;


public enum ComputerService {

	INSTANCE;
 
	final Logger logger = LoggerFactory.getLogger(ComputerService.class);
	private ComputerService(){	
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// print logback's internal status
		StatusPrinter.print(lc);
		//logger.info("Entering application.");
	}

	

	public void insert(Computer o) {	
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		try {
			DAOFactory.INSTANCE.getComputerDAO().create(o, connection);
			UtilService.logInfo(" inséré avec succés", connection);
			success = true;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
	}

	public void update(Object o) {
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		try {
			DAOFactory.INSTANCE.getComputerDAO().update((Computer)o, connection);
			UtilService.logInfo(" mis à jour avec succés", connection);
			logger.info(" mis à jour avec succés");
			success = true;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
	}

	public void delete(Long id) {
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		boolean success = false;
		try {
			 DAOFactory.INSTANCE.getComputerDAO().delete(id, connection);
			UtilService.logInfo(" supprimé avec succés", connection);
			logger.debug("suppression ok");
			success = true;
		}  catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
	}

	public ArrayList<Computer> getAll(int order) {
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			list=  DAOFactory.INSTANCE.getComputerDAO().getAll(order, connection);
			success = true;
			return list;			
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}

	public ArrayList<Computer> filterByName(String name, int order) {
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			list =  DAOFactory.INSTANCE.getComputerDAO().filterByName(name,order, connection);	
			success = true;
			return list;	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}

	public Computer find(Long id)  {
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		Computer result = new Computer.ComputerBuilder().build();
		boolean success = false ;
		try {
			result =  DAOFactory.INSTANCE.getComputerDAO().find(id, connection);
			success = true;
			return result;	
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}
}