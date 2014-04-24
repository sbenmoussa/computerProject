package com.projet.computerdata.validator;

import java.util.ArrayList;

import com.projet.computerdata.model.Company;

public class CompanyDTO {

	public Company toDTO(String data){
		Company company = new Company();
		if(data != null){
			company.setId(Long.parseLong(data.split(",")[0]));
			company.setName(data.split(",")[1]);
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
