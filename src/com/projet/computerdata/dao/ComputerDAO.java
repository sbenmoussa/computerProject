package com.projet.computerdata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


/*import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;*/

//import java.text.SimpleDateFormat;
//import java.sql.Timestamp;



import java.util.logging.Logger;

import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;

public enum ComputerDAO {

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
			Logger logger = Logger.getLogger("com.projet.computerdata.daoComputerDAO");
			logger.info("connexion à la base de données établie");
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
					Logger logger = Logger.getLogger("com.projet.computerdata.daoComputerDAO");
					logger.info("déconnexion à la base de données réussi");
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
	 * méthode pour récupérer la liste des computeresultSet
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws ParesultSeteException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Computer> getAllComputer() throws IllegalAccessException,
	ParseException, SQLException {
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			connect();
			String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id";
			preparedStatement = cn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
			while (resultSet.next()) {
				Company com = new Company(resultSet.getString("compa"));
				Date intr = null;
				Date disc = null;
				if (resultSet.getTimestamp("intr") != null) {
					intr = new Date(resultSet.getTimestamp("intr").getTime());
				}

				if (resultSet.getTimestamp("dis") != null) {
					disc = new Date(resultSet.getTimestamp("dis").getTime());
				}
				Computer c = new Computer(resultSet.getLong("id"),resultSet.getString("namecp"), intr, disc,com);
				computeresultSet.add(c);
			}

			return computeresultSet;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return computeresultSet;
	}

	
	/**
	 * méthode qui permet d'avoir la liste des computeresultSet telqu'ils ont le même
	 * nom
	 * 
	 * @param name
	 * @return
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Computer> filterComputerByName(String name)
			throws IllegalAccessException, SQLException {
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			connect();			
			String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like '%"+name+"%'";
			statement = cn.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Company com = new Company(resultSet.getString("compa"));
				Date intr = null;
				Date disc = null;
				if (resultSet.getTimestamp("intr") != null) {
					intr = new Date(resultSet.getTimestamp("intr").getTime());
				}

				if (resultSet.getTimestamp("dis") != null) {
					disc = new Date(resultSet.getTimestamp("dis").getTime());
				}
				Computer c = new Computer(resultSet.getLong("id"),resultSet.getString("namecp"), intr, disc,
						com);
				computeresultSet.add(c);
			}

			return computeresultSet;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return computeresultSet;
	}

	/**
	 * méthode qui permet d'avoir la liste des computeresultSet telqu'ils ont la même
	 * date d'introduction dans le marché
	 * 
	 * @param name
	 * @return
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Computer> filterComputerByDate(String date)
			throws IllegalAccessException, SQLException {
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			connect();

			String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.introduced = ";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1,date);			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company com = new Company(resultSet.getString("compa"));
				Date intr = null;
				Date disc = null;
				if (resultSet.getTimestamp("intr") != null) {
					intr = new Date(resultSet.getTimestamp("intr").getTime());
				}

				if (resultSet.getTimestamp("dis") != null) {
					disc = new Date(resultSet.getTimestamp("dis").getTime());
				}
				Computer c = new Computer(resultSet.getLong("id"),resultSet.getString("namecp"), intr, disc,
						com);
				computeresultSet.add(c);
			}

			return computeresultSet;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return computeresultSet;
	}

	/**
	 * méthode qui permet d'avoir la liste des computeresultSet d'une company
	 * 
	 * @param name
	 * @return
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Computer> filterComputerByCompany(String name)
			throws IllegalAccessException, SQLException {
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		try {
			connect();
			String query = "select cp.id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cm.name like '%"
					+ name + "%'";
			preparedStatement = cn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company com = new Company(resultSet.getString("compa"));
				Date intr = null;
				Date disc = null;
				if (resultSet.getTimestamp("intr") != null) {
					intr = new Date(resultSet.getTimestamp("intr").getTime());
				}

				if (resultSet.getTimestamp("dis") != null) {
					disc = new Date(resultSet.getTimestamp("dis").getTime());
				}
				Computer c = new Computer(resultSet.getLong("id"), resultSet.getString("namecp"), intr, disc,
						com);
				computeresultSet.add(c);
			}

			return computeresultSet;
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return computeresultSet;
	}

	/**
	 * rajouter un Computer dans la base
	 * 
	 * @param c
	 * @throws IllegalAccessException 
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public int addComputer(Computer c) throws IllegalAccessException, SQLException  {

		if(existComputer(c) == 0){
			try {			

				// On recupere d'abord l'id de la company s'il existe dans la base
				/*Company com = new Company(c.getCompany().getName());
				int idCom = existCompany(com) ;
				if(idCom == 0){
					idCom = AddCompany(com);
				}
				else{
					if(idCom == -1){
						System.out.println("erreur sql loresultSet de l'interogation de la base sur la table company");
					}
				}*/


				Long idCom = c.getCompany().getId();
				connect();
				// Insertion des données du computer dans la base
				String query = "insert into computer(name, introduced, discontinued, company_id) values(?,?,?,?)";
				preparedStatement = cn.prepareStatement(query);
				preparedStatement.setString(1, c.getName());
				preparedStatement.setDate(2, new java.sql.Date(c.getIntroduced().getTime()));
				preparedStatement.setDate(3, new java.sql.Date(c.getDiscontinued().getTime()));
				preparedStatement.setFloat(4, idCom);
				int checkInsert = preparedStatement.executeUpdate();

				System.out.println(c.toString());
				return checkInsert;

			} catch (SQLException e) {
				System.out.println("sql error: " + e.getMessage());
				return -1;
			} finally {
				disconnect();
			}
		}
		else if(existComputer(c) == -1){
			System.out.println("erreur sql le resultSet de l'interogation de la base sur la table computer");
		}
		else{
			System.out.println("Computer already exists");
		}
		return 0;
	}


	/**
	 * 
	 * @param c
	 * @throws IllegalAccessException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public int existComputer(Computer c) throws IllegalAccessException, SQLException {

		try {
			connect();
			String query = "select id from computer where name = ?";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, c.getName() );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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
	 * @param c as computer object
	 * @throws IllegalAccessException 
	 */
	public void updateComputer(Computer c) throws IllegalAccessException{
		try {			
			connect();
			String query = "update computer set name = ? , introduced = ?, discontinued = ? where id=?";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, new java.sql.Date(c.getIntroduced().getTime()));
			preparedStatement.setDate(3, new java.sql.Date(c.getDiscontinued().getTime()));
			preparedStatement.setLong(4, c.getId());
			int checkUpdate = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
	}
	
	/**
	 * 
	 * @param c as computer object
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 */
	public void deleteComputer(Long id) throws IllegalAccessException, SQLException{

		try {			
			connect();
			String query = "delete from computer where id=?";
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setLong(1, id);
			int checkDelete = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
}

	
	public int TotalCount() throws IllegalAccessException{
		try {			
			connect();
			String query = "select count(*) as total from computer";
			statement = cn.createStatement();
			resultSet = statement.executeQuery(query);
			if(resultSet.next()){
				return resultSet.getInt("total");
			}
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return 0;
	}
	
	
	public Computer getComputerById(Long id) throws IllegalAccessException{
		
		Computer res  = new Computer(); 		
		try {			
			connect();
			String query = "select  cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa, cm.id as idcompa from computer as cp join company as cm on cp.company_id = cm.id where cp.id="+id;
			statement = cn.createStatement();
			resultSet = statement.executeQuery(query);
			res.setId(id);
			
			if(resultSet.next()){
				res.setName(resultSet.getString("namecp"));

				Company com = new Company(resultSet.getLong("idcompa"),resultSet.getString("compa"));
				Date intr = null;
				Date disc = null;
				if (resultSet.getTimestamp("intr") != null) {
					intr = new Date(resultSet.getTimestamp("intr").getTime());
				}

				if (resultSet.getTimestamp("dis") != null) {
					disc = new Date(resultSet.getTimestamp("dis").getTime());
				}
				
				res.setIntroduced(intr);
				res.setDiscontinued(disc);
				res.setCompany(com);
			}
			return res;
			
		} catch (SQLException e) {
			System.out.println("sql error: " + e.getMessage());
		} finally {
			disconnect();
		}
		return res;
		
	}
	/**
	 * test
	 * 
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws IllegalAccessException, SQLException{
		//ComputerData cd = new ComputerData();
		Company sana = new Company("sana");
		Computer sanaComputer = new Computer(20000L,"sana_computerNew", new Date(),new Date(), sana);
		ComputerDAO.INSTANCE.addComputer(sanaComputer);
	}

}
