package com.excilys.computerdatabase.webServices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.excilys.computerdatabase.model.Computer;

@WebService
public interface ComputerWebService{

	@WebMethod
	public boolean insert(Computer o) throws java.rmi.RemoteException;

	@WebMethod
	public boolean update(Computer o) throws java.rmi.RemoteException;

	@WebMethod
	public void delete(Long id) throws java.rmi.RemoteException;

	@WebMethod
	public   List<Computer> getAll(String order, int page) throws java.rmi.RemoteException;

	@WebMethod
	public  List<Computer> filterByName(String name, String order, int page) throws java.rmi.RemoteException;

	@WebMethod
	public Computer find(Long id) throws java.rmi.RemoteException;
}
