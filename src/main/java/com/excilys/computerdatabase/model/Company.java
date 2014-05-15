package com.excilys.computerdatabase.model;


public class Company {

	private long id;
	private String name;
	
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
			this.id = id;
			return this;
		}
		
		public CompanyBuilder name(String name){
			this.setName(name);
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
