package com.excilys.computerdatabase.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface DAO<T> {
	
	public abstract boolean create(T object, Connection connection) throws SQLException;
	
	public abstract boolean update(T object, Connection connection) throws SQLException ;
	
	public abstract boolean delete(long id, Connection connection) throws SQLException ;
	
	public abstract T find(long id, Connection connection) throws SQLException ;
	
	public abstract ArrayList<T> getAll(int order, Connection connection) throws SQLException;
	
	public abstract ArrayList<T> filterByName(String name, int order, Connection connection) throws SQLException;
}
