package com.excilys.computerdatabase.validator;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.*;

//import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.*;

@Component
public class ComputerDTO {
	
	
	private long id;
	@NotNull(message = "The Computer name must not be null")
	@Size(min= 1, max = 255)
	//@SafeHtml()
	private String name;
	@NotNull(message = "The introduced date must not be null")
	@Pattern(regexp = "yyyy-MM-dd")
	private String introduced;
	@NotNull(message = "The discontinued date must not be null")
	@Pattern(regexp = "yyyy-MM-dd")
	private String discontinued;
	@NotNull(message = "The Company must be ")
	@Valid
	private Company company;

	public Computer toDTO(String data){
		Util util = new Util();
		Computer computer = new
	            Computer.ComputerBuilder().id(id).name(name).introduced(util.stringToDate(introduced)).discontinued(util.stringToDate(discontinued)).company(company)
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
	
	public ArrayList<String> fromDTOList(ArrayList<Computer> computers){
		ArrayList<String> result = new ArrayList<String>();
		for(Computer c : computers){
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
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
