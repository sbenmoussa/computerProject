package com.excilys.computerdatabase.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Throwables;

@Controller
class CustomErrorController {

	@RequestMapping("error")
	public String customError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		System.out.println("--------------------------------------------- ");
		System.out.println("L'UTILISATEUR CONNECTÉ EST "+request.getRemoteUser()+" si user "+request.isUserInRole("ROLE_USER")+" utilisateur principale "+request.getUserPrincipal()+" est secure "+request.isSecure());
		System.out.println("--------------------------------------------- ");
		// String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		String message = MessageFormat.format(" An error {0} returned for {1} with message {3}",
				statusCode, requestUri, (!exceptionMessage.isEmpty())? exceptionMessage: "none"
				);
		
		System.out.println("-------erreur--------------- ");
		System.out.println(message);
		System.out.println("--------------------------------------------- ");
		model.addAttribute("errorMessage", message); 
		request.setAttribute("errorMessage", message);
		return "customError";
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}
}