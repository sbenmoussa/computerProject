package com.excilys.computerdatabase.webServices;

import com.excilys.computerdatabase.model.CompanyList;

public interface CompanyWebService {

	public CompanyList getAll() throws java.rmi.RemoteException;
}
