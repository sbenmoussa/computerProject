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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Object> companies = new ArrayList<Object>();
		try {
			companies = ComputerDataService.INSTANCE.getAll("company",0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			try {
				ComputerDataService.INSTANCE.insert("computer", newComputer);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute( "add", acf.getResult());
		request.getRequestDispatcher( "Dashboard").forward( request, response );
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/addComputer.jsp" ).forward( request, response );
		//response.sendRedirect("Dashboard");
	}

}
