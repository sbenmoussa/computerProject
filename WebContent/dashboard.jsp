<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>


<section id="main">

	<c:if test="${ computers != null }" var="result">
		<h1 id="homeTitle">${ computers.size() } Computers found</h1>
	</c:if>

	<c:if test="${ computers == null }" var="result">
		<%
			response.sendRedirect("Dashboard");
		%>
	</c:if>

	<div id="actions">
		<form action="Dashboard" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="AddComputer">Add Computer</a>
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
		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ computers }" var="computer" varStatus="boucle">
					<tr>
						<td><a href="UpdateComputer?idUpdate= ${ computer.getId() }" onclick="">${ computer.getName() }</a></td>
						<td>${ computer.getIntroduced() }</td>
						<td>${ computer.getDiscontinued() }</td>
						<td>${ computer.getCompany().getName() }</td>
						<td><input type="button" name=${ computer.getId() }
							id=${ computer.getId() } value="DELETE" class="btn btn-ttc"
							style="color: red"
							onclick="callServlet(this.form,${ computer.getId() });" /></td>
					</tr>
				</c:forEach>

				<tr>
					<td><input type="hidden" id="idComputer" name="idComputer"
						VALUE="INITIAL" /></td>
				</tr>
				<!--  	<ul id="pagination-clean">
			    <li class="previous-off">« Précédent</li>
			    <li class="active">1</li>
			    <li><a href="/page=2">2</a></li>
			    <li><a href="/?page=3">3</a></li>
			    <li><a href="/?page=4">4</a></li>
			    <li><a href="/?page=5">5</a></li>
			    <li><a href="/?page=6">6</a></li>
			    <li><a href="/?page=7">7</a></li>
			    <li class="next"><a href="/?page=2">Suivant »</a></li>
			</ul>  begin="${page - 9}" end="${page} -->
			</tbody>
		</table>
		</form>
</section>

<jsp:include page="include/footer.jsp" />
