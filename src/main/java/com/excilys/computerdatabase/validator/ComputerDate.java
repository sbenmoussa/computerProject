package com.excilys.computerdatabase.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface ComputerDate {
	String message() default "Invalid date";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String value();
	String introducedDate() default "";
	// Et là on ajoute notre liste de conditions
	//public Condition[] conditions() default {};
}