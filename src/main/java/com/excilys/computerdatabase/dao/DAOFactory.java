package com.excilys.computerdatabase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.modele.Company;
import com.excilys.computerdatabase.modele.Computer;

@Repository
public class DAOFactory {

	@Autowired
	private ComputerDAO computerdao;
	@Autowired
	private CompanyDAO companydao;
	
	public void setComputerDAO(ComputerDAO computerdao){
		this.computerdao = computerdao;
	}
	
	public void setCompanyDAO(CompanyDAO companydao){
		this.companydao = companydao;
	}
	
	public DAOFactory(){
	}
	
	public  DAO<Computer> getComputerDAO(){
		return computerdao;
		
	}
	
	public   DAO<Company> getCompanyDAO(){
		return companydao;
		
	}
	
}
