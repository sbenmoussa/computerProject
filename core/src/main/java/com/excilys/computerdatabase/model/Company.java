package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "id", insertable=false, updatable=false)
	private Long id;

	@Column( name = "name")
	private String name;


	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return this.id+","+this.name;
	}

	private Company(CompanyBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
	}

	public Company(){

	}
	
	public static CompanyBuilder builder() {
		return new CompanyBuilder();
	}

	public static class CompanyBuilder{
		private long id;
		private String name;

		public CompanyBuilder(){
			this.id = 0L;
			this.name = "Unknown";
		}

		public CompanyBuilder id(long id){
			if(id!=0){
				this.id = id;
			}
			return this;
		}

		public CompanyBuilder name(String name){
			if(!name.equals("Unknown")){
				this.name = name;
			}
			else{
				this.name= null;
			}		
			return this;
		}
		public Company build(){
			return new Company(this);
		}
	}
}
