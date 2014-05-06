package com.excilys.computerdatabase.controler;

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

import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.validator.ComputerDTO;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
	
	@Override
	public void init() throws ServletException{
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<String> computers = new ArrayList<String>();
		ComputerDTO computerDto = new ComputerDTO();
		String name = request.getParameter( "search" );
		if((request.getParameter( "order" ) != null) && (request.getParameter( "order" ) != "")){

			if(name !=null){
				computers = computerDto.fromDTOList( computerService.filterByName(name, Integer.parseInt(request.getParameter( "order" ) )));
				request.setAttribute("search", name);
			}
			else{
				computers = computerDto.fromDTOList( computerService.getAll(Integer.parseInt(request.getParameter( "order" ) )));		
			}
			request.setAttribute("order", request.getParameter( "order" ));
		}

		else{
			if(name !=null){
				computers = computerDto.fromDTOList(computerService.filterByName(name, 0));
				request.setAttribute("search", name);
			}
			else{
				computers = computerDto.fromDTOList(computerService.getAll(0));		
			}
			request.setAttribute("order", 0);
		}

		request.setAttribute("computers", computers);
		if(request.getParameter( "page" ) != null){
			request.setAttribute("page", request.getParameter( "page" ));
		}	
		else{
			request.setAttribute("page", 0);
		}
		
		if(request.getParameter( "update" ) != null){
			System.out.println("un apdate effectue "+request.getParameter( "update" ));
			request.setAttribute("update", request.getAttribute( "update" ));
		}	 	
		
		if(request.getParameter( "add" ) != null){
			request.setAttribute("add", request.getParameter( "add" ));
		}	
		System.out.println();
		this.getServletContext().getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward( request, response );
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans la méthode post du dashbord de nouveau");
		doGet(request, response);
	}

}
