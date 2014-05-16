package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.DAOFactory;
import com.excilys.computerdatabase.model.Company;
import com.jolbox.bonecp.BoneCPDataSource;

@Service
@Transactional
public class CompanyService {

 
	@Autowired
	private BoneCPDataSource datasource;
	
	@Autowired
	private DAOFactory daofactory;

	@Transactional()
	public void insert(Company company) {	
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getCompanyDAO().create(company, connection);	
			UtilService.logInfo(" inséré avec succés", connection);
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
	}

	@Transactional()
	public void update(Company o) {
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getCompanyDAO().update(o, connection);
			UtilService.logInfo("mis à jour avec succés", connection);
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
	}

	@Transactional()
	public void delete(Long id) {
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			daofactory.getCompanyDAO().delete(id, connection);
			UtilService.logInfo(" supprimé avec succés", connection);
			//logger.debug("suppression ok");
			//success = true;
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public ArrayList<Company> getAll(int order) {
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			return  daofactory.getCompanyDAO().getAll(order, connection);	
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly = true)
	public ArrayList<Company> filterByName(String name, int order) {
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			return  daofactory.getCompanyDAO().filterByName(name,order, connection);
		} catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Company find(Long id)  {
		try {
			Connection connection = DataSourceUtils.getConnection(datasource);
			return  daofactory.getCompanyDAO().find(id, connection);
		}catch (SQLException e) {	
			//logger.error("transaction annulée: "+e.getMessage());
			System.out.println("transaction annulée: "+e.getMessage());
		}
		return null;
	}
}
