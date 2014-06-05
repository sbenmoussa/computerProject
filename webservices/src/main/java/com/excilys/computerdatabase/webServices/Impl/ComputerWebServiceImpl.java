package com.excilys.computerdatabase.webServices.Impl;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.model.ComputerList;
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
		return Response.status(200).entity("JAX-RS MARCHE avec Jackson!!!").build();
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
	public ComputerList getAll(String order, int page) throws RemoteException {
		return new ComputerList(computerService.getAll(order, page).getContent());
	}


	@Override
	@GET
	@Path("/filterByName")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerList filterByName(String name, String order, int page)
			throws RemoteException {
		return new ComputerList(computerService.filterByName(name, order, page).getContent());
	}


	@Override
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Computer find(@PathParam("id")  Long id) throws RemoteException {
		if(computerService == null)
			return new Computer.ComputerBuilder().build();
		return computerService.find(id);
	}
	
}