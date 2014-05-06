package com.excilys.computerdatabase.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.connection.ConnectionManager;

@Repository
public class UtilDAO {

	@Autowired
	private ConnectionManager connectionManager;
	
	public void setConnectionManager(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	public static void close( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}
	
	public static void close( PreparedStatement preparedStatement ) {
	    if ( preparedStatement != null ) {
	        try {
	            preparedStatement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du preparedStatement : " + e.getMessage() );
	        }
	    }
	}

	public static void close( Connection connexion ) {
		if ( ( connexion != null ) ){
			try {
				
				connexion.close();
				System.out.println("connection fermé avec succés");
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}
	
	public  void endTransaction(boolean success, Connection connection){
		try {
			if(success){
				connection.commit();
			}
			else{
				connection.rollback();
			}			
		} catch (SQLException e) {
			System.out.println("erreur commit ou rollback");
		}

	}
	
	public void beginTransaction(){
		try {
			if(connectionManager == null){
				System.out.println("le connection manager n'a pas été instancié");
			}
			Connection  cn = connectionManager.getConnection();
			cn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("initialisation de transaction");
		}
	}

}
