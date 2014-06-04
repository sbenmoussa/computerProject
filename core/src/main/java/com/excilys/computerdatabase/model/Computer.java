package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.excilys.computerdatabase.model.Company.CompanyBuilder;

@Entity
@Table(name = "computer")
public class Computer{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "id")
	private long id;
	
	@Column( name = "name" )
	private String name;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column( name = "introduced" )
	private DateTime introduced;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column( name = "discontinued" )
	private DateTime discontinued;
	
	@ManyToOne(targetEntity=Company.class, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id",  unique=true)
	private Company company;
	
	private Computer(ComputerBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}
	
	private Computer(){
	}
	
	public Long getId() {
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
	public DateTime getIntroduced() {
		return introduced;
	}
	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}
	public DateTime getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString(){
		return this.id+","+this.name+","+this.introduced+","+this.discontinued+","+this.company.toString();
	}
	
	public static ComputerBuilder builder() {
		return new ComputerBuilder();
	}
	
	public static class ComputerBuilder{
		private long id;
		private String name;
		private DateTime introduced;
		private DateTime discontinued;
		private Company company;
		
		public ComputerBuilder(){
			this.id =0L;
			this.name = "Unknown";
			this.introduced = null;
			this.discontinued = null;
			this.company = new Company.CompanyBuilder().build();
		}

		public ComputerBuilder id(long id){
			if(id!=0){
				this.id = id;
			}
			return this;
		}

		public ComputerBuilder name(String name){
			if(!name.equals("Unknown")){
				this.name = name;
			}
			else{
				this.name= null;
			}		
			return this;
		}
		
		public ComputerBuilder introduced(DateTime introduced){
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder discontinued(DateTime discontinued){
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
