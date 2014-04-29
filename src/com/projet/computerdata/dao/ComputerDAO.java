package com.projet.computerdata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;


public enum ComputerDAO {

	INSTANCE;


	/**
	 * méthode pour récupérer la liste des computeresultSet
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws ParesultSeteException
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public ArrayList<Object> getAllComputer(int order, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {
		ArrayList<Object> computeresultSet = new ArrayList<Object>();

		String query ="select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id ";;

		switch(order){
		case 0 : query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id order by cp.name";
		break;
		case 1: query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id order by intr";
		break;

		case 2: query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id order by dis";
		break;

		case 3:query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp left join company as cm on cp.company_id = cm.id order by compa";
		break;
		}

		preparedStatement = cn.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		while (resultSet.next()) {
			Company com = new
		            Company.CompanyBuilder()
		            .name(resultSet.getString("compa"))
		            .build();
			Date intr = null;
			Date disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new Date(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new Date(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new
		            Computer.ComputerBuilder(resultSet.getLong("id"), resultSet.getString("namecp"), intr, disc, com)
		            .build(); 
			computeresultSet.add(c);
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
	public ArrayList<Object> filterComputerByName(String name, int order, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{
		ArrayList<Object> computeresultSet = new ArrayList<Object>();		
		String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like ?";


		switch(order){
		case 0 : query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like ? order by cp.name";
		break;
		case 1: query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like ? order by cp.introduced";
		break;

		case 2: query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like ? order by cp.discontinued";
		break;

		case 3:query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.name like % order by cm.name";
		break;
		}

		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1, "%"+name+"%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new
		            Company.CompanyBuilder()
		            .name(resultSet.getString("compa"))
		            .build();
			Date intr = null;
			Date disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new Date(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new Date(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new
		            Computer.ComputerBuilder(resultSet.getLong("id"), resultSet.getString("namecp"), intr, disc, com)
		            .build();
			computeresultSet.add(c);
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
	public ArrayList<Computer> filterComputerByDate(String date, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException, IllegalAccessException{
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		String query = "select cp.id as id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cp.introduced = ";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1,date);			
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new
		            Company.CompanyBuilder()
		            .name(resultSet.getString("compa"))
		            .build();;
			Date intr = null;
			Date disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new Date(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new Date(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new
		            Computer.ComputerBuilder(resultSet.getLong("id"), resultSet.getString("namecp"), intr, disc, com)
		            .build();
			computeresultSet.add(c);
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
	public ArrayList<Computer> filterComputerByCompany(String name, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{
		ArrayList<Computer> computeresultSet = new ArrayList<Computer>();
		String query = "select cp.id, cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa from computer as cp join company as cm on cp.company_id = cm.id where cm.name like ?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1, "%"+name+"%");
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Company com = new
		            Company.CompanyBuilder()
		            .name(resultSet.getString("compa"))
		            .build();;
			Date intr = null;
			Date disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new Date(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new Date(resultSet.getTimestamp("dis").getTime());
			}
			Computer c = new
		            Computer.ComputerBuilder(resultSet.getLong("id"), resultSet.getString("namecp"), intr, disc, com)
		            .build();
			computeresultSet.add(c);
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
	public int addComputer(Computer c, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{

		if(existComputer(c, cn,preparedStatement, resultSet) == 0){		
			Long idCom = c.getCompany().getId();
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
		}
		else if(existComputer(c, cn,preparedStatement, resultSet) == -1){
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
	public int existComputer(Computer c, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException{

		String query = "select id from computer where name = ?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1, c.getName() );
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("id");
		}
		return 0;
	}


	/**
	 * 
	 * @param c as computer object
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	 */
	public void updateComputer(Computer c, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {

		System.out.println(c.toString());
		System.out.println("nouvelles dates "+new java.sql.Date(c.getDiscontinued().getTime()));
		String query = "update computer set name = ? , introduced = ?, discontinued = ?, company_id = ? where id=?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setString(1, c.getName());
		preparedStatement.setDate(2, new java.sql.Date(c.getIntroduced().getTime()));
		preparedStatement.setDate(3, new java.sql.Date(c.getDiscontinued().getTime()));
		preparedStatement.setLong(4, c.getCompany().getId());
		preparedStatement.setLong(5, c.getId());
		int checkUpdate = preparedStatement.executeUpdate();
		System.out.println("update ok? : "+checkUpdate);
	}

	/**
	 * 
	 * @param c as computer object
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 */
	public void deleteComputer(Long id, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {

		String query = "delete from computer where id=?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setLong(1, id);
		int checkDelete = preparedStatement.executeUpdate();
		System.out.println("checkDelete = "+ checkDelete);
	}


	public int TotalCount(Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {

		String query = "select count(*) as total from computer";
		preparedStatement = cn.prepareStatement(query);
		resultSet = preparedStatement.executeQuery(query);
		if(resultSet.next()){
			return resultSet.getInt("total");
		}
		return 0;
	}


	public Computer getComputerById(Long id, Connection cn, PreparedStatement preparedStatement, ResultSet resultSet) throws IllegalAccessException, SQLException {

				
		String query = "select  cp.name as namecp, cp.introduced as intr, cp.discontinued as dis, cm.name as compa, cm.id as idcompa from computer as cp join company as cm on cp.company_id = cm.id where cp.id=?";
		preparedStatement = cn.prepareStatement(query);
		preparedStatement.setLong(1, id);
		resultSet = preparedStatement.executeQuery();
		Computer res  = new
	            Computer.ComputerBuilder()
				.id(id)
	            .build(); 

		if(resultSet.next()){
			 

			Company com = new
		            Company.CompanyBuilder()
		            .id(Long.parseLong(resultSet.getString("idcompa")))
		            .name(resultSet.getString("compa"))
		            .build();
			Date intr = null;
			Date disc = null;
			if (resultSet.getTimestamp("intr") != null) {
				intr = new Date(resultSet.getTimestamp("intr").getTime());
			}

			if (resultSet.getTimestamp("dis") != null) {
				disc = new Date(resultSet.getTimestamp("dis").getTime());
			}

			res  = new
		            Computer.ComputerBuilder()
					.id(id)
					.name(resultSet.getString("namecp"))
					.introduced(intr)
					.discontinued(disc)
					.company(com)
		            .build();
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
		//Company sana = new Company("sana");
		//Computer sanaComputer = new Computer(20000L,"sana_computerNew", new Date(),new Date(), sana);
		//ComputerDAO.INSTANCE.addComputer(sanaComputer);
	}

}
