package com.excilys.computerdatabase.view.validator;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.beans.Company;

@Component
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
