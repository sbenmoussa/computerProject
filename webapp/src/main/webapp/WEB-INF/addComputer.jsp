<jsp:include page="include/header.jsp" />
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style type="text/css">
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

	<h1><spring:message code="addsubtitle" text="default text" /></h1>
	
	<c:if test='${ (add != null) && (add =="Success") }' var="result">
		<h2 style="color: red"><spring:message code="successop" text="default text" /> <a href="dashboard" class="btn">dashboard</a></h2>
	</c:if>
	
	<c:if test='${ (add != null)  && (add =="Fail") }' var="result">
		<h2 style="color: red" ><spring:message code="failadd" text="default text" /><a href="dashboard" class="btn">dashboard</a></h2>
	</c:if>
	
	<form:form action="addComputer" commandName="computerdto" method="POST" id="formulaire">
	<form:errors path="*" cssClass="errorblock" element="div" />
		<fieldset>
			<div class="clearfix">
				<label for="name"><spring:message code="computername" text="default text" />:</label>
				<div class="input">
					<form:input  path="name" type="text" name="name" id="name"/>
					<form:errors path="name" cssClass="error" />
					<!--  <span class="help-inline">Required</span>-->
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introducedDate"><spring:message code="introducedText" text="default text" />:</label>
				<div class="input">
					<form:input  path="introduced" type="date" name="introduced" id="introduced"  placeholder="YYYY-MM-DD"/>
					<form:errors path="introduced" cssClass="error" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="discontinuedText" text="default text" />:</label>
				<div class="input">
					<form:input path="discontinued" type="date" name="discontinued" id="discontinued" placeholder="YYYY-MM-DD"/>
					<form:errors path="discontinued" cssClass="error" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="company" text="default text" />:</label>
				<div class="input">
					<form:select path="company.id" name="company" id="company">
					<option value="0">--</option>
						<c:forEach items="${ companies }" var="company" varStatus="boucle">					
							<option value="${ company.getId() }">${ company.getName() }</option>
						</c:forEach>
			
					</form:select>
					<form:errors path="company.id" cssClass="error" />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value=<spring:message code="add" text="default text" /> class="btn primary">
			or <a href="dashboard " class="btn"><spring:message code="cancel" text="default text" /></a>
		</div>
	</form:form>
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			jQuery.validator.addMethod("regex", function(value, element, regexp) {
				if (regexp.constructor != RegExp)
					regexp = new RegExp(regexp);
				else if (regexp.global)
					regexp.lastIndex = 0;
				return this.optional(element) || regexp.test(value);
				},"erreur expression reguliere"
			);
			
			jQuery.validator.addMethod('greaterThan',function(value, element, param) {
				console.log("verification de validité de date discontinued");
				console.log("discontinued entré = "+$(document.getElementById("discontinued")).val()+" ce qui donne apres parse: "+Date.parse($(document.getElementById("discontinued")).val()));

				if ( (!(isNaN(Date.parse($(document.getElementById("introduced")).val())))) && (!(isNaN(Date.parse($(document.getElementById("discontinued")).val())))) ){
					console.log("les deux dates sont rentré donc comparés");
					return (Date.parse($(document.getElementById("introduced")).val()) <= Date.parse($(document.getElementById("discontinued")).val()));
				}
				else{
					//Ici le cas ou un des champs date est null ou bien qu'elle n'a pas encore été rempli CEci est permis
					console.log("une seule date entrée donc pas de comparaison");
					return true;
				}
				},jQuery.validator.format('Discontinued date must be after introduced date')
			);
	
			jQuery(document).ready(function() {
				jQuery("#formulaire").validate({
					rules: {
						"name":{
							"required": true
						},
						"introduced": {
							"date": true,
							regex: /^[0-9]{4}(\-)[0-9]{2}(\-)[0-9]{2}$/,
							greaterThan: true
						},
						"discontinued": {
							"date": true,
							regex: /^[0-9]{4}(\-)[0-9]{2}(\-)[0-9]{2}$/,
							greaterThan: true
						}
					}
				});
			});
		});
	</script>
</section>
<jsp:include page="include/footer.jsp" />