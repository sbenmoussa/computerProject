package com.excilys.computerdatabase.validator;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class TestValidation {

	private static Validator validator;

	@BeforeClass
	public static void init() {
		// Crée le validateur
		ValidatorFactory factory =
				Validation.buildDefaultValidatorFactory();
		validator = (Validator) factory.getValidator();
	}

	@Test
	public void validComputer() {
		Computer computer= createValidComputer();
		Assert.assertEquals(0, 
				validator.validate(computer).size());
	}

	@Test
	public void name() {
		Computer computer =  createValidComputer();
		computer.setName(""); // 
		checkOneError(computer, "name");
	}

	@Test
	public void date() {
		Computer computer =  createValidComputer();
		computer.setIntroduced(new Date());
		checkOneError(computer, "introduced");
	}

	@Test
	public void email() {
		Computer computer = createValidComputer();
		computer.setCompany(new Company.CompanyBuilder().build()); 
		checkOneError(computer, "company");
	}

	private void checkOneError(Computer computer, String field) {
		Set<ConstraintViolation<Computer>> violations
		= validator.validate(computer);
		assertEquals(1, violations.size());
		for (ConstraintViolation<Computer> violation : violations)
			assertEquals(field, violation.getPropertyPath().toString());
	}

	private Computer createValidComputer() {
		Computer computer =  new Computer.ComputerBuilder().build();
		computer.setName("sone");
		computer.setIntroduced(new Date());
		computer.setDiscontinued(new Date());
		computer.setCompany(new Company.CompanyBuilder().build());
		return computer;
	}
}
