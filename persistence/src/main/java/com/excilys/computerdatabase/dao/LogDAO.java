package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LogDAO {
	
	@Autowired
	DataSource datasource;

	private JdbcTemplate jt ;
	
	public void logInfo(String message) throws SQLException {
		jt  = new JdbcTemplate(datasource);	
		String query = "insert into log4j (priority, category, thread, message, layout_msg, timestamp, addon) VALUES(?,?,?,?,?,?,?)";
		int result = jt.update(query, "INFO", "operation", message, "INFO", "INFO", new java.sql.Date(new Date().getTime())+"", "INFO");
		System.out.println("resultat de l'insertion du log "+result);
	}
}
