package com.excilys.computerdatabase.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util {

	public String dateToString(DateTime date){
		String result ="";
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		if(date !=null){
			result = fmt.print(date);
		}
		return result;
	}

	public DateTime stringToDate(String date){
		DateTime result = new DateTime();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date != null){
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			result = formatter.parseDateTime(date);
		}	
		return result;
	}
}
