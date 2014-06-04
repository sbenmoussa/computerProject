package com.excilys.computerdatabase.webServices.Impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.webServices.ComputerWebService;

@WebService
public class ComputerWebServiceImpl   implements ComputerWebService{
	
	@Autowired
	private ComputerService computerService;

	@Override
	public boolean insert(Computer o) throws RemoteException {
		return  computerService.insert(o) ;
	}


	@Override
	public boolean update(Computer o) throws RemoteException {
		return  computerService.update(o) ;
	}


	@Override
	public void delete(Long id) throws RemoteException {
		computerService.delete(id); 
	}


	@Override
	public List<Computer> getAll(String order, int page) throws RemoteException {
		return computerService.getAll(order, page).getContent();
	}


	@Override
	public List<Computer> filterByName(String name, String order, int page)
			throws RemoteException {
		return computerService.filterByName(name, order, page).getContent();
	}


	@Override
	public Computer find(Long id) throws RemoteException {
		return computerService.find(id);
	}
	
}