package com.excilys.computerdatabase.webServices.Impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.webServices.ComputerWebService;

@Component
@Path("/ComputerWebService")
public class ComputerWebServiceImpl   implements ComputerWebService{
	
	@Autowired
	private ComputerService computerService;

	@Override
	@GET
	@Path("/insert")
	public boolean insert(Computer o) throws RemoteException {
		return  computerService.insert(o) ;
	}

	@Override
	@GET
	@Path("/test")
	public Response test(){
		return Response.status(200).entity("JAX-RS MARCHE !!!").build();
	}

	@Override
	@GET
	@Path("/update")
	public boolean update(Computer o) throws RemoteException {
		return  computerService.update(o) ;
	}


	@Override
	@GET
	@Path("/delete")
	public void delete(Long id) throws RemoteException {
		computerService.delete(id); 
	}


	@Override
	@GET
	@Path("/getAll")
	public List<Computer> getAll(String order, int page) throws RemoteException {
		return computerService.getAll(order, page).getContent();
	}


	@Override
	@GET
	@Path("/filterByName")
	public List<Computer> filterByName(String name, String order, int page)
			throws RemoteException {
		return computerService.filterByName(name, order, page).getContent();
	}


	@Override
	@GET
	@Path("/find")
	public Computer find(Long id) throws RemoteException {
		return computerService.find(id);
	}
	
}