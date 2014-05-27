package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;

@Repository
public class ComputerDAO implements DAO<Computer>{
	
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
		return (Computer) sessionFactory.getCurrentSession().get(Computer.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Computer> getAll(int order, int page) throws SQLException {
		StringBuilder query = new StringBuilder("from Computer as computer left outer join fetch computer.company as company ");
		switch(order){
		case 0 : query.append(" order by computer.name");
		break;
		case 1: query.append(" order by computer.introduced");
		break;
		case 2: query.append(" order by computer.discontinued");
		break;
		case 3:query.append(" order by computer.company.name");
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createQuery(query.toString()).setFirstResult(page*10).setMaxResults(10).list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Computer> filterByName(String name, int order, int page) throws SQLException {
		System.out.println("nom du computer recherché "+name);
		StringBuilder query= new StringBuilder("from Computer as computer left outer join fetch computer.company as company where computer.name like '%"+name+"%' ");
		switch(order){
		case 0 : query.append(" order by computer.name") ;
		break;
		case 1: query.append(" order by computer.introduced");
		break;
		case 2: query.append(" order by computer.discontinued");
		break;
		case 3:query.append(" order by computer.company.name");
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createQuery(query.toString()).setFirstResult(page*10).setMaxResults(10).list();
	}

	@Override
	public long count(String name) throws SQLException {
		if(name.equals("") ||( name == null)){
			return (long) sessionFactory.getCurrentSession().createQuery("select count(computer) from Computer as computer ").uniqueResult();
		}
		return (long) sessionFactory.getCurrentSession().createQuery("select count(computer) from Computer as computer where computer.name like '%"+name+"%'").uniqueResult();
	}
}
