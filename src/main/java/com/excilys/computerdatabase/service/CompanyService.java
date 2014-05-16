package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.connection.ConnectionManager;
import com.excilys.computerdatabase.dao.DAOFactory;
//import com.excilys.computerdatabase.dao.UtilDAO;
import com.excilys.computerdatabase.model.Company;

@Service
@Transactional
public class CompanyService {

 
	@Autowired
	private ConnectionManager connectionManager;
	
	@Autowired
	private DAOFactory daofactory;
	
	//@Autowired
	//private UtilDAO utildao;
	
	public void setConnectionManager(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	public CompanyService(){
	}


	public void insert(Company company) {	
		//boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		try {
			daofactory.getCompanyDAO().create(company, connection);	
			UtilService.logInfo(" inséré avec succés", connection);
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
	}

	public void update(Company o) {
		//boolean success = false;
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		try {
			daofactory.getCompanyDAO().update(o, connection);
			UtilService.logInfo("mis à jour avec succés", connection);
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
	}

	public void delete(Long id) {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		//boolean success = false;
		try {
			daofactory.getCompanyDAO().delete(id, connection);
			UtilService.logInfo(" supprimé avec succés", connection);
			//logger.debug("suppression ok");
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
	}

	@Transactional(readOnly = true)
	public ArrayList<Company> getAll(int order) {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		//boolean success = false;
		try {
			//success = true;
			return  daofactory.getCompanyDAO().getAll(order, connection);	
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return null;
	}

	@Transactional(readOnly = true)
	public ArrayList<Company> filterByName(String name, int order) {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		//boolean success = false;
		try {
			//success = true;
			return  daofactory.getCompanyDAO().filterByName(name,order, connection);
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Company find(Long id)  {
		connectionManager.connect();
		Connection connection = connectionManager.getConnection();
		//utildao.beginTransaction();
		//boolean success = false ;
		try {
			//success = true;
			return  daofactory.getCompanyDAO().find(id, connection);
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			//utildao.endTransaction(success, connection);
			connectionManager.disconnect();
		}
		return null;
	}
}
