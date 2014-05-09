package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import com.excilys.computerdatabase.connection.ConnectionManager;
import com.excilys.computerdatabase.dao.DAOFactory;
import com.excilys.computerdatabase.dao.UtilDAO;
import com.excilys.computerdatabase.model.Computer;

@Service
public class ComputerService {

 
	@Autowired
	private ConnectionManager connectionManager;
	
	@Autowired
	private DAOFactory daofactory;
	
	@Autowired
	private UtilDAO utildao;
	
	
	public void setConnectionManager(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	final Logger logger = LoggerFactory.getLogger(ComputerService.class);
	public ComputerService(){	
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// print logback's internal status
		StatusPrinter.print(lc);
		//logger.info("Entering application.");
	}

	

	public boolean insert(Computer o) {	
		boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		try {
			daofactory.getComputerDAO().create(o, connection);
			UtilService.logInfo(" inséré avec succés", connection);
			success = true;
			return success;
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
			return success;
		}
		finally{
		utildao.endTransaction(success, connection);
		connectionManager.disconnect();
		}
		
	}

	public boolean update(Object o) {
		boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		try {
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
		finally{
			utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
	}

	public boolean delete(Long id) {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		boolean success = false;
		try {
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
		finally{
			utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
	}

	public ArrayList<Computer> getAll(int order) {
		boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			list=  daofactory.getComputerDAO().getAll(order, connection);
			success = true;
			return list;			
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
		}
		finally{
			utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return null;
	}

	public ArrayList<Computer> filterByName(String name, int order) {
		boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		ArrayList<Computer> list = new ArrayList<Computer>();
		try {
			list = daofactory.getComputerDAO().filterByName(name,order, connection);	
			success = true;
			return list;	
		}catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return list;
	}

	public Computer find(Long id)  {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		utildao.beginTransaction();
		Computer result = new Computer.ComputerBuilder().build();
		boolean success = false ;
		try {
			result =  daofactory.getComputerDAO().find(id, connection);
			success = true;
			if(result == null ){
				System.out.println("resultat retournee par dao null");
			}
			return result;	
		} catch (SQLException e) {	
			logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return result;
	}
}