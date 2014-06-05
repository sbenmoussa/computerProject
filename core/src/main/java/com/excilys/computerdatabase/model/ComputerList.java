package com.excilys.computerdatabase.model;

import java.util.List;

public class ComputerList {

	private List<Computer> computerList;
	
	public ComputerList(List<Computer> computerList){
		this.computerList = computerList;
	}
	
	public String toString(){
		StringBuilder list = new StringBuilder("{");
		for(Computer c: computerList)
			list.append(c.toString());
		return list.append("}").toString();
		
	}
}
