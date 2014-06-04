package com.excilys.computerdatabase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.bindingUtil.DateUtil;

@Component
public class DateValidator implements ConstraintValidator<ComputerDate, String> {

	private String date;
	
	@Autowired
	DateUtil util;
	
	@Override
	public void initialize(ComputerDate constraintAnnotation) {
		this.date= constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		
		if((this.date == null) || (this.date.isEmpty()) || (util.stringToDate(date) != null)){
			return true;
		}
		else{
			return false;
		}
	}

}
