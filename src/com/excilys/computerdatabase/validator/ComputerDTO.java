package com.excilys.computerdatabase.validator;

import java.util.ArrayList;

import com.excilys.computerdatabase.modele.Company;
import com.excilys.computerdatabase.modele.Computer;
import com.excilys.computerdatabase.util.*;
public class ComputerDTO {

	public Computer toDTO(String data){
		Computer computer = new
	            Computer.ComputerBuilder()
	            .build();
		Util util = new Util();
		if(data != null){
			computer = new
            Computer.ComputerBuilder()
			.id(Long.parseLong(data.split(",")[0]))
			.name(data.split(",")[1])
            .build();
			if(data.split(",")[2] != null){
				computer.setIntroduced(util.stringToDate(data.split(",")[2]));
			}
			if(data.split(",")[3] != null){
				computer.setDiscontinued(util.stringToDate(data.split(",")[3]));
			}
			if(data.split(",")[4] != null){
				Company company = new
			            Company.CompanyBuilder()
			            .id(Long.parseLong(data.split(",")[4]))
			            .build();
				computer.setCompany(company);
				}
		}
		return computer;
	}
	
	public String fromDTO(Computer computer){
		Util util = new Util();
		return computer.getId()+","+computer.getName()+","+util.dateToString(computer.getIntroduced())+","+util.dateToString(computer.getDiscontinued())+","+computer.getCompany().getId()+","+computer.getCompany().getName();
	}
	
	public ArrayList<String> fromDTOList(ArrayList<Computer> computers){
		ArrayList<String> result = new ArrayList<String>();
		for(Computer c : computers){
			result.add(fromDTO(c));
		}
		return result;		
	}
}
