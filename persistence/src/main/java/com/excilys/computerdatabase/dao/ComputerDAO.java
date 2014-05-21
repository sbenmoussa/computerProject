package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	private JdbcTemplate jt;
	
	public ComputerDAO(){
		
	}

	public boolean create(Computer object) throws SQLException {
		jt  = new JdbcTemplate(datasource);		
		String query = "insert into computer(name, introduced, discontinued, company_id) values(?,?,?,?)";	
		long id1 = object.getCompany().getId();
		System.out.println(id1);
		Long id= ((object.getCompany().getId()==0)? null : object.getCompany().getId());
		int result= jt.update(query, new Object[]{object.getName(), new java.sql.Date(object.getIntroduced().toDate().getTime()), new java.sql.Date(object.getDiscontinued().toDate().getTime()), id});
		return result == 1;
	}


	public boolean update(Computer object) throws SQLException {
		jt  = new JdbcTemplate(datasource);
		String query = "update computer set name = ? , introduced = ?, discontinued = ?, company_id = ? where id=?";
		long id= (object.getCompany().getId()==0)? null : object.getCompany().getId();
		int result= jt.update(query, new Object[]{object.getName(), new java.sql.Date(object.getIntroduced().toDate().getTime()), new java.sql.Date(object.getDiscontinued().toDate().getTime()), id,object.getId()});
		return result == 1;
	}

	public boolean delete(long id) throws SQLException {	
		jt  = new JdbcTemplate(datasource);
		String query = "delete from computer where id=?";
		int result= jt.update(query, new Object[]{id});
		return result == 1;
	}

	public Computer find(long id) throws SQLException {
		jt  = new JdbcTemplate(datasource);	
		Computer result  = new Computer.ComputerBuilder().build(); 		
		String query = "select  cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa, cm.id as idcompa from computer as cp join company as cm on cp.company_id = cm.id where cp.id="+id;
		result = jt.query(query, new ResultSetExtractor<Computer>(){
			@Override
			public Computer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Computer result  = new Computer.ComputerBuilder().build(); 
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
				return result;
			}
			
		});
		result.setId(id);
		return result;
	}

	public List<Computer> getAll(int order) throws SQLException {
		jt  = new JdbcTemplate(datasource);
		String query ="select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id ";

		switch(order){
		case 0 : query += " order by cp.name";
		break;
		case 1: query += " order by intr";
		break;
		case 2: query += " order by dis";
		break;
		case 3:query += " order by compa";
		break;
		}	
		List<Computer> computeresultSet = jt.query(query, new RowMapper<Computer>(){
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Company com = new Company.CompanyBuilder(rs.getString("compa")).build();
				DateTime intr = null;
				DateTime disc = null;
				if (rs.getTimestamp("intr") != null) {
					intr = new DateTime(rs.getTimestamp("intr").getTime());
				}
	
				if (rs.getTimestamp("dis") != null) {
					disc = new DateTime(rs.getTimestamp("dis").getTime());
				}
				Computer c = new Computer.ComputerBuilder(rs.getLong("id"),rs.getString("namecp"), intr, disc,com).build();
				return c;
			}
		});	
		return computeresultSet;
	}

	
	public List<Computer> filterByName(String name, int order) throws SQLException {
		jt  = new JdbcTemplate(datasource);
		String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like ?";

		switch(order){
		case 0 : query += " order by cp.name";
		break;
		case 1: query += " order by intr";
		break;
		case 2: query += " order by dis";
		break;
		case 3:query += " order by compa";
		break;
		}
		List<Computer> computeresultSet = jt.query(query, new Object[]{new String("%"+name+"%")},new RowMapper<Computer>(){
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Company com = new Company.CompanyBuilder(rs.getString("compa")).build();
				DateTime intr = null;
				DateTime disc = null;
				if (rs.getTimestamp("intr") != null) {
					intr = new DateTime(rs.getTimestamp("intr").getTime());
				}
	
				if (rs.getTimestamp("dis") != null) {
					disc = new DateTime(rs.getTimestamp("dis").getTime());
				}
				Computer c = new Computer.ComputerBuilder(rs.getLong("id"),rs.getString("namecp"), intr, disc,com).build();
				return c;
			}
		});	
		return computeresultSet;
	}

}
