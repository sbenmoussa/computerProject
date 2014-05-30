package com.excilys.computerdatabase.dao;



import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.QCompany;
import com.excilys.computerdatabase.model.QComputer;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.HibernateUpdateClause;

@Repository
public class ComputerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void create(Computer object) throws SQLException {	
		//Querydsl supports only the DML clauses JPQL supports: UPDATE and DELETE
		//There is no JPQL equivalent  to define insert
		sessionFactory.getCurrentSession().save(object) ;
	}


	public void update(Computer object) throws SQLException {	 
		QComputer computer = QComputer.computer;
		new HibernateUpdateClause(sessionFactory.getCurrentSession(), computer).where(computer.id.eq(object.getId()))
	    .set(computer.name, object.getName())
	    .set(computer.introduced, object.getIntroduced())
	    .set(computer.discontinued, object.getDiscontinued())
	    .set(computer.company, object.getCompany())
	    .execute();  
	}

	public void delete(long id) throws SQLException {	
		QComputer computer = QComputer.computer;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), computer).where(computer.id.eq(id)).execute();
	}

	public Computer find(long id) throws SQLException {
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		return query.from(computer).where(computer.id.eq(id)).uniqueResult(computer);
	}

	public List<Computer> getAll(int order, int page) throws SQLException {		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		switch(order){
		case 0 :return query.from(computer)
			    .leftJoin(computer.company, company)
			    .orderBy(computer.name.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 1: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .orderBy(computer.introduced.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 2: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .orderBy(computer.discontinued.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 3: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .orderBy(computer.company.id.asc()).limit(10).offset(page*10)
			    .list(computer);
		default: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .orderBy(computer.name.asc()).limit(10).offset(page*10)
			    .list(computer);
		}
	}

	public List<Computer> filterByName(String name, int order, int page) throws SQLException {
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		switch(order){
		case 0 :return query.from(computer)
			    .leftJoin(computer.company, company)  
			    .where(computer.name.like("%"+name+"%"))
			    .orderBy(computer.name.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 1: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .where(computer.name.like("%"+name+"%"))
			    .orderBy(computer.introduced.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 2: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .where(computer.name.like("%"+name+"%"))
			    .orderBy(computer.discontinued.asc()).limit(10).offset(page*10)
			    .list(computer);
		case 3: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .where(computer.name.like("%"+name+"%"))
			    .orderBy(computer.company.id.asc()).limit(10).offset(page*10)
			    .list(computer);
		default: return query.from(computer)
			    .leftJoin(computer.company, company)
			    .where(computer.name.like("%"+name+"%"))
			    .orderBy(computer.name.asc()).limit(10).offset(page*10)
			    .list(computer);
		}
	}

	public long count(String name) throws SQLException {
		
		if(name.equals("") ||( name == null)){
			return (long) sessionFactory.getCurrentSession().createCriteria(Computer.class).setProjection( Projections.rowCount() ).uniqueResult();
		}
		return (long) sessionFactory.getCurrentSession().createCriteria(Computer.class).setProjection( Projections.rowCount() ).add(Restrictions.like("name","%"+name+"%")).uniqueResult();
	}
}
