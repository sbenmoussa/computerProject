package com.excilys.computerdatabase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ComputerDate, String> {

	private String introduced;
	private String discontinued;
	
	@Override
	public void initialize(ComputerDate constraintAnnotation) {
		this.discontinued = constraintAnnotation.value();
		this.introduced = constraintAnnotation.introducedDate();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		System.out.println("dis à l'initialisation= "+ discontinued+" et objet récupéré en param de isvalid= "+date+" et introduced = "+introduced);
		if (((date == null) || (date.isEmpty()))){
			return true;
		}     
		else if((introduced.isEmpty()) || (introduced == null)){
			return true;
		}
		else{
			try{			
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime intro = formatter.parseDateTime(introduced);
				DateTime disc = formatter.parseDateTime(date);
				if((disc.toDate().getTime() - intro.toDate().getTime()) <= 0){
					return false;
				}
			}
			catch(Exception e){
				return false;
			}
			return true;
		}
	}

}
