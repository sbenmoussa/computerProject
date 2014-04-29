package com.projet.computerdata.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.projet.computerdata.dao.CompanyDAO;
import com.projet.computerdata.dao.ComputerDAO;
import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public enum ComputerDataService {

	INSTANCE;	
	
	private BoneCP connectionPool;
	private  final Logger LOG = LoggerFactory.getLogger("com.projet.computerdata");
	private ComputerDataService() {
		//LOG= Logger.getLogger("com.projet.computerdata.service.ComputerDataService.class");
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			BoneCPConfig config = new BoneCPConfig();   
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");           
			config.setUsername("root" );       
			config.setPassword( "root" );       

			config.setMinConnectionsPerPartition( 5 );  
			config.setMaxConnectionsPerPartition( 10 ); 
			config.setPartitionCount( 2 );         

			connectionPool = new BoneCP( config ); 
			LOG.debug("initialisation du pool de connection BoneCP");
			
		} catch (SQLException e) {
			System.out.println("erreur sql: " + e.getMessage());
			LOG.info("initialisation du pool de connection BoneCP impossible " + e.getMessage());
			LOG.debug("initialisation du pool de connection BoneCP impossible");

		} catch (ClassNotFoundException e2) {
			System.out.println("impossible de charger la classe du driver: "
					+ e2.getMessage());
			LOG.info("impossible de charger la classe du driver:"+ e2.getMessage());
		}
	}
	
	/**
	 * Méthode pour se connecter à la base de donnée
	 * 
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 */
	public Connection connect() throws IllegalAccessException {

		try {	
			Connection cn = connectionPool.getConnection();
			cn.setAutoCommit(false);
			return  cn;
		} catch (SQLException e) {
			System.out.println("erreur sql: " + e.getMessage());

		} 
		return null;
	}

	/**
	 * méthode pour se déconnecter de la base
	 */
	public void disconnect(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		try {
			if ((!connection.isClosed()) && (connection != null) ){
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
				
				connection.close();
				System.gc();
				System.out.println("Déconnecté");
				
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
	
	
	public void insert(String model, Object o) throws SQLException{
		
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			cn = connect();
			
			if(model.equals("computer")){
				ComputerDAO.INSTANCE.addComputer((Computer)o, cn, preparedStatement, resultSet);
			}
			if(model.equals("company")){
				CompanyDAO.INSTANCE.AddCompany((Company)o, cn, preparedStatement, resultSet);
			}	
			logInfo(model+" inséré avec succés", cn, preparedStatement);
			cn.commit();
		} catch (IllegalAccessException e) {			
			cn.rollback();	
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {	
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
	}
	
	public void update(String model, Object o) throws SQLException{
		
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			cn = connect();
			cn.setAutoCommit(false);
			if(model.equals("computer")){
				ComputerDAO.INSTANCE.updateComputer((Computer)o, cn, preparedStatement, resultSet);
			}
			if(model.equals("company")){
				CompanyDAO.INSTANCE.update((Company)o, cn, preparedStatement, resultSet);;
			}
			logInfo(model+" mis à jour avec succés", cn, preparedStatement);
			cn.commit();			
		} catch (IllegalAccessException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
	}
	
	public void delete(String model , Long id) throws SQLException{
		
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			cn = connect();
			if(model.equals("computer")){
				ComputerDAO.INSTANCE.deleteComputer(id, cn, preparedStatement, resultSet);
			}
			if(model.equals("company")){
				CompanyDAO.INSTANCE.deleteCompany(id, cn, preparedStatement, resultSet);
			}
			logInfo(model+" supprimé avec succés", cn, preparedStatement);
			LOG.debug("suppression ok");
			cn.commit();
		} catch (IllegalAccessException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
	}
	
	public ArrayList<Object> getAll(String model, int order) throws SQLException{
		
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			cn = connect();
			if(model.equals("computer")){
				return ComputerDAO.INSTANCE.getAllComputer(order, cn, preparedStatement, resultSet);
			}
			if(model.equals("company")){
				return CompanyDAO.INSTANCE.getAllCompany(cn, preparedStatement, resultSet);
			}	
			cn.commit();
		} catch (IllegalAccessException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
		return null;
	}
	
	public ArrayList<Object> filterByName(String model, String name, int order) throws SQLException{
		
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			cn = connect();
			if(model.equals("computer")){
				return ComputerDAO.INSTANCE.filterComputerByName(name,order, cn, preparedStatement, resultSet);
			}
			cn.commit();
		} catch (IllegalAccessException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
		return null;
	}
	
	public Object getElementById(String model, Long id) throws SQLException{
		Connection cn = null ;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			cn = connect();
			if(model.equals("computer")){
				return ComputerDAO.INSTANCE.getComputerById(id, cn, preparedStatement, resultSet);
			}
			cn.commit();
		} catch (IllegalAccessException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		} catch (SQLException e) {
			cn.rollback();
			System.out.println("transaction annulée: "+e.getMessage());
		}
		finally{
			disconnect(resultSet, preparedStatement, cn);
		}
		return null;
	}

	public void logInfo(String message, Connection cn, PreparedStatement preparedStatement) throws SQLException{
		System.out.println("méthode de l'insertion du log ");
		preparedStatement = cn.prepareStatement("insert into log4j (priority, category, thread, message, layout_msg, timestamp, addon) VALUES(?,?,?,?,?,?,?)");
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


