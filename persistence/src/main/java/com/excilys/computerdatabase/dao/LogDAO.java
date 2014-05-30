package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void logInfo(String message) throws SQLException {
		String query = "insert into log4j (priority, category, thread, message, layout_msg, timestamp, addon) VALUES(?,?,?,?,?,?,?)";
		int result = sessionFactory.getCurrentSession().createSQLQuery(query).setString(1, "INFO").setString(1, "operation").setString(1, message)
		.setString(1, "INFO").setString(1, "INFO").setString(1,  new java.sql.Date(new Date().getTime())+"").setString(1, "INFO").executeUpdate();
		System.out.println("result de l'insertion du log "+result);
	}
}
