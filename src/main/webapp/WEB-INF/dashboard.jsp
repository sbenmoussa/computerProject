<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/utile.tld" prefix="utile" %>


<section id="main">


	<c:if test="${ computers != null }" var="result">
		<h1 id="homeTitle">${ computers.size() } Computers found  ${ update }</h1>
	</c:if>

	<c:if test="${ computers == null }" var="result">
		<%
			response.sendRedirect("Dashboard");
		%>
	</c:if>
	
	<c:if test='${ (update != null)  && (update =="Success") }' var="result">
		<h2 style="color: red">successful update    <a href="Dashboard" class="btn">return to dashboard</a></h2> 
	</c:if>
	
	<c:if test='${ (update != null)  && (update=="Fail") }' var="result">
		<h2 style="color: red" >Fail to update computer <a href="Dashboard" class="btn">dashboard</a></h2>
	</c:if>
	<c:if test='${ (add != null)  && (add =="Success") }' var="result">
		<h2 style="color: red">successful operation      <a href="Dashboard" class="btn">dashboard</a></h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="Fail") }' var="result">
		<h2 style="color: red" >Fail to add computer        <a href="Dashboard" class="btn">dashboard</a></h2>
	</c:if>

	<div id="actions">
		<form action="Dashboard" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer">Add Computer</a>
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
					<th style="color:green; text-align: center;  padding: 8px; background: #b9c9fe;" > <a href="dashboard?page=${page}&order=0">Computer Name </a></th>
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe; " ><a href="dashboard?page=${page}&order=1">Introduced Date</a></th>
					<!-- Table header for Discontinued Date -->
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="dashboard?page=${page}&order=2">Discontinued Date</a></th>
					<!-- Table header for Company -->
					<th style="color:red; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="dashboard?page=${page}&order=3">Company</a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ computers }" var="computer"  begin='${page * 10}' end='${(page*10) + 9}'>
					<tr>
						<td><a href='UpdateComputer?idUpdate= ${ computer.split(",")[0] }' onclick="">${ computer.split(",")[1] }</a></td>
						<td>${ computer.split(",")[2] }</td>
						<td>${ computer.split(",")[3] }</td>
						<td>${ computer.split(",")[5] }</td>
						<td><input type="button" name=${ computer.split(",")[0] }
							id=${ computer.split(",")[0] } value="DELETE" class="btn btn-ttc"
							style="color: red"
							onclick='callServlet(this.form,${ computer.split(",")[0] });' /></td>
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
				<c:url var="searchUri" value="dashboard??s=${searchval}&page=##&order=${order}&search=${search}" />
				<utile:pagination maxLinks="10" currPage="${page}" totalPages="${computers.size()}" uri="${searchUri}" />
			</c:when>
			<c:otherwise>
				<c:url var="searchUri" value="dashboard??s=${searchval}&page=##&order=${order}" />
				<utile:pagination maxLinks="10" currPage="${page}" totalPages="${computers.size()}" uri="${searchUri}" />
			</c:otherwise>
		</c:choose>

	</form>
		
</section>

<jsp:include page="include/footer.jsp" />
