package com.excilys.computerdatabase.model;

import java.util.List;

public class CompanyList {

private List<Company> companyList;
	
	public CompanyList(List<Company> companyList){
		this.companyList = companyList;
	}
	
	public String toString(){
		StringBuilder list = new StringBuilder("{");
		for(Company c: companyList)
			list.append(c.toString());
		return list.append("}").toString();
		
	}
}
