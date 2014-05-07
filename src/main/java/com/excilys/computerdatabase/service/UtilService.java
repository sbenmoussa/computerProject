package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
 
	public static void logInfo(String message, Connection connection) throws SQLException{
		System.out.println("méthode de l'insertion du log ");
		PreparedStatement preparedStatement = connection.prepareStatement("insert into log4j (priority, category, thread, message, layout_msg, timestamp, addon) VALUES(?,?,?,?,?,?,?)");
		preparedStatement.setString(1, "INFO");
		preparedStatement.setString(2, "operation");
		preparedStatement.setString(3, message);
		preparedStatement.setString(4, "INFO");
		preparedStatement.setString(5, "INFO");
		preparedStatement.setString(6, new java.sql.Date(new Date().getTime())+"");
		preparedStatement.setString(7, "INFO");
		int resultSet = preparedStatement.executeUpdate();
		System.out.println("resultat de l'insertion du log "+resultSet);
	}
}
