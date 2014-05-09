package com.excilys.computerdatabase.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.cdi.HibernateValidator;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.Util;

import javax.inject.Inject;
//import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

//import org.junit.BeforeClass;
//import org.junit.Test;

@Component
//@ApplicationScoped
public class ValidatorComputer{


	private String result;
	private Map<String, String> errors = new HashMap<String,String>();

	public String getResult() {
		return result;
	}
	public Map<String, String> getErrors() {
		return errors;
	}

	@Inject
	@HibernateValidator
	private static Validator validator;

	@Inject
	@HibernateValidator
	private static ValidatorFactory validatorFactory;

	//@BeforeClass
	public static void setUp() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 * vérifie si données récupérées sont valides et renvoie de l'objet computer correspondant
	 * @param computerdto  : renvoyé par la jsp (Spring MVC)
	 * @return
	 */
	public Computer isValid(ComputerDTO computerdto){

		Long id = 0L;
		String nom = computerdto.getName();
		Date dateIntroduction = checkDate(computerdto.getIntroduced());
		Date dateDisc = checkDate(computerdto.getDiscontinued());
		Long idCompany = computerdto.getCompany().getId();

		checkName(nom) ;			
		dateOk(dateIntroduction, dateDisc);	
		Computer computer = new
				Computer.ComputerBuilder()
		.build();

		if(errors.isEmpty()){
			Util util = new Util();
			computer = computerdto.toDTO(id+","+nom+","+util.dateToString(dateIntroduction)+","+util.dateToString(dateDisc)+","+idCompany);
			result="Success";
			System.out.println("pas d'erreur dans les données computer reçu donc valid");	 
		}
		else{
			result="Fail";
			System.out.println("des erreur dans la saisie:  "+errors.keySet()+", "+errors.values());
		}
		System.out.println("computer renvoyé par le validator: "+computer.toString());
		return computer;
	}

	/**
	 * vérifie si un nom est nom null 
	 * @param n
	 */
	public void checkName(String n){
		if(n==null){
			errors.put("NAME", "il faut saisir un nom");
		}
	}

	/**
	 * vérification du format de la date si ok et conversion du string en date
	 * @param dat
	 * @return
	 */
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

	/**
	 * vérification si date d'introduction est supérieur à celle de discontinued
 	 * @param d1
	 * @param d2
	 */
	public void dateOk(Date d1 , Date d2){
		if((d2.getTime() - d1.getTime()) <= 0){
			errors.put("DATEsEPARATION", "Date de fin est avant ou est le même que la date d'introduction");
		}
	}
}
