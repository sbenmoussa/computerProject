package com.excilys.computerdatabase.DTO;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class CompanyDTO {

	//@NotEmpty(message = "The Company name must not be null")
	private String name ="";
	//@NotNull(message = "The Company id must not be NULL")
	private Long id;

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
