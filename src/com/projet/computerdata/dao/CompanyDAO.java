package com.projet.computerdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.projet.computerdata.model.Company;

public enum CompanyDAO {
	INSTANCE;	

	/**
	 * méthode pour récupérer la liste des company
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Object> getAllCompany(Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {
		ArrayList<Object> companies = new ArrayList<Object>();			
		String query = "select cm.id as id, cm.name as compa from company as cm order by cm.name";
		preparedStatement = cn.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new
		            Company.CompanyBuilder()
		            .id(Long.parseLong(resultSet.getString("id")))
		            .name(resultSet.getString("compa"))
		            .build();
			companies.add(com);
		}
		return companies;
	}

	/**
	 * rajouter une company dans la base
	 * 
	 * @param c
	 * @throws IllegalAccessException 
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public int AddCompany(Company c, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{
		
		int checkInsert =0;
		if(existCompany(c, cn, preparedStatement, resultSet) == 0){
			String query = "insert into company(name) values(?)";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			checkInsert = preparedStatement.executeUpdate();			
		}
		else{
			System.out.println("company already exists");
		}
		return checkInsert;
	}

	/**
	 * méthode qui vérifie qu'une company exist et retourne son id si c'est le
	 * cas sinon 0
	 * 
	 * @param c
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public int existCompany(Company c, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{

		int result = 0;
		System.out.println(c.getName());
		String query = "select id from company where name like ?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1, "%"+c.getName()+"%");
		resultSet = preparedStatement.executeQuery();
		System.out.println("recherche effectue");
		if (resultSet.next()) {
			System.out.println("il y'a au moins un result");
			return resultSet.getInt("id");
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 */
	public void update(Company o, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet){
		//TODO update methode
	}

	/**
	 * 
	 * @param company
	 */
	public void deleteCompany(Long id, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet){
		//TODO delete methode
	}
}