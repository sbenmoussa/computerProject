package com.projet.computerdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.projet.computerdata.model.Company;
import com.projet.computerdata.service.ComputerDataService;

public enum CompanyDAO {
	INSTANCE;	

	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet ;

	/**
	 * méthode pour récupérer la liste des company
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> companies = new ArrayList<Company>();
		Connection cn = null ;
		try {
			ComputerDataService.INSTANCE.connect();
			cn =  ComputerDataService.INSTANCE.getCn();
			String query = "select cm.id as id, cm.name as compa from company as cm";
			preparedStatement = cn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company com = new Company(resultSet.getString("compa"));
				com.setId(Long.parseLong(resultSet.getString("id")));
				companies.add(com);
			}

			return companies;
		} catch (SQLException sql) {
			System.out.println("sql error: " + sql.getMessage());
		} 
		catch(IllegalAccessException illegal){
		}
		finally {
			ComputerDataService.INSTANCE.disconnect(resultSet, preparedStatement,statement,cn);
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
	public int AddCompany(Company c){
		Connection cn = null ;
		if(existCompany(c) == 0){
			try {
				ComputerDataService.INSTANCE.connect();
				cn =  ComputerDataService.INSTANCE.getCn();
				String query = "insert into company(name) values(?)";
				preparedStatement = cn.prepareStatement(query);
				preparedStatement.setString(1, c.getName());
				int checkInsert = preparedStatement.executeUpdate();
				return checkInsert;

			} catch (SQLException sql) {
				System.out.println("sql error: " + sql.getMessage());
				return -1;
			} 
			catch(IllegalAccessException illegal){
			}
			finally {
				ComputerDataService.INSTANCE.disconnect(resultSet, preparedStatement, statement, cn);
			}
		}
		else{
			System.out.println("company already exists");
		}
		return 0;
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
	public int existCompany(Company c){

		Connection cn = null ;
		try {
			ComputerDataService.INSTANCE.connect();
			cn =  ComputerDataService.INSTANCE.getCn();
			System.out.println(c.getName());
			String query = "select id from company where name like '%"+c.getName()+"%'";
			preparedStatement = cn.prepareStatement(query);
			//preparedStatement.setString(1, c.getName());
			resultSet = preparedStatement.executeQuery();
			System.out.println("recherche effectue");
			if (resultSet.next()) {
				System.out.println("il y'a au moins un result");
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
			return -1;
		} catch (IllegalAccessException illegal) {
			System.out.println("sql error: " + illegal.getMessage());
			return -1;
		} finally {
			ComputerDataService.INSTANCE.disconnect(resultSet, preparedStatement,statement, cn);
		}
		return 0;
	}

	/**
	 * 
	 * @param company
	 */
	public void update(Company company){
		//TODO update methode
	}

	/**
	 * 
	 * @param company
	 */
	public void deleteCompany(Company company){
		//TODO delete methode
	}
}