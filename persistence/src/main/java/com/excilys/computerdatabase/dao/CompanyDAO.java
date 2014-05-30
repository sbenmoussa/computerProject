package com.excilys.computerdatabase.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;

@Repository
public interface CompanyDAO extends CrudRepository<Company, Long>{
	
	public List<Company> findAll();

}
