package com.projet.computerdata.model;

public class Company {

	private final String name;
	private final Long id;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	
	public String toString(){
		return this.id+" : "+this.name;
	}
	
	
	private Company(CompanyBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
	}
	
	public static class CompanyBuilder{
		
		private String name;
		private Long id;
		
		public CompanyBuilder(Long id, String name){
			this.id = id;
			this.name = name;
		}
		
		public CompanyBuilder(Long id){
			this.id = id;
		}
		
		public CompanyBuilder(String name){
			this.name = name;
		}
		
		public CompanyBuilder(){
			this.id = 0L;
			this.name = "Unknown";
		}
		
		public CompanyBuilder id(Long id){
			this.id = id;
			return this;
		}
		
		public CompanyBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public Company build(){
			return new Company(this);
		}
		
	}
}


