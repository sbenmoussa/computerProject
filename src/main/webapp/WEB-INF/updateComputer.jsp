<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<section id="main">



	<c:if test="${ computer == null }" var="result">
		<%
			//response.sendRedirect("dashboard.jsp");
		%>
	</c:if>

	<h1>Update Computer</h1>

	<c:if test='${ (update != null)  && (update =="Success") }' var="result">
		<h2 style="color: red">successful update    <a href="dashboard" class="btn">return to dashboard</a></h2> 
	</c:if>
	
	<c:if test='${ (update != null)  && (update=="Fail") }' var="result">
		<h2 style="color: red" >Fail to update computer <a href="dashboard" class="btn">dashboard</a></h2>
	</c:if>

	<c:if test="${ computer != null }" var="result">
	<form:form action="updateComputer"  commandName="computer"  method="POST" id="formulaire">
	<form:errors path="*" cssClass="errorblock" element="div" />
		<fieldset>
			<form:hidden path="id" name="id" id="id" value='${id}' />
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input  path="name" type="text" name="name" id="name"/>
					<form:errors path="name" cssClass="error" />
					<span class="help-inline"></span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input path="introduced" type="date" name="introducedDate" id="introducedDate" value='${comp.split(",")[2]}'
					<form:errors path="introduced" cssClass="error" />
						   /> <span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<form:input path="discontinued" type="date" name="discontinuedDate" id="discontinuedDate" value='${comp.split(",")[3]}'
					<form:errors path="discontinued" cssClass="error" />
						   /> <span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<form:select path="company.id" name="company" id="company">
						<option value="0">--</option>
						<c:forEach items="${ companies }" var="company" varStatus="boucle">
							<c:choose>
								<c:when
									test='${ company.split(",")[0]  == computer.getCompany().getId() }'>
									<option selected value='${ company.split(",")[0] }'>${ company.split(",")[1] }</option>
								</c:when>

								<c:otherwise>
									<option value='${ company.split(",")[0] }'>${ company.split(",")[1] }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					<form:errors path="company.id" cssClass="error" />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="UPDATE" id="envoi" class="btn primary">
			or <a href="dashboard" class="btn">Cancel</a>
		</div>
	</form:form>
	</c:if>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	
	<script>
	   $(document).ready(function(){
		   jQuery.validator.addMethod(
				   	  "regex",
				  	   function(value, element, regexp) {
				   	       if (regexp.constructor != RegExp)
				   	          regexp = new RegExp(regexp);
				   	       else if (regexp.global)
				   	          regexp.lastIndex = 0;
				   	          return this.optional(element) || regexp.test(value);
				   	   },"erreur expression reguliere"
				   	);
		   
		   jQuery(document).ready(function() {
		   	   jQuery("#formulaire").validate({
		   	      rules: {
		   	         "name":{
		   	            "required": true
		    	        },
		   	         "introducedDate": {
		   	        	"required": true,
		   	            "date": true,
		   	         	 regex: /^[0-9]{4}(\-)[0-9]{2}(\-)[0-9]{2}$/
		   	         },
		   	         "discontinuedDate": {
		   	            "required": true,
		   	         	"date": true,
		   	           	regex: /^[0-9]{4}(\-)[0-9]{2}(\-)[0-9]{2}$/
		   	         }
		   	      }
		   	  });
		   	}); 
	    });	   
	</script>

</section>
<jsp:include page="include/footer.jsp" />