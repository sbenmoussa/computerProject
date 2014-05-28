package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Company> getAll(int order, int page) throws SQLException {
		return (List<Company>) sessionFactory.getCurrentSession().createCriteria(Company.class).list();
	}

}
