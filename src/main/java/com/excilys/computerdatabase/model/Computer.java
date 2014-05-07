package com.excilys.computerdatabase.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.*;
//Simport org.springframework.stereotype.Component;


//@Component
public class Computer {

	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;
	
	private Computer(ComputerBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}

	public Computer(){
		new Computer.ComputerBuilder().build();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
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
	
	@Required
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString(){
		return this.id+","+this.name+","+this.introduced+","+this.discontinued+","+this.company.toString();
	}
	
	public static class ComputerBuilder{
		private long id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private Company company;
		
		public ComputerBuilder(){
			this.setId(0L);
			this.setName("Unknown");
			this.setIntroduced(null);
			this.setDiscontinued(null);
			this.setCompany(new Company.CompanyBuilder().build());
		}
		
		public ComputerBuilder(long id, String name, Date introduced, Date discontinued, Company company){
			this.setId(id);
			this.setName(name);
			this.setIntroduced(introduced);
			this.setDiscontinued(discontinued);
			this.setCompany(company);
		}
		
		public ComputerBuilder id(long id){
			this.id = id;
			return this;
		}
		
		public ComputerBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public ComputerBuilder introduced(Date introduced){
			this.introduced = introduced;
			return this;
		}
		
		public ComputerBuilder discontinued(Date discontinued){
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder company(Company company){
			this.company = company;
			return this;
		}
		
		public Computer build(){
			return new Computer(this);
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getIntroduced() {
			return introduced;
		}

		public void setIntroduced(Date introduced) {
			this.introduced = introduced;
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
	}
}
