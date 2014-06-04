package com.excilys.computerdatabase.webServices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.excilys.computerdatabase.model.Company;

@WebService
public interface CompanyWebService {

	@WebMethod
	public List<Company> getAll() throws java.rmi.RemoteException;
}
