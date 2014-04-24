<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>


<section id="main">

	<c:if test='${ (update != null)  && (update =="Success") }' var="result">
		<h2 style="color: red">successful update</h2>
	</c:if>
	
	<c:if test='${ (update != null)  && (update =="Fail") }' var="result">
		<h2 style="color: red" >Fail to update computer</h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="Success") }' var="result">
		<h2 style="color: red">successful operation</h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="Fail") }' var="result">
		<h2 style="color: red" >Fail to add computer</h2>
	</c:if>

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
		<table class="computers zebra-striped" style='font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif; font-size: 12px; margin: 10px 0; width: 100%; text-align: center; border-collapse: collapse;'>
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th style="color:green; text-align: center;  padding: 8px; background: #b9c9fe;" > <a href="Dashboard?page=${page}&order=0">Computer Name </a></th>
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe; " ><a href="Dashboard?page=${page}&order=1">Introduced Date</a></th>
					<!-- Table header for Discontinued Date -->
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="Dashboard?page=${page}&order=2">Discontinued Date</a></th>
					<!-- Table header for Company -->
					<th style="color:green; text-align: center; padding: 8px; background: #b9c9fe;" ><a href="Dashboard?page=${page}&order=3">Company</a></th>
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
			<ul id="pagination-clean" >
				<c:choose>
					<c:when test='${page == 0}'>
						<li style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;" class="previous-off">« Précédent</li>
					</c:when>								
    				<c:otherwise>
    					<li class="previous-on" style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;"><a href='Dashboard?page=${page -1}&order=${order}'>« Précédent</a></li>
    				</c:otherwise>
				</c:choose>
			    
			    <c:forEach  var="i" begin='${ (page - 10 < 0) ? 0 : page - 10}' end='${ page +10}' step="1">
			   		<c:choose>
						<c:when test='${page == i}'>
							<li class="active" style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;">${i}</li>
						</c:when>								
    					<c:otherwise>
    						<li style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;" ><a href="Dashboard?page=${i}&order=${order}">${i}</a></li>
    					</c:otherwise>
					</c:choose>			    
			    </c:forEach>
			    
			    <c:choose>
					<c:when test='${page == computers.size() / 10}'>
						<li style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;" class="previous-off"> Suivant »</li>
					</c:when>								
    				<c:otherwise>
    					<li style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px; "> ... </li>
    					<li style="display: inline; list-style: none; /* pour enlever les puces sur IE7 */ margin: 5px;" class="next-on"><a style="color=red" href='Dashboard?page=${page +1}&order=${order}'>Suivant »</a></li>
    				</c:otherwise>
				</c:choose>
   	    
			</ul>  
		</form>
</section>

<jsp:include page="include/footer.jsp" />
