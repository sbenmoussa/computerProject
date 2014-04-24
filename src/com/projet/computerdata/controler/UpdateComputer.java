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
import com.projet.computerdata.model.Computer;
import com.projet.computerdata.validator.*;

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
		List<String> companies = new ArrayList<String>();
		ComputerDTO computerDto = new ComputerDTO();
		CompanyDTO companyDto = new CompanyDTO();
		String computer;
		if((request.getParameter("idUpdate") != null) && (!request.getParameter("idUpdate").equals(""))){
			Long idComputer = Long.parseLong(request.getParameter("idUpdate").trim());
			computer  = computerDto.fromDTO(ComputerDAO.INSTANCE.getComputerById(idComputer));
			companies = companyDto.fromDTOList( CompanyDAO.INSTANCE.getAllCompany());
			request.setAttribute("computer", computer);
			request.setAttribute("companies", companies);
			request.setAttribute("idUpdate", request.getParameter("idUpdate"));
			this.getServletContext().getRequestDispatcher( "/WEB-INF/updateComputer.jsp" ).forward( request, response );
		}
		else{
			System.out.println("l'id du computer n'a pas été reçu par la servlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidatorForm acf = new ValidatorForm();
		Computer computer;
		computer = acf.addComputer(request);
		if(acf.getResult().equals("Success")){
			ComputerDAO.INSTANCE.updateComputer(computer);
		}
		request.setAttribute( "update", acf.getResult());
		//this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
		response.sendRedirect("Dashboard");
	}

}
