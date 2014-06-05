package com.excilys.computerdatabase.webServices;

import java.util.List;
import javax.ws.rs.core.Response;

import com.excilys.computerdatabase.model.Computer;


public interface ComputerWebService{


	public Response insert(Computer o) throws java.rmi.RemoteException;

	public Response update(Computer o) throws java.rmi.RemoteException;


	public void delete(Long id) throws java.rmi.RemoteException;

	public   List<Computer> getAll(String order, int page) throws java.rmi.RemoteException;

	public  List<Computer> filterByName(String name, String order, int page) throws java.rmi.RemoteException;

	public Computer find(Long id) throws java.rmi.RemoteException;

	Response test();
}
