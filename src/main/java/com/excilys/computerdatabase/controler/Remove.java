package com.excilys.computerdatabase.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class Remove
 */
@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
	public void setComputerService(ComputerService computerService){
		this.computerService = computerService;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id computer : "+request.getParameter("idComputer"));
		
		if ((request.getParameter("idComputer") !=null) && (!request.getParameter("idComputer").equals(""))){
			System.out.println(request.getParameter("idComputer"));
			try {
				computerService.delete(Long.parseLong(request.getParameter("idComputer")));
			} catch (NumberFormatException e) {
				System.out.println("idComputer n'a pas le bon format --> pas un long");
			}
			this.getServletContext().getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward( request, response );
		}
		else{
			System.out.println("vide");
		}	
	}

}
