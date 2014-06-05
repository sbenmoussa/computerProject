package com.excilys.computerdatabase.webServices;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface CompanyWebService {

	public List<Company> getAll() throws java.rmi.RemoteException;
}
