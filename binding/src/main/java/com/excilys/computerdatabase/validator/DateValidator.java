package com.excilys.computerdatabase.validator;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class DateValidator implements ConstraintValidator<ComputerDate, String> {

	private String introduced;
	private String date;
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public void initialize(ComputerDate constraintAnnotation) {
		this.date= constraintAnnotation.value();
		this.introduced = constraintAnnotation.introducedDate();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		System.out.println("dis à l'initialisation= "+ date+" et objet récupéré en param de isvalid= "+date+" et introduced = "+introduced);
//		WebApplicationContext webApplicationContext = this.getRequestContext().getWebApplicationContext();
//        //A utiliser pour récupérer un bean
//        webApplicationContext.getBean("monBean");
//        //A utiliser pour avoir la locale
//        this.getRequestContext().getLocale();
//        //a utiliser pour avoir une traduction, la locale est implicite dans ce cas car il s'agit du contexte de la page
//        this.getRequestContext().getMessage("maKey");
		Locale locale  = new Locale("fr");
		String pattern = messageSource.getMessage("pattern.date.regex", null, locale);
		
		if((this.date.matches(pattern)) || (this.date.isEmpty())){
			return true;
		}
		else{
			return false;
		}
//		if (((date == null) || (date.isEmpty()))){
//			return true;
//		}     
//		else if((introduced.isEmpty()) || (introduced == null)){
//			return true;
//		}
//		else{
//			try{			
//				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//				DateTime intro = formatter.parseDateTime(introduced);
//				DateTime disc = formatter.parseDateTime(date);
//				if((disc.toDate().getTime() - intro.toDate().getTime()) <= 0){
//					return false;
//				}
//			}
//			catch(Exception e){
//				return false;
//			}
//			return true;
//		}
	}

}
