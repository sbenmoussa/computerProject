package com.projet.computerdata.service;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ComputerDataService {

	INSTANCE;

	private Connection cn;
	
	
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
			Class.forName("com.mysql.jdbc.Driver");
			BoneCPConfig config = new BoneCPConfig();   // création d'un objet BoneCPConfig
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");           // définition de l'URL JDBC
			config.setUsername("root" );       // définition du nom d'utilisateur
			config.setPassword( "root" );       // définition du mot de passe
			    
			config.setMinConnectionsPerPartition( 5 );  // définition du nombre min de connexions par partition
			config.setMaxConnectionsPerPartition( 10 ); // définition du nombre max de connexions par partition
			config.setPartitionCount( 2 );          // définition du nombre de partitions
			    
			BoneCP connectionPool = new BoneCP( config );   // création du pool à partir de l'objet BoneCPConfig
			
			cn = connectionPool.getConnection();
			/*this.cn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull",
							"root", "root");
*/
			//this.cn = ds.getConnection();
		} catch (SQLException e) {
			System.out.println("erreur sql: " + e.getMessage());

		} catch (ClassNotFoundException e2) {
			System.out.println("impossible de charger la classe du driver: "
					+ e2.getMessage());
		}
	}

	/**
	 * méthode pour se déconnecter de la base
	 */
	public void disconnect(ResultSet resultSet, PreparedStatement preparedStatement, Statement statement, Connection connection) {
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
				
				if ( statement != null ) {
					try {
						statement.close();
					} catch ( SQLException ignore ) {
					}
				}
				
				connection.close();
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
	

	public void setCn(Connection cn){
		this.cn = cn;
	}
	
	
	public Connection getCn(){
		return this.cn ;
	}
}
