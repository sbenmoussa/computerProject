package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtil {

	
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
	
	public static void close( Connection connection ) {
	    if ( connection != null ) {
	        try {
	        	connection.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture de la connection : " + e.getMessage() );
	        }
	    }
	}
}
