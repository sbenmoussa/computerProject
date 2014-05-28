package com.excilys.computerdatabase.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidatorComputer.class )
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComputerValid {

	String message() default "Computer data are invalid check that the discontinued date is before Introduced date";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}