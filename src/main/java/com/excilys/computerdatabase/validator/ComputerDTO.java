package com.excilys.computerdatabase.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;

//import org.springframework.format.annotation.DateTimeFormat;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.*;
//import com.excilys.computerdatabase.validator.ComputerDate;

@Component
public class ComputerDTO {


	private long id;

	@Size(min= 1, max = 255)
	private String name;

	@Pattern(regexp = "^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}$")
	private  String introduced = "";
	
	final String introducedValue = introduced;

	//@ComputerDate(introducedDate = introducedValue , value = "")
	@Pattern(regexp = "^[0-9]{4}(-)[0-9]{2}(-)[0-9]{2}$")
	private String discontinued;

	@Valid
	private CompanyDTO company;
	
	private Util util = new Util();

	//@NotEmpty(message = "The introduced date must not be null")
	//@NotNull(message = "The Company must be ")
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	//@NotEmpty(message = "The discontinued date must not be null")
	//@SafeHtml() 
	//@NotEmpty(message = "The Computer name must not be null")

	public Computer toDTO(String data){
		
		Computer computer = new
				Computer.ComputerBuilder().id(id).name(name).introduced(util.stringToDate(introduced)).discontinued(util.stringToDate(discontinued)).company(company.toDTO(""))
				.build();		
		if((data != null) && (!data.equals(""))){
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

	public List<String> fromDTOList(List<Computer> list){
		ArrayList<String> result = new ArrayList<String>();
		for(Computer c : list){
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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		//if(util.stringToDate(discontinued).isBefore(util.stringToDate(getIntroduced())))
			this.discontinued = discontinued;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
