<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>

<section id="main">



	<c:if test="${ computer == null }" var="result">
		<%
			//response.sendRedirect("dashboard.jsp");
		%>
	</c:if>

	<h1>Update Computer</h1>

	<c:if test='${ (update != null)  && (update =="Success") }' var="result">
		<h2 style="color: red">successful update    <a href="Dashboard" class="btn">return to dashboard</a></h2> 
	</c:if>
	
	<c:if test='${ (update != null)  && (update=="Fail") }' var="result">
		<h2 style="color: red" >Fail to update computer <a href="Dashboard" class="btn">dashboard</a></h2>
	</c:if>

	<c:if test="${ computer != null }" var="result">
	<form action="UpdateComputer" method="POST" id="formulaire">
		<fieldset>
			<input type="hidden" name="idUpdate" id="idUpdate"
				value='${idUpdate }' />
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="name"
						value='${ computer.split(",")[1] }' class="required" required /> <span
						class="help-inline"></span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" id="introducedDate"
						value='${ computer.split(",")[2] }' class="required date"  required/> <span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedDate"
						value='${ computer.split(",")[3] }' class="required date"  required/> <span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company" id="company">
						<option value="0">--</option>
						<c:forEach items="${ companies }" var="company" varStatus="boucle">
							<c:choose>
								<c:when
									test='${ company.split(",")[0]  == computer.split(",")[4] }'>
									<option selected value='${ company.split(",")[0] }'>${ company.split(",")[1] }</option>
								</c:when>

								<c:otherwise>
									<option value='${ company.split(",")[0] }'>${ company.split(",")[1] }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="UPDATE" id="envoi" class="btn primary">
			or <a href="Dashboard" class="btn">Cancel</a>
		</div>
	</form>
	</c:if>

	<script src="jquery.js"></script>
	<script src="jquery.validate.js"></script>
	<script>
		$(document).ready(function() {
			$("#formulaire").validate();
		 });
	</script>

</section>
<jsp:include page="include/footer.jsp" />