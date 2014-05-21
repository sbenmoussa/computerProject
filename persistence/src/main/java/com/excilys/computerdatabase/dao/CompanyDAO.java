package com.excilys.computerdatabase.dao;


import static com.excilys.computerdatabase.dao.DAOUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;



import com.excilys.computerdatabase.dao.DAO;
import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements DAO<Company>{

	@Autowired
	private DataSource datasource;

	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	public CompanyDAO(){
	}
	
	public boolean create(Company object) throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "insert into company(name) values(?)";
		Connection connection = DataSourceUtils.getConnection(datasource);
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, object.getName());
		int result = preparedStatement.executeUpdate();
		close(preparedStatement);
		switch(result){
		case 1 : return true;
		default : return false;
		}
	}


	public boolean update(Company object) throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "update company set name = ?  where id=?";
		Connection connection = DataSourceUtils.getConnection(datasource);
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, object.getName());
		preparedStatement.setLong(2, object.getId());
		int result = preparedStatement.executeUpdate();
		close(preparedStatement);
		switch(result){
		case 1 : return true;
		default : return false;
		}
	}


	public boolean delete(long id) throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "delete from company where id=?";
		Connection connection = DataSourceUtils.getConnection(datasource);
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);
		int result = preparedStatement.executeUpdate();
		close(preparedStatement);
		switch(result){
		case 1 : return true;
		default : return false;
		}
	}


	public Company find(long id) throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String query = "";
		Company result  = new Company.CompanyBuilder().build(); 		
		query = "select  cm.name as name from company as cm where cm.id="+id;
		Connection connection = DataSourceUtils.getConnection(datasource);
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		result.setId(id);
		if(resultSet.next()){
			result.setName(resultSet.getString("name"));
		}
		close(resultSet);
		close(preparedStatement);
		return result;
	}

	public ArrayList<Company> getAll(int order) throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String query = "";
		ArrayList<Company> companies = new ArrayList<Company>();
		query = "select cm.id as id, cm.name as compa from company as cm";
		preparedStatement = DataSourceUtils.getConnection(datasource).prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new Company.CompanyBuilder(resultSet.getString("compa")).build();
			com.setId(Long.parseLong(resultSet.getString("id")));
			companies.add(com);
		}
		close(resultSet);
		close(preparedStatement);
		return companies;
	}

	public ArrayList<Company> filterByName(String name, int order)
			throws SQLException {
		logger.debug("Connection:" + DataSourceUtils.getConnection(datasource));
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String query = "";
		ArrayList<Company> companyresultSet = new ArrayList<Company>();
		query = "select cp.id as id, cp.name as name from company as cp where cp.name like ?";
		Connection connection = DataSourceUtils.getConnection(datasource);
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, "%"+name+"%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company c = new Company.CompanyBuilder(resultSet.getLong("id"),resultSet.getString("name")).build();
			companyresultSet.add(c);
		}
		close(resultSet);
		close(preparedStatement);
		return companyresultSet;
	}
}