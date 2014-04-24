package com.projet.computerdata.util;

import java.util.Date;


public class Util {

	public String dateToString(Date date){
		String result ="";
		if(date !=null){
			result = new java.sql.Date(date.getTime())+""; //date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public Date stringToDate(String date){
		Date result = new Date();
		if(date != null){
			System.out.println(date);
			int day =Integer.parseInt(date.split("-")[2]);
			int month =Integer.parseInt(date.split("-")[1]) -1;
			int year =Integer.parseInt(date.split("-")[0]) - 1900;
			result.setDate(day);
			result.setYear(year);
			result.setMonth(month);
		}	
		return result;
		
	}
	
}
