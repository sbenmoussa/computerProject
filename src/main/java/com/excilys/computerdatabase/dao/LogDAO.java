package com.excilys.computerdatabase.dao;

import static com.excilys.computerdatabase.dao.DAOUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;


@Repository
public class LogDAO {
	
	@Autowired
	DataSource datasource;

	public void logInfo(String message) throws SQLException {
		if(datasource == null){
			System.out.println("la datasource n'a pas été initialisé ");
			throw new RuntimeException();
		}
		Connection connection = DataSourceUtils.getConnection(datasource);
		PreparedStatement preparedStatement = connection.prepareStatement("insert into log4j (priority, category, thread, message, layout_msg, timestamp, addon) VALUES(?,?,?,?,?,?,?)");
		preparedStatement.setString(1, "INFO");
		preparedStatement.setString(2, "operation");
		preparedStatement.setString(3, message);
		preparedStatement.setString(4, "INFO");
		preparedStatement.setString(5, "INFO");
		preparedStatement.setString(6, new java.sql.Date(new Date().getTime())+"");
		preparedStatement.setString(7, "INFO");
		int resultSet = preparedStatement.executeUpdate();
		close(preparedStatement);
		System.out.println("resultat de l'insertion du log "+resultSet);
	}
}
