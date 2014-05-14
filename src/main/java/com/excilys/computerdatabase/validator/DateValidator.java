package com.excilys.computerdatabase.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date intro = sdf.parse(introduced);
				Date disc = sdf.parse(date);
				if((disc.getTime() - intro.getTime()) <= 0){
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
