package com.excilys.computerdatabase.bindingUtil;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	@Autowired
    private MessageSource messageSource;
	
	public String dateToString(DateTime date){
		String result ="";
		Locale locale = LocaleContextHolder.getLocale();
		String pattern = messageSource.getMessage("date.pattern", null, locale); 
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		if(date !=null){
			result = fmt.print(date);
		}
		return result;
	}

	public DateTime stringToDate(String date){
		DateTime result = null;
		if((date != null) && (!date.equals(""))){
			Locale locale = LocaleContextHolder.getLocale();
			String pattern = messageSource.getMessage("date.pattern", null, locale);
			DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
			result = formatter.parseDateTime(date);
		}	
		return result;
	}
}
