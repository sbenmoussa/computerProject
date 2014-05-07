package com.excilys.computerdatabase.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.excilys.computerdatabase.model.Computer;
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

	
	public Computer addComputer(ModelMap model){
		
		Long id = 0L;
		if(getValeurChamp(model, "idUpdate") !=null){
			id = Long.parseLong(getValeurChamp(model, "idUpdate"));
		}
		
		String nom = getValeurChamp( model, "name" );
		String dateIntroduction = getValeurChamp( model, "introducedDate" );;
		String dateDisc = getValeurChamp( model, "discontinuedDate" );;
		Long idCompany = Long.parseLong(getValeurChamp( model, "company" ));
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
	
	private static String getValeurChamp(ModelMap model, String nomChamp ) {
	    String valeur = (String) model.get( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
}
