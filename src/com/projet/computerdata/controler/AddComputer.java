package com.projet.computerdata.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.computerdata.dao.CompanyDAO;
import com.projet.computerdata.dao.ComputerDAO;
import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;
import com.projet.computerdata.validator.ValidatorForm;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Company> companies = new ArrayList<Company>();
		companies = CompanyDAO.INSTANCE.getAllCompany();
		request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/addComputer.jsp" ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidatorForm acf = new ValidatorForm();
		Computer newComputer;
		newComputer = acf.addComputer(request);
		if(acf.getResult().equals("Success")){
			ComputerDAO.INSTANCE.addComputer(newComputer);
		}
		request.setAttribute( "add", acf.getResult());
		//this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
		response.sendRedirect("Dashboard");
	}

}
