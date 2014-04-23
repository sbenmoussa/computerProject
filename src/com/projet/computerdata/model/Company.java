package com.projet.computerdata.model;

public class Company {

	private String name;
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Company(String name){
		this.name = name;
	}
	
	public Company(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Company(){
		this.name ="unknown";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
		return this.id+" : "+this.name;
	}
}
