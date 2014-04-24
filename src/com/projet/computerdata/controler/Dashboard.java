package com.projet.computerdata.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.computerdata.dao.ComputerDAO;
import com.projet.computerdata.validator.ComputerDTO;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				computers = computerDto.fromDTOList( ComputerDAO.INSTANCE.filterComputerByName(name, Integer.parseInt(request.getParameter( "order" ) )));				
			}
			else{
				
				computers = computerDto.fromDTOList(  ComputerDAO.INSTANCE.getAllComputer(Integer.parseInt(request.getParameter( "order" ) )));		
			}
			request.setAttribute("order", request.getParameter( "order" ));
		}
		
		else{
			if(name !=null){
				computers = computerDto.fromDTOList( ComputerDAO.INSTANCE.filterComputerByName(name, 0));				
			}
			else{
				
				computers = computerDto.fromDTOList(  ComputerDAO.INSTANCE.getAllComputer(0));		
			}
			request.setAttribute("order", 0);
		}
		
		request.setAttribute("computers", computers);
		if(request.getParameter( "page" ) != null){
			request.setAttribute("page", request.getParameter( "page" ));
		}	
		this.getServletContext().getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward( request, response );
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
