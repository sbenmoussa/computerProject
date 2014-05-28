package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean create(Computer object) throws SQLException {		
		return sessionFactory.getCurrentSession().save(object) != null;
	}


	public void update(Computer object) throws SQLException {	 
		sessionFactory.getCurrentSession().update(object);
	}

	public boolean delete(long id) throws SQLException {	
		return sessionFactory.getCurrentSession().createQuery("delete from Computer computer where computer.id= "+id).executeUpdate() ==1;
	}

	public Computer find(long id) throws SQLException {
		return (Computer) sessionFactory.getCurrentSession().createCriteria(Computer.class).add(Restrictions.eq("id",id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Computer> getAll(int order, int page) throws SQLException {
		String search = "name";
		switch(order){
		case 0 :search = "name";
		break;
		case 1: search = "introduced";
		break;
		case 2: search = "discontinued";
		break;
		case 3: search = "company.name";
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createCriteria(Computer.class).addOrder( Order.asc(search)).setFirstResult(page*10).setMaxResults(10).list();
	}

	@SuppressWarnings("unchecked")
	public List<Computer> filterByName(String name, int order, int page) throws SQLException {
		String search = "name";
		switch(order){
		case 0 :search = "name";
		break;
		case 1: search = "introduced";
		break;
		case 2: search = "discontinued";
		break;
		case 3: search = "company";
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createCriteria(Computer.class).addOrder( Order.asc(search)).add(Restrictions.like("name","%"+name+"%")).setFirstResult(page*10).setMaxResults(10).list();
	}

	public long count(String name) throws SQLException {
		
		if(name.equals("") ||( name == null)){
			return (long) sessionFactory.getCurrentSession().createCriteria(Computer.class).setProjection( Projections.rowCount() ).uniqueResult();
		}
		return (long) sessionFactory.getCurrentSession().createCriteria(Computer.class).setProjection( Projections.rowCount() ).add(Restrictions.like("name","%"+name+"%")).uniqueResult();
	}
}
