package com.projet.computerdata.validator;

import java.util.ArrayList;

import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;
import com.projet.computerdata.util.*;
public class ComputerDTO {

	public Computer toDTO(String data){
		Computer computer = new Computer();
		Util util = new Util();
		if(data != null){
			computer.setId(Long.parseLong(data.split(",")[0]));
			computer.setName(data.split(",")[1]);
			if(data.split(",")[2] != null){
				computer.setIntroduced(util.stringToDate(data.split(",")[2]));
			}
			if(data.split(",")[3] != null){
				computer.setDiscontinued(util.stringToDate(data.split(",")[3]));
			}
			if(data.split(",")[4] != null){
				Company company = new Company();
				company.setId(Long.parseLong(data.split(",")[4]));
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
