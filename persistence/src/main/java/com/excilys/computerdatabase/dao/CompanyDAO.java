package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.DAO;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements DAO<Company>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean create(Company object) throws SQLException {	
		return sessionFactory.getCurrentSession().save(object) != null;
	}


	public void update(Company object) throws SQLException {
		sessionFactory.getCurrentSession().update(object);
	}


	public boolean delete(long id) throws SQLException {
		return sessionFactory.getCurrentSession().createQuery("delete from Company as company where company.id= "+id).executeUpdate() ==1;
	}


	public Company find(long id) throws SQLException {
		return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Company> getAll(int order, int page) throws SQLException {
		String query = "from Company as company";
		return ((List<Company>) sessionFactory.getCurrentSession().createQuery(query).list());
	}

	@SuppressWarnings("unchecked")
	public List<Company> filterByName(String name, int order, int page)
			throws SQLException {
		String query = "from Company as company  where company.name like '%"+name+"%'";
		return (List<Company>) sessionFactory.getCurrentSession().createQuery(query).list();
	}
	
	@Override
	public long count(String name) throws SQLException {
		return (long) sessionFactory.getCurrentSession().createQuery("select count(company) from Company as company ").uniqueResult();
	}

}
