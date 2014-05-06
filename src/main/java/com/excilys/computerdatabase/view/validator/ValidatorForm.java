package com.excilys.computerdatabase.view.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.beans.Computer;
import com.excilys.computerdatabase.util.Util;

@Component
public class ValidatorForm {

	
	private String result;
	private Map<String, String> errors = new HashMap<String,String>();
	
	public String getResult() {
		return result;
	}
	public Map<String, String> getErrors() {
		return errors;
	}

	
	public Computer addComputer(HttpServletRequest request ){
		
		Long id = 0L;
		if(getValeurChamp(request, "idUpdate") !=null){
			id = Long.parseLong(getValeurChamp(request, "idUpdate"));
		}
		
		String nom = getValeurChamp( request, "name" );
		String dateIntroduction = getValeurChamp( request, "introducedDate" );;
		String dateDisc = getValeurChamp( request, "discontinuedDate" );;
		Long idCompany = Long.parseLong(getValeurChamp( request, "company" ));
		Date d1 = null;
		Date d2 = null;
		
		checkName(nom) ;		
		d1 = checkDate(dateIntroduction);		
		d2 = checkDate(dateDisc);	
		dateOk(d1, d2);	
		
		ComputerDTO computerDto = new ComputerDTO();
		Computer computer = new
	            Computer.ComputerBuilder()
	            .build();
		
		if(errors.isEmpty()){
			Util util = new Util();
			computer = computerDto.toDTO(id+","+nom+","+util.dateToString(d1)+","+util.dateToString(d2)+","+idCompany);
			result="Success";
			System.out.println("pas d'erreur donc création du computer test if ok");	 
		}
		else{
			result="Fail";
			System.out.println("des erreur dans la saisie"+errors.keySet()+", "+errors.values());
		}
		System.out.println("computer renvoyé par le validator: "+computer.toString());
		return computer;
	}
	

	public void checkName(String n){
		if(n==null){
			errors.put("NAME", "il faut saisir un nom");
		}
	}
	
	public Date checkDate(String dat){
		Date d = new Date();
		try{			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			d = sdf.parse(dat);
			return  d;
		}
		catch(Exception e){
			errors.put("DATE", "format de date invalide donc catché");
		}
		return d;
	}
	
	public void dateOk(Date d1 , Date d2){
		if((d2.getTime() - d1.getTime()) <= 0){
			errors.put("DATEsEPARATION", "Date de fin est avant ou est le même que la date d'introduction");
		}
	}
	
	/*public void checkCompany(Company com) {
		int check = CompanyDAO.INSTANCE.existCompany(com);
		if((check !=0) && (check !=-1)){
			System.out.println("company existe déja");
		}
		else if((check ==0)){
			errors.put("COMPANY", "Company inexistante");
		}
		else{
			errors.put("COMPANYsQL", "SQL error retry");
		}
	}*/
	
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
}
