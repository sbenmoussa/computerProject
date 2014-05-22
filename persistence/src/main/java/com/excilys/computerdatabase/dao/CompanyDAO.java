package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.DAO;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements DAO<Company>{

	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jt ;
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	public boolean create(Company object) throws SQLException {	
		String query = "insert into company(name) values(?)";
		return jt.update(query, new Object[]{object.getName()}) == 1;
	}


	public boolean update(Company object) throws SQLException {
		String query = "update company set name = ?  where id=?";
		return jt.update(query, new Object[]{object.getName(), object.getId()}) == 1;
	}


	public boolean delete(long id) throws SQLException {
		String query = "delete from company where id=?";
		return jt.update(query, new Object[]{id}) == 1;
	}


	public Company find(long id) throws SQLException {
		Company result  = new Company.CompanyBuilder().build();  		
		String query = "select  cm.name as name from company as cm where cm.id=?";
		result = jt.query(query, new Object[]{id},new ResultSetExtractor<Company>(){
			@Override
			public Company extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Company result  = new Company.CompanyBuilder().build();
				if(rs.next()){
					result.setName(rs.getString("name"));
				}
				return result;			
			}});
		result.setId(id);
		return result;
	}

	public List<Company> getAll(int order, int page) throws SQLException {
		String query = "select cm.id as id, cm.name as compa from company as cm";
		List<Company> companies = jt.query(query, new RowMapper<Company>(){
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException{
				return new Company.CompanyBuilder(rs.getString("compa")).id(rs.getLong("id")).build();
			}
		});	
		return companies;
	}

	public List<Company> filterByName(String name, int order, int page)
			throws SQLException {
		String query = "select cp.id as id, cp.name as name from company as cp where cp.name like ?";
		List<Company> companyresultSet = jt.query(query, new Object[]{new String("%"+name+"%")},new RowMapper<Company>(){
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException{
				Company com = new Company.CompanyBuilder(rs.getLong("id"),rs.getString("name")).build();
				return com;
			}
		});	
		return companyresultSet;
	}
	
	@Override
	public int count(String name) throws SQLException {
		return jt.queryForInt("select count(*) from company");
	}

}
