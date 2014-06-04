package com.excilys.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.DTO.ComputerDTO;
import com.excilys.computerdatabase.bindingUtil.DateUtil;
import com.excilys.computerdatabase.model.Computer;

public class ComputerMapper {

	@Autowired
	private DateUtil util;
	
	public ComputerDTO toDTO(Computer computer){
		ComputerDTO mapped = new ComputerDTO();
		mapped.setId(computer.getId());
		mapped.setName(computer.getName());
		mapped.setIntroduced(util.dateToString(computer.getIntroduced()));
		mapped.setDiscontinued(util.dateToString(computer.getDiscontinued()));
		mapped.setCompany(new CompanyMapper().toDTO(computer.getCompany()));
		return mapped;
	}
	
	public Computer fromDTO(ComputerDTO computerdto){
		if(computerdto.getCompany().getId() == 0){
			return  Computer.builder().id(computerdto.getId()).name( computerdto.getName()).introduced(util.stringToDate(computerdto.getIntroduced())) 
					.discontinued(util.stringToDate(computerdto.getDiscontinued())).company(null)
					.build();
		}
		return Computer.builder().id(computerdto.getId()).name( computerdto.getName())
				.introduced(util.stringToDate(computerdto.getIntroduced())).discontinued(util.stringToDate(computerdto.getDiscontinued())).company( 
				new CompanyMapper().fromDTO(computerdto.getCompany())).build();
	}
	
	public List<ComputerDTO> toDTOList(List<Computer> list){
		List<ComputerDTO> result = new ArrayList<ComputerDTO>();
		for(Computer c : list){
			result.add(toDTO(c));
		}
		return result;		
	}
}
