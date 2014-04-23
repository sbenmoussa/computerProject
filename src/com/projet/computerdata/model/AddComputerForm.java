package com.projet.computerdata.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.computerdata.dao.CompanyDAO;
import com.projet.computerdata.dao.ComputerDAO;

public class AddComputerForm {

	private static final Company com = new Company();
	
	private String result;
	private Map<String, String> errors = new HashMap<String,String>();
	
	public String getResult() {
		return result;
	}
	public Map<String, String> getErrors() {
		return errors;
	}

	
	public void addComputer(HttpServletRequest request ) throws Exception{
		Computer computer = new Computer();
		String nom = getValeurChamp( request, "name" );
		String dateIntroduction = getValeurChamp( request, "introducedDate" );;
		String dateDisc = getValeurChamp( request, "discontinuedDate" );;
		String idCompany = getValeurChamp( request, "company" );;
		Date d1 = null;
		Date d2 = null;
		
		try{
			checkName(nom) ;
		}
		catch(Exception e){
			errors.put("name", e.getMessage());
		}
		
		computer.setName(nom);
		try{
			d1 = checkDate(dateIntroduction);
		}
		catch(Exception e){
			errors.put("dateIntroduction", e.getMessage());
		}
		
		computer.setIntroduced(checkDate(dateIntroduction));
		
		try{
			d2 = checkDate(dateDisc);
		}
		catch(Exception e){
			errors.put("date de discont", e.getMessage());
		}
		
		
		computer.setDiscontinued(checkDate(dateDisc));
		
		com.setId((long) Integer.parseInt(idCompany));
		//checkCompany(com);;
		
		computer.setCompany(com);
		
		
		try{
			dateOk(computer.getIntroduced(), computer.getDiscontinued());
		}
		catch(Exception e){
			errors.put("dates invalides (décallage)", e.getMessage());
			System.out.println("dates de discont est avant celle d'introduction");
		}
		
		if(errors.isEmpty()){
			System.out.println("pas d'erreur donc création du computer test if ok");
			ComputerDAO.INSTANCE.addComputer(computer);
			 result="Success";
		}
		else{
			System.out.println("des erreur donc test if non ok");
			result="Fail";
			System.out.println("des erreur dans la saisie"+errors.keySet()+", "+errors.values());
		}
		//return computer;
	}
	

	public void checkName(String n) throws Exception{
		if(n==null){
			throw new Exception("il faut saisir un nom");
		}
	}
	
	public Date checkDate(String dat) throws Exception{
		Date d = new Date();
		try{			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			d = sdf.parse(dat);
			return  d;
		}
		catch(Exception e){
			System.out.println("format de date invalide donc catché");
		}
		System.out.println(d);
		return d;
	}
	
	public void dateOk(Date d1 , Date d2) throws Exception{
		if((d2.getTime() - d1.getTime()) <= 0){
			throw new Exception("Date de fin est avant ou est le même que la date d'introduction");
		}
	}
	
	public void checkCompany(Company com) throws Exception{
		int check = CompanyDAO.INSTANCE.existCompany(com);
		if((check !=0) && (check !=-1)){
			System.out.println("company existe déja");
		}
		else if((check ==0)){
			System.out.println("company n'existe pas");
			throw new Exception("Company inexistante");
			
		}
		else{
			System.out.println("erreur sql");
			throw new Exception("SQL error retry");
		}
	}
	
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
}
