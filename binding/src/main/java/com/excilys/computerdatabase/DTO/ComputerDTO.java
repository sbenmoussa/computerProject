package com.excilys.computerdatabase.DTO;

import javax.validation.Valid;
import javax.validation.constraints.*;

//import org.springframework.format.annotation.DateTimeFormat;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Component;

//import com.excilys.computerdatabase.validator.ComputerDate;

@Component
public class ComputerDTO {


	private long id;

	@Size(min= 1, max = 255)
	private String name;

	//@Pattern(regexp = "", flags={})
	private  String introduced = "";
	
	final String introducedValue = introduced;

	//@ComputerDate(value = "")
	private String discontinued;

	@Valid
	private CompanyDTO company;

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
