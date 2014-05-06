package com.excilys.computerdatabase.controler.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.model.beans.Computer;
import com.excilys.computerdatabase.model.service.CompanyService;
import com.excilys.computerdatabase.model.service.ComputerService;
import com.excilys.computerdatabase.view.validator.*;

/**
 * Servlet implementation class UpdateComputer
 */
@WebServlet("/UpdateComputer")
public class UpdateComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
       
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ComputerDTO computerDto;
	
	@Autowired
	CompanyDTO companyDto;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComputer() {
        super();
    }

    @Override
	public void init() throws ServletException{
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("l'id est "+request.getParameter("idUpdate"));
		List<String> companies = new ArrayList<String>();
		String computer = null;
		if((request.getParameter("idUpdate") != null) && (!request.getParameter("idUpdate").equals(""))){
			Long idComputer = Long.parseLong(request.getParameter("idUpdate").trim());
			Computer cp = computerService.find(idComputer);
			computer  = computerDto.fromDTO(cp);
			companies = companyDto.fromDTOList( companyService.getAll(0));		
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
			computerService.update(computer);
		}
		request.setAttribute( "update", acf.getResult());	
		request.getRequestDispatcher( "Dashboard").forward( request, response );
		//response.sendRedirect("Dashboard");/WEB-INF/updateComputer.jsp
	}

}
