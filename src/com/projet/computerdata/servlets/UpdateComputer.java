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
import com.projet.computerdata.model.Company;
import com.projet.computerdata.model.Computer;

/**
 * Servlet implementation class UpdateComputer
 */
@WebServlet("/UpdateComputer")
public class UpdateComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("l'id est "+request.getParameter("idUpdate"));
		List<Company> companies = new ArrayList<Company>();
		Computer computer = new Computer();
		if((request.getParameter("idUpdate") != null) && (!request.getParameter("idUpdate").equals(""))){
			Long idComputer = Long.parseLong(request.getParameter("idUpdate").trim());
			try {
				computer = ComputerDAO.INSTANCE.getComputerById(idComputer);
				companies = CompanyDAO.INSTANCE.getAllCompany();
				System.out.println(companies.get(0).getName());
				request.setAttribute("computer", computer);
				request.setAttribute("companies", companies);
				this.getServletContext().getRequestDispatcher( "/updateComputer.jsp" ).forward( request, response );
			} catch (IllegalAccessException e) {
				System.out.println("Une erreur catché donc pas de update");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println("l'id n'est pas arrivé");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
