package com.excilys.computerdatabase.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionManager {

	INSTANCE;
	
	private BoneCP connectionPool = null;
	private ConnectionManager(){
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			BoneCPConfig config = new BoneCPConfig();   // création d'un objet BoneCPConfig
			config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull" );           // définition de l'URL JDBC
			config.setUsername( "root" );       // définition du nom d'utilisateur
			config.setPassword( "root" );       // définition du mot de passe			    
			config.setMinConnectionsPerPartition( 5 );  // définition du nombre min de connexions par partition
			config.setMaxConnectionsPerPartition( 10 ); // définition du nombre max de connexions par partition
			config.setPartitionCount( 2 );          // définition du nombre de partitions
			this.connectionPool = new BoneCP( config );
		} 
		catch (ClassNotFoundException e) {
			System.out.println("impossible de charger la class du driver "+e.getMessage());
		}   
		catch (SQLException e) {
			System.out.println("impossible d'initialiser le pool "+e.getMessage());
		}   
	}
	
	public void connect(){
		try {
			computerThreadLocal.set(connectionPool.getConnection());
		} catch (SQLException e) {
			System.out.println("impossible d'initialiser la connection par le pool "+e.getMessage());
		}	
	}
	
	public Connection getConnection() {
        return  computerThreadLocal.get();
    }
	
	public void disconnect() {
		if ( ( computerThreadLocal.get() != null ) ){
			try {
				computerThreadLocal.get().close();
			} catch (SQLException e) {
				System.out.println("impossible de fermer la connection "+e.getMessage());
			}
			computerThreadLocal.remove();
			System.out.println("connection fermé avec succés");
		}
	}
	
	private final ThreadLocal<Connection> computerThreadLocal  = new ThreadLocal<Connection>(){  
			@Override  
			protected Connection initialValue() {  
				try {
					return connectionPool.getConnection();
				} catch (SQLException e) {
					System.out.println("exception de la demande de connection au pool "+e.getMessage());
				}
				return null; 
			}  
		};	
	
}
