package com.excilys.computerdatabase.webServices.Impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

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
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Computer o) throws RemoteException {
		return Response.status(200).entity("Résultat de l'insertion:  "+computerService.insert(o)).build();  
	}

	@Override
	@GET
	@Path("/test")
	public Response test(){
		return Response.status(200).entity("JAX-RS MARCHE !!!").build();
	}

	@Override
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Computer o) throws RemoteException {
		return  Response.status(200).entity("Résultat de l'insertion:  "+computerService.update(o)).build() ;
	}


	@Override
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(Long id) throws RemoteException {
		computerService.delete(id); 
	}


	@Override
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Computer> getAll(String order, int page) throws RemoteException {
		return computerService.getAll(order, page).getContent();
	}


	@Override
	@GET
	@Path("/filterByName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Computer> filterByName(String name, String order, int page)
			throws RemoteException {
		return computerService.filterByName(name, order, page).getContent();
	}


	@Override
	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Computer find(Long id) throws RemoteException {
		return computerService.find(id);
	}
	
}