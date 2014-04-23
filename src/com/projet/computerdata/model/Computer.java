package com.projet.computerdata.model;
import java.util.Date;


public class Computer {

	private Long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Computer(Long id, String name, Date introduced, Date discontinued, Company company){
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	
	public Computer(){
		this.name = "Undefined";
		this.introduced = null;
		this.discontinued =null;
		this.company = new Company();
	}
	
	public String toString(){
		return this.getName()+" introduit en "+this.getIntroduced()+" et discontinued en "+this.getDiscontinued()+" par "+this.getCompany().getName();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
