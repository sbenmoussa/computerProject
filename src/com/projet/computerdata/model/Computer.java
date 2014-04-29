package com.projet.computerdata.model;
import java.util.Date;


public class Computer {

	private  Long id;
	private  String name;
	private  Date introduced;
	private  Date discontinued;
	private  Company company;
	
	private Computer(ComputerBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}

	public Date getIntroduced() {
		return introduced;
	}
	
	public String getName() {
		return name;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public Company getCompany() {
		return company;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
		return this.getName()+" introduit en "+this.getIntroduced()+" et discontinued en "+this.getDiscontinued()+" par "+this.getCompany().getName();
	}
	
	public static class ComputerBuilder{
		
		private String name;
		private Long id;
		private Date introduced;
		private Date discontinued;
		private Company company;
		
		public ComputerBuilder(Long id, String name, Date introduced, Date discontinued, Company company){
			this.id = id;
			this.name = name;
			this.introduced = introduced;
			this.discontinued = discontinued;
			this.company = company;
		}
		
		public ComputerBuilder(Long id){
			this.id = id;
		}
		
		public ComputerBuilder(String name){
			this.name = name;
		}
		
		public ComputerBuilder(){
			this.id = 0L;
			this.name = "Unknown";
			this.introduced = null;
			this.discontinued = null;
			this.company =new
		            Company.CompanyBuilder()
		            .build();
		}
		
		public ComputerBuilder id(Long id){
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
}
}
