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

import com.excilys.computerdatabase.model.beans.Company;
import com.excilys.computerdatabase.model.beans.Computer;
import com.excilys.computerdatabase.model.service.CompanyService;
import com.excilys.computerdatabase.model.service.ComputerService;
import com.excilys.computerdatabase.view.validator.ValidatorForm;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
	@Autowired
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService){
		this.companyService = companyService;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
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
		
		List<Company> companies = new ArrayList<Company>();
		companies = companyService.getAll(0);
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
			computerService.insert(newComputer);
		}
		request.setAttribute( "add", acf.getResult());
		request.getRequestDispatcher( "Dashboard").forward( request, response );
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/addComputer.jsp" ).forward( request, response );
		//response.sendRedirect("Dashboard");
	}

}
