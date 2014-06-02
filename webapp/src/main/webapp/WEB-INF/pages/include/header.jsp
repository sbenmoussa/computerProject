<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
<head>
<title><spring:message code="title" text="default text" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="topbar">
		
		
		<c:if test='${ pageContext.request.remoteUser != null }' var="result">
			<div >
	    		<p >${pageContext.request.remoteUser} </p>
			</div>
			<a href="<c:url value='j_spring_security_logout'/>" > <spring:message code="logout" text="Logout" /></a>
		</c:if>
			
		<h1 class="fill">
			<a href="dashboard"><spring:message code="header" text="default text" /> </a>	<a href="?language=fr_FR"><img src="images/fr.jpg" style="position:relative;left:5px;" height="25" width="25" /> </a>  <a href="?language=en"><img src="images/en.jpeg" style="position:relative;left:5px;" height="25" width="25" /></a>
		</h1>
	</header>
