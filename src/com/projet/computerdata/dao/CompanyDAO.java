package com.projet.computerdata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.projet.computerdata.model.Company;

public enum CompanyDAO {
	INSTANCE;

	private Connection cn;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet resultSet ;
	
	
	/**
	 * Méthode pour se connecter à la base de donnée
	 * 
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 */
	public void connect() throws IllegalAccessException {

		try {

			//Context ct  = new InitialContext();
			//if(ct !=null){
			//	System.out.println("contexte initialisé ");
			//}
			//DataSource ds = (DataSource) ct.lookup("java:/comp/env/jdbc/commerce");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.cn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull",
							"root", "root");

			//this.cn = ds.getConnection();
		} catch (SQLException e) {
			System.out.println("erreur sql: " + e.getMessage());

		} catch (ClassNotFoundException e2) {
			System.out.println("impossible de charger la classe du driver: "
					+ e2.getMessage());
		} catch (InstantiationException e3) {
			System.out.println("instanciation du driver impossible: "
					+ e3.getMessage());
		}
	}

	/**
	 * méthode pour se déconnecter de la base
	 */
	public void disconnect() {
		try {
			if ((!cn.isClosed()) && (cn != null) ){
				if ( resultSet != null ) {
					try {
						resultSet.close();
					} catch ( SQLException ignore ) {
					}
				}

				if ( preparedStatement != null ) {
					try {
						preparedStatement.close();
					} catch ( SQLException ignore ) {
					}
				}
				
				if ( statement != null ) {
					try {
						statement.close();
					} catch ( SQLException ignore ) {
					}
				}
				
				try {
					cn.close();
					System.out.println("Déconnecté");
				}catch ( SQLException ignore ) {
				}			
			} else {
				System.out
				.println("inutile de déconnecter car déja déconnecté");
			}
		} catch (SQLException e) {
			System.out.println("erreur sql: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.out.println("connection inexistante");
		}

	}

/**
 * méthode pour récupérer la liste des company
 * 
 * @return
 * @throws IllegalAccessException
 * @throws NamingException 
 * @throws SQLException 
 */
public ArrayList<Company> getAllCompany() throws IllegalAccessException, SQLException {
	ArrayList<Company> companies = new ArrayList<Company>();
	try {
		connect();
		String query = "select cm.id as id, cm.name as compa from company as cm";
		preparedStatement = cn.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new Company(resultSet.getString("compa"));
			com.setId(Long.parseLong(resultSet.getString("id")));
			companies.add(com);
		}

		return companies;
	} catch (SQLException e) {
		System.out.println("sql error: " + e.getMessage());
	} finally {
		disconnect();
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
public int AddCompany(Company c) throws IllegalAccessException, SQLException {
	if(existCompany(c) == 0){
		try {
			connect();
			String query = "insert into company(name) values(?)";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			int checkInsert = preparedStatement.executeUpdate();
			return checkInsert;

		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
			return -1;
		} finally {
			disconnect();
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
public int existCompany(Company c) throws IllegalAccessException, SQLException {

	try {
		connect();
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
	} finally {
		disconnect();
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