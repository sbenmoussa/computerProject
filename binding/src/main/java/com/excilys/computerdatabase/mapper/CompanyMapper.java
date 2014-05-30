package com.excilys.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.DTO.CompanyDTO;
import com.excilys.computerdatabase.model.Company;

public class CompanyMapper {
	
	public CompanyDTO toDTO(Company company){
		CompanyDTO mapped = new CompanyDTO();
		try{
		mapped.setId(company.getId());
		mapped.setName(company.getName());
		}catch(NullPointerException e){
			mapped.setId(0L);
			mapped.setName("Unknown");
		}
		return mapped;
	}
	
	public Company fromDTO(CompanyDTO companydto){
		//return null;
		return new Company.CompanyBuilder().id(companydto.getId()).name(companydto.getName()).build();
	}
	
	public List<CompanyDTO> toDTOList(List<Company> list){
		List<CompanyDTO> result = new ArrayList<CompanyDTO>();
		for(Company c : list){
			result.add(toDTO(c));
		}
		return result;
	}
}
