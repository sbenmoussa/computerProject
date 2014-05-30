package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.QCompany;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class CompanyDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Company> getAll(int order, int page) throws SQLException {
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QCompany company = QCompany.company;
		return query.from(company)
			    .list(company);
	}

}
