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
	
	public  Company(){
		new Company.CompanyBuilder().build();
	}
	
	public static class CompanyBuilder{
		private long id;
		private String name;
		
		public CompanyBuilder(){
			this.setId(0L);
			this.setName("Unknown");
		}
		
		public CompanyBuilder(long id, String name){
			this.setId(id);
			this.setName(name);
		}
		
		public CompanyBuilder(String name){
			this.setId(0L);
			this.setName(name);
		}
		
		public CompanyBuilder(long id){
			this.setId(id);
			this.setName("Unknown");
		}
		
		public CompanyBuilder id(long id){
			if(id!=0){
				this.id = id;
			}
			return this;
		}
		
		public CompanyBuilder name(String name){
			if(!name.equals("Unknown")){
				this.setName(name);
			}
			else{
				this.name= null;
			}		
			return this;
		}
		public Company build(){
			return new Company(this);
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
	}
}
