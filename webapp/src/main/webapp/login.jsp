<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
<title><spring:message code="title" text="default text" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>

<body>
	<c:if test='${ pageContext.request.remoteUser != null }' var="result">
			<div >
	    		<p >${pageContext.request.remoteUser} </p>
			</div>
			<a href="<c:url value='j_spring_security_logout'/>" > <spring:message code="logout" text="Logout" /></a>
	</c:if>
	<section id="main">
		<h1 style="color:red" > <spring:message code="login" text="Log in" />: </h1>
		<br/>
		<br/>
		<form method="post" id="loginForm" action="<c:url value='j_spring_security_check'/>">
			<label ><spring:message code="j_username" text="username" />:</label><input type="text" name="j_username" id="j_username"/>
			<label ><spring:message code="j_password" text="password" />:</label><input type="password" name="j_password" id="j_password"/>
			<input type="submit" value=<spring:message code="login" text="connect" /> class="btn primary">
		</form>
	</section>
</body>