<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section id="main">

	<h1>Add Computer</h1>
	
	<c:if test='${ (add != null)  && (add =="Success") }' var="result">
		<h2 style="color: red">successful operation      <a href="dashboard" class="btn">dashboard</a></h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="Fail") }' var="result">
		<h2 style="color: red" >Fail to add computer        <a href="dashboard" class="btn">dashboard</a></h2>
	</c:if>
	
	<form:form action="save/addComputer" commandName="computer" method="POST" id="formulaire">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input  path="name" type="text" name="name" id="name"/>
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input  path="introduced" type="date" name="introducedDate" id="introducedDate"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<form:input path="discontinued" type="date" name="discontinuedDate" id="discontinuedDate"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<form:select path="company.id" name="company" id="company">
					<option value="0">--</option>
						<c:forEach items="${ companies }" var="company" varStatus="boucle">					
							<option value="${ company.getId() }">${ company.getName() }</option>
						</c:forEach>
			
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="dashboard.jsp" class="btn">Cancel</a>
		</div>
	</form:form>
	
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
	      //$("#addform").validate();
	    });
	   
	   
	</script>


</section>
<jsp:include page="include/footer.jsp" />