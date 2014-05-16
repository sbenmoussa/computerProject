package com.excilys.computerdatabase.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
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
}
