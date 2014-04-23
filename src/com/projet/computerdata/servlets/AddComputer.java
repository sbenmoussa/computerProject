package com.projet.computerdata.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.computerdata.dao.CompanyDAO;
import com.projet.computerdata.dao.ComputerDAO;
import com.projet.computerdata.model.AddComputerForm;
import com.projet.computerdata.model.Company;

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
		try {
			companies = CompanyDAO.INSTANCE.getAllCompany();
			request.setAttribute("companies", companies);
			this.getServletContext().getRequestDispatcher( "/addComputer.jsp" ).forward( request, response );
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddComputerForm acf = new AddComputerForm();
		try {
			acf.addComputer(request);
		} catch (Exception e) {
			System.out.println("L'ajout n'a pas pu être fait car : "+e.getMessage());
		}
		request.setAttribute( "success", acf.getResult());
		//this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
		response.sendRedirect("Dashboard");
	}

}
