package com.excilys.computerdatabase.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;

@Component
public class CompanyDTO {

	//@NotEmpty(message = "The Company name must not be null")
	private String name ="";
	@NotNull(message = "The Company id must not be NULL")
	private Long id;
	public Company toDTO(String data){
		Company company = new Company.CompanyBuilder().name(name).id(id).build();
		if((data != null) && (!data.equals(""))){
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
	
	public ArrayList<String> fromDTOList(List<Company> list){
		ArrayList<String> result = new ArrayList<String>();
		for(Company c : list){
			result.add(fromDTO(c));
		}
		return result;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
