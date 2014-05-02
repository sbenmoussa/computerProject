package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.computerdatabase.connection.ConnectionManager;
import com.excilys.computerdatabase.dao.DAOFactory;
import com.excilys.computerdatabase.dao.UtilDAO;
import com.excilys.computerdatabase.modele.Company;

public enum CompanyService {

	INSTANCE;
 
	private CompanyService(){
	}


	public void insert(Company company) {	
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		try {
			 DAOFactory.INSTANCE.getCompanyDAO().create(company, connection);	
			UtilService.logInfo(" inséré avec succés", connection);
			success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
	}

	public void update(Company o) {
		boolean success = false;
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		try {
			 DAOFactory.INSTANCE.getCompanyDAO().update(o, connection);
			UtilService.logInfo("mis à jour avec succés", connection);
			success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
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
			 DAOFactory.INSTANCE.getCompanyDAO().delete(id, connection);
			UtilService.logInfo(" supprimé avec succés", connection);
			//logger.debug("suppression ok");
			success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
	}

	public ArrayList<Company> getAll(int order) {
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		boolean success = false;
		try {
			success = true;
			return  DAOFactory.INSTANCE.getCompanyDAO().getAll(order, connection);	
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}

	public ArrayList<Company> filterByName(String name, int order) {
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		boolean success = false;
		try {
			success = true;
			return  DAOFactory.INSTANCE.getCompanyDAO().filterByName(name,order, connection);
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}

	public Company find(Long id)  {
		ConnectionManager.INSTANCE.connect();
		Connection connection = ConnectionManager.INSTANCE.getConnection();
		UtilDAO.beginTransaction();
		boolean success = false ;
		try {
			success = true;
			return  DAOFactory.INSTANCE.getCompanyDAO().find(id, connection);
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			UtilDAO.endTransaction(success, connection);
			ConnectionManager.INSTANCE.disconnect();
		}
		return null;
	}
}
