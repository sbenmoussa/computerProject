package com.projet.computerdata.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.computerdata.model.Computer;
import com.projet.computerdata.service.ComputerDataService;
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
		String computer = null;
		if((request.getParameter("idUpdate") != null) && (!request.getParameter("idUpdate").equals(""))){
			Long idComputer = Long.parseLong(request.getParameter("idUpdate").trim());
			try {
				computer  = computerDto.fromDTO((Computer) ComputerDataService.INSTANCE.getElementById("computer", idComputer));
				companies = companyDto.fromDTOList( ComputerDataService.INSTANCE.getAll("company", 0));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
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
			try {
				ComputerDataService.INSTANCE.update("computer",computer);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute( "update", acf.getResult());	
		request.getRequestDispatcher( "Dashboard").forward( request, response );
		//response.sendRedirect("Dashboard");/WEB-INF/updateComputer.jsp
	}

}
