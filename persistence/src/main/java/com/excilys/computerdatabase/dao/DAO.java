package com.excilys.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DAO<T> {
	
	public abstract boolean create(T object) throws SQLException ;
	
	public abstract boolean update(T object) throws SQLException ;
	
	public abstract boolean delete(long id) throws SQLException ;
	
	public abstract T find(long id) throws SQLException ;
	
	public abstract List<T> getAll(int order, int page) throws SQLException;
	
	public abstract List<T> filterByName(String name, int order, int page) throws SQLException;
	
	int count(String name) throws SQLException;
}
