<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/utile.tld" prefix="utile" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section id="main">


	<c:if test="${ computers != null }" var="result">
		<h1 id="homeTitle">${ count }  <spring:message code="found" text="default text" /> </h1>
	</c:if>

	<c:if test="${ computers == null }" var="result">
		<%
			response.sendRedirect("dashboard");
		%>
	</c:if>
	
	<c:if test='${ (update != null)  && (update =="true") }' var="result">
		<h2 style="color: red"><spring:message code="successupdate" text="default text" /></h2> 
	</c:if>
	
	<c:if test='${ (update != null)  && (update=="false") }' var="result">
		<h2 style="color: red" ><spring:message code="failupdate" text="default text" /></h2>
	</c:if>
	<c:if test='${ (add != null)  && (add =="true") }' var="result">
		<h2 style="color: red"><spring:message code="successop" text="default text" /></h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="false") }' var="result">
		<h2 style="color: red" ><spring:message code="failadd" text="default text" /></h2>
	</c:if>

	<div id="actions">
		<form action="dashboard" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value=<spring:message code="filterbutton" text="default text" />
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer"><spring:message code="addsubtitle" text="default text" /></a>
	</div>
		<script>
			function callServlet(monForm,idC){	 
				console.log('idc '+idC);
				//document.getElementsByName('idComputer').value = idC+'';
				document.getElementById('idComputer').setAttribute('value', idC);
				monForm.submit();
			}
		</script>
	<form action="Remove" method="POST">
		<table class="computers zebra-striped" style='font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif; font-size: 12px; margin: 10px 0; width: 100%; text-align: center; border-collapse: collapse;'>
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th style="color:green; text-align: center;  padding: 8px; background: #b9c9fe;" > <a href="?page=${page}&order=0&search=${search}"><spring:message code="computername" text="default text" /></a></th>
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe; " ><a href="?page=${page}&order=1&search=${search}"><spring:message code="introducedText" text="default text" /></a></th>
					<!-- Table header for Discontinued Date -->
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="?page=${page}&order=2&search=${search}"><spring:message code="discontinuedText" text="default text" /></a></th>
					<!-- Table header for Company -->
					<th style="color:red; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="?page=${page}&order=3&search=${search}"><spring:message code="company" text="default text" /></a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ computers }" var="computer"  begin='0' end='${computers.size()}'>
					<tr>
						<td><a href='updateComputer?idUpdate= ${ computer.split(",")[0] }' onclick="">${ computer.split(",")[1] }</a></td>
						<td>${ computer.split(",")[2] }</td>
						<td>${ computer.split(",")[3] }</td>
						<td>${ computer.split(",")[5] }</td>
						<td><input type="button" name=${ computer.split(",")[0] }
							id=${ computer.split(",")[0] } value=<spring:message code="delete" text="default text" /> class="btn btn-ttc" style="color:red" onclick='callServlet(this.form,${ computer.split(",")[0] });' /></td>
					</tr>
				</c:forEach>

				<tr>
					<td><input type="hidden" id="idComputer" name="idComputer"
						VALUE="INITIAL" /></td>
				</tr> 
			</tbody>
		</table>
		<!--  begin="${page * 10}" end="${page + 9}	-->
		
		
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
<%-- 						<h1>Current Locale : ${pageContext.response.locale}</h1> --%>
<jsp:include page="include/footer.jsp" />
