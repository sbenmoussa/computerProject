package com.excilys.computerdatabase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

	public String dateToString(Date date){
		String result ="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if(date !=null){
			result = sdf.format(date);
		}
		return result;
	}
	
	public Date stringToDate(String date){
		Date result = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if(date != null){
//			System.out.println(date);
//			int day =Integer.parseInt(date.split("-")[2]);
//			int month =Integer.parseInt(date.split("-")[1]) -1;
//			int year =Integer.parseInt(date.split("-")[0]) - 1900;
//			result.setDate(day);
//			result.setYear(year);
//			result.setMonth(month);
			try {
				result = sdf.parse(date);
			} catch (ParseException e) {
				System.out.println("impossible de parser la date");
			}
			}	
		return result;
		
	}
	
}
