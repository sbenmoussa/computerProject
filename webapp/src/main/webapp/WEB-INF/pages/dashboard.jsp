<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/utile.tld" prefix="utile" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<section id="main">


	<c:if test="${ computers != null }" var="result">
		<h1 id="homeTitle">${ count }  <spring:message code="found" text="found" /> </h1>
	</c:if>

	<c:if test="${ computers == null }" var="result">
		<%
			response.sendRedirect("dashboard");
		%>
	</c:if>
	
	<c:if test='${ (update != null)  && (update =="true") }' var="result">
		<h2 style="color: red"><spring:message code="successupdate" text="success" /></h2> 
	</c:if>
	
	<c:if test='${ (update != null)  && (update=="false") }' var="result">
		<h2 style="color: red" ><spring:message code="failupdate" text="fail" /></h2>
	</c:if>
	<c:if test='${ (add != null)  && (add =="true") }' var="result">
		<h2 style="color: red"><spring:message code="successop" text="success" /></h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="false") }' var="result">
		<h2 style="color: red" ><spring:message code="failadd" text="fail" /></h2>
	</c:if>

	<div id="actions">
		<form action="dashboard" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value=<spring:message code="filterbutton" text="filter" />
				class="btn primary">
		</form>
		<security:authorize ifAnyGranted="ROLE_ADMIN">
			<a class="btn success" id="add" href="addComputer"><spring:message code="addsubtitle" text="add Computer" /></a>
		</security:authorize>
	</div>
		<script>
			function callServlet(monForm,idC){	 
				console.log('idc '+idC);
				document.getElementById('idComputer').setAttribute('value', idC);
				monForm.submit();
			}
		</script>
	<form action="Remove" method="POST">
		<table class="computers zebra-striped" style='font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif; font-size: 12px; margin: 10px 0; width: 100%; text-align: center; border-collapse: collapse;'>
			<thead>
				<tr>
					<!-- Table header for Computer Name -->
					<th style="color:green; text-align: center;  padding: 8px; background: #b9c9fe;" > <a href="?page=${page}&order=name&search=${search}"><spring:message code="computername" text="name" /></a></th>
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe; " ><a href="?page=${page}&order=introduced&search=${search}"><spring:message code="introducedText" text="introduced" /></a></th>
					<!-- Table header for Discontinued Date -->
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="?page=${page}&order=discontinued&search=${search}"><spring:message code="discontinuedText" text="discontinued" /></a></th>
					<!-- Table header for Company -->
					<th style="color:red; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="?page=${page}&order=company_id&search=${search}"><spring:message code="company" text="company" /></a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ computers }" var="computer"  begin='0' end='${computers.size()}'>
					<tr>
						<td><a href='updateComputer?idUpdate= ${ computer.id }' >${ computer.name }</a></td>
						<td>${ computer.introduced }</td>
						<td>${ computer.discontinued}</td>
						<td>${ computer.company.name }</td>
						<security:authorize ifAnyGranted="ROLE_ADMIN">
							<td><input type="button" name=${ computer.id }
								id=${ computer.id } value=<spring:message code="delete" text="delete" /> class="btn btn-ttc" style="color:red" onclick='callServlet(this.form,${ computer.id });' />
							</td>
						</security:authorize>			
					</tr>
				</c:forEach>

				<tr>
					<td><input type="hidden" id="idComputer" name="idComputer"
						VALUE="INITIAL" /></td>
				</tr> 
			</tbody>
		</table>
		
		<c:choose>
			<c:when test='${search != null}'>
				<c:url var="searchUri" value="dashboard?page=##&order=${order}&search=${search}" />
				<utile:pagination maxLinks="10" currPage="${page}" totalPages="${count}" uri="${searchUri}" lang="${pageContext.response.locale}"/>
			</c:when>
			<c:otherwise>
				<c:url var="searchUri" value="dashboard?page=##&order=${order}" />
				<utile:pagination maxLinks="10" currPage="${page}" totalPages="${count}" uri="${searchUri}" lang="${pageContext.response.locale}"/>
			</c:otherwise>
		</c:choose>

	</form>
		
</section>
<jsp:include page="include/footer.jsp" />
