package com.excilys.computerdatabase.webServices;

import javax.ws.rs.core.Response;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerList;


public interface ComputerWebService{


	public Response insert(Computer o) throws java.rmi.RemoteException;

	public Response update(Computer o) throws java.rmi.RemoteException;


	public void delete(Long id) throws java.rmi.RemoteException;

	public   ComputerList getAll(String order, int page) throws java.rmi.RemoteException;

	public  ComputerList filterByName(String name, String order, int page) throws java.rmi.RemoteException;

	public Computer find(Long id) throws java.rmi.RemoteException;

	Response test();
}
