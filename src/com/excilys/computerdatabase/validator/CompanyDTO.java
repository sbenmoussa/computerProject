package com.excilys.computerdatabase.validator;

import java.util.ArrayList;

import com.excilys.computerdatabase.modele.Company;

public class CompanyDTO {

	public Company toDTO(String data){
		Company company = new Company.CompanyBuilder().build();
		if(data != null){
			company = new
		            Company.CompanyBuilder()
		            .id(Long.parseLong(data.split(",")[0]))
		            .name(data.split(",")[1])
		            .build();
		}
		return company;
	}
	
	public String fromDTO(Company company){
		return company.getId()+","+company.getName();
	}
	
	public ArrayList<String> fromDTOList(ArrayList<Company> companies){
		ArrayList<String> result = new ArrayList<String>();
		for(Company c : companies){
			result.add(fromDTO(c));
		}
		return result;		
	}
}
