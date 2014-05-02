package com.excilys.computerdatabase.dao;

import com.excilys.computerdatabase.modele.Company;
import com.excilys.computerdatabase.modele.Computer;

public enum DAOFactory {

	INSTANCE;
	
	private DAOFactory(){
	}
	
	public  DAO<Computer> getComputerDAO(){
		return ComputerDAO.INSTANCE;
		
	}
	
	public   DAO<Company> getCompanyDAO(){
		return CompanyDAO.INSTANCE;
		
	}
	
}
