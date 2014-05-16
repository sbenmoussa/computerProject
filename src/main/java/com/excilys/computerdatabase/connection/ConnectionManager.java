package com.excilys.computerdatabase.connection;

import java.sql.Connection;
import java.sql.SQLException;

//import javax.annotation.Resource;
//import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Repository
public class ConnectionManager {
	
	private BoneCP connectionPool = null;
	//@Resource
    //private DataSource dataSource;
	public ConnectionManager(){
		System.out.println("instanciation du connection manager");
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			BoneCPConfig config = new BoneCPConfig();   
			config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull" );       
			config.setUsername( "root" );      
			config.setPassword( "root" );      			    
			config.setMinConnectionsPerPartition( 5 );  
			config.setMaxConnectionsPerPartition( 10 ); 
			config.setPartitionCount( 2 );          
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
			//computerThreadLocal.set(dataSource.getConnection());
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
					//return dataSource.getConnection();
					return connectionPool.getConnection();
				} catch (SQLException e) {
					System.out.println("exception de la demande de connection au pool "+e.getMessage());
				}
				return null; 
			}  
			
			
		};	
	
}
