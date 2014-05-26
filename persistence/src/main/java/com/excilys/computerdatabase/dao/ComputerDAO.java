package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAO implements DAO<Computer>{

	@Autowired
	private BoneCPDataSource datasource;
	
	@Autowired
	private JdbcTemplate jt;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//private Session session;

	public boolean create(Computer object) throws SQLException {		
		String query = "insert into computer(name, introduced, discontinued, company_id) values(?,?,?,?)";	
		Long id= ((object.getCompany().getId()==0)? null : object.getCompany().getId());
		return jt.update(query, new Object[]{object.getName(), new java.sql.Date(object.getIntroduced().toDate().getTime()), new java.sql.Date(object.getDiscontinued().toDate().getTime()), id}) == 1;
	}


	public boolean update(Computer object) throws SQLException {
		String query = "update computer set name = ? , introduced = ?, discontinued = ?, company_id = ? where id=?";
		long id= (object.getCompany().getId()==0)? null : object.getCompany().getId();
		return jt.update(query, new Object[]{object.getName(), new java.sql.Date(object.getIntroduced().toDate().getTime()), new java.sql.Date(object.getDiscontinued().toDate().getTime()), id,object.getId()}) == 1;		 
	}

	public boolean delete(long id) throws SQLException {	
		String query = "delete from computer where id=?";
		return jt.update(query, new Object[]{id}) == 1;
	}

	public Computer find(long id) throws SQLException {	
		Computer result  = new Computer.ComputerBuilder().build(); 	
		String query = "select  cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa, cm.id as idcompa from computer as cp left outer join company as cm on cp.company_id = cm.id where cp.id="+id;
		result = jt.query(query, new ResultSetExtractor<Computer>(){
			@Override
			public Computer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Computer result  = new Computer.ComputerBuilder().build(); 
				if(rs.next()){
					result.setName(rs.getString("namecp"));
					Company company = new Company.CompanyBuilder(rs.getLong("idcompa"),rs.getString("compa")).build();
					DateTime intr = null;
					DateTime disc = null;
					if (rs.getTimestamp("intr") != null) {
						intr = new DateTime(rs.getTimestamp("intr").getTime());
					}
					if (rs.getTimestamp("dis") != null) {
						disc = new DateTime(rs.getTimestamp("dis").getTime());
					}
					result.setIntroduced(intr);
					result.setDiscontinued(disc);
					result.setCompany(company);
				}
				else{
					System.out.print("il n'ya pas de résultats dans result set pour id= ");
				}				
				return result;
			}		
		});
		System.out.println(id);
		result.setId(id);
		return result;
	}

	public List<Computer> getAll(int order, int page) throws SQLException {
		String query = "from Computer as computer left outer join fetch computer.company as company ";
		switch(order){
		case 0 : query += " order by computer.name";
		break;
		case 1: query += " order by computer.introduced";
		break;
		case 2: query += " order by computer.discontinued";
		break;
		case 3:query += " order by computer.company.name";
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createQuery(query).setFirstResult(page*10).setMaxResults(10).list();
	}

	
	public List<Computer> filterByName(String name, int order, int page) throws SQLException {
		System.out.println("nom du computer recherché "+name);
		String query = "from Computer as computer left outer join fetch computer.company as company where computer.name like '%"+name+"%' ";
		switch(order){
		case 0 : query += " order by computer.name";
		break;
		case 1: query += " order by computer.introduced";
		break;
		case 2: query += " order by computer.discontinued";
		break;
		case 3:query += " order by computer.company.name";
		break;
		}
		return (List<Computer>) sessionFactory.getCurrentSession().createQuery(query).setFirstResult(page*10).setMaxResults(10).list();
	}

	@Override
	public int count(String name) throws SQLException {
//		if(name.equals("") ||( name == null)){
//			return sessionFactory.getCurrentSession().createQuery("select count(computer) from Computer as computer left outer join fetch computer.company as company where computer.name like '%"+name+"%' ");
//		}
//		return sessionFactory.getCurrentSession().createQuery("select count(computer) from Computer as computer left outer join fetch computer.company as company");
//	}
		return 555;
	}
}
