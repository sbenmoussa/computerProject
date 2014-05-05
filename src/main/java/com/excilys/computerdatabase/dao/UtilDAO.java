package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdatabase.connection.ConnectionManager;


public class UtilDAO {

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
	
	public static void endTransaction(boolean success, Connection connection){
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
	
	public static void beginTransaction(){
		try {
			ConnectionManager.INSTANCE.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("initialisation de transaction");
		}
	}

}
