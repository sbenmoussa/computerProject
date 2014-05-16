package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import static com.excilys.computerdatabase.dao.UtilDAO.*;

@Repository
public class ComputerDAO implements DAO<Computer>{


	public ComputerDAO(){

	}


	public boolean create(Computer object, Connection connection) throws SQLException {
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "insert into computer(name, introduced, discontinued, company_id) values('testTransaction3',now(),now(),1)";
		preparedStatement = connection.prepareStatement(query);
		int result = preparedStatement.executeUpdate();
		query = "insert into computer(name, introduced, discontinued, company_id) values(?,?,?,?)";	
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, object.getName());
		preparedStatement.setDate(2, new java.sql.Date(object.getIntroduced().toDate().getTime()));
		preparedStatement.setDate(3, new java.sql.Date(object.getDiscontinued().toDate().getTime()));
		preparedStatement.setFloat(4, object.getCompany().getId());
		result = preparedStatement.executeUpdate();
		close(preparedStatement);
		return result == 1;
	}

	public boolean update(Computer object, Connection connection) throws SQLException {
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "update computer set name = ? , introduced = ?, discontinued = ?, company_id = ? where id=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, object.getName());
		preparedStatement.setDate(2, new java.sql.Date(object.getIntroduced().toDate().getTime()));
		preparedStatement.setDate(3, new java.sql.Date(object.getDiscontinued().toDate().getTime()));
		preparedStatement.setFloat(4, object.getCompany().getId());
		preparedStatement.setLong(5, object.getId());
		int result = preparedStatement.executeUpdate();
		close(preparedStatement);
		switch(result){
		case 1 : return true;
		default : return false;
		}
	}

	public boolean delete(long id, Connection connection) throws SQLException {	
		PreparedStatement preparedStatement =null;
		String query = "";
		query = "delete from computer where id=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);
		int result = preparedStatement.executeUpdate();
		close(preparedStatement);
		switch(result){
		case 1 : return true;
		default : return false;
		}
	}

	public Computer find(long id, Connection connection) throws SQLException {
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String query = "";
		Computer result  = new Computer.ComputerBuilder().build(); 		
		query = "select  cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa, cm.id as idcompa from computer as cp join company as cm on cp.company_id = cm.id where cp.id="+id;
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		result.setId(id);

		if(resultSet.next()){
			result.setName(resultSet.getString("namecp"));
			Company company = new Company.CompanyBuilder(resultSet.getLong("idcompa"),resultSet.getString("compa")).build();
			DateTime intr = null;
			DateTime disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new DateTime(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new DateTime(resultSet.getTimestamp("dis").getTime());
			}

			result.setIntroduced(intr);
			result.setDiscontinued(disc);
			result.setCompany(company);
		}
		close(resultSet);
		close(preparedStatement);
		return result;
	}

	public ArrayList<Computer> getAll(int order, Connection connection) throws SQLException {
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String query = "";
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		query ="select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id ";

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

		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new Company.CompanyBuilder(resultSet.getString("compa")).build();
			DateTime intr = null;
			DateTime disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new DateTime(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new DateTime(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new Computer.ComputerBuilder(resultSet.getLong("id"),resultSet.getString("namecp"), intr, disc,com).build();
			computeresultSet.add(c);
		}
		close(resultSet);
		close(preparedStatement);
		return computeresultSet;
	}

	public ArrayList<Computer> filterByName(String name, int order, Connection connection) throws SQLException {
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
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

		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, "%"+name+"%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new Company.CompanyBuilder(resultSet.getString("compa")).build();
			DateTime intr = null;
			DateTime disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new DateTime(resultSet.getTimestamp("intr").getTime());
			}
			if (resultSet.getTimestamp("dis") != null) {
				disc = new DateTime(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new Computer.ComputerBuilder(resultSet.getLong("id"),resultSet.getString("namecp"), intr, disc,com).build();
			computeresultSet.add(c);
		}
		close(resultSet);
		close(preparedStatement);
		return computeresultSet;
	}

}
