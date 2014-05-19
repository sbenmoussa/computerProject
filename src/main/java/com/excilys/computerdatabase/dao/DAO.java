package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface DAO<T> {
	
	public abstract boolean create(T object) throws SQLException ;
	
	public abstract boolean update(T object) throws SQLException ;
	
	public abstract boolean delete(long id) throws SQLException ;
	
	public abstract T find(long id) throws SQLException ;
	
	public abstract ArrayList<T> getAll(int order) throws SQLException;
	
	public abstract ArrayList<T> filterByName(String name, int order) throws SQLException;
}
