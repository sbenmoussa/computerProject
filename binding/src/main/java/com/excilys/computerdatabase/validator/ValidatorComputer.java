package com.excilys.computerdatabase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.DTO.ComputerDTO;
import com.excilys.computerdatabase.util.Util;


@Component
public class ValidatorComputer  implements ConstraintValidator<ComputerValid, ComputerDTO>{

	@Autowired
	Util util;
	
	@Override
	public void initialize(ComputerValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
		
		if ( computer == null ) {
            return true;
        }

        return dateOk(util.stringToDate(computer.getIntroduced()), util.stringToDate(computer.getDiscontinued()));
	}
	
	/**
	 * vérification si date d'introduction est supérieur à celle de discontinued
	 * @param d1
	 * @param d2
	 */
	public boolean dateOk(DateTime d1 , DateTime d2){
		if((d2.toDate().getTime() - d1.toDate().getTime()) <= 0){
			return false;
		}
		return true;
	}
}
