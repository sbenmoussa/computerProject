<jsp:include page="include/header.jsp" />
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section id="main">

	<h1><spring:message code="addsubtitle" text="Add Computer" /></h1>
	
	<c:set var="pattern"><spring:message code="pattern.date.string"/></c:set>
	<form:form action="addComputer" commandName="computerdto" method="POST" id="formulaire">
	<form:errors path="*" cssClass="errorblock" element="div" />
		<fieldset>
			<div class="clearfix">
				<label for="name"><spring:message code="computername" text="name" />:</label>
				<div class="input">
					<form:input  path="name" type="text" name="name" id="name"/>
					<form:errors path="name" cssClass="error" />
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introducedDate"><spring:message code="introducedText" text="introduced" />:</label>
				<div class="input">
					<form:input  path="introduced" type="text" name="introduced" id="introduced"  placeholder="${pattern}"/>
					<form:errors path="introduced" cssClass="error" />
					<span class="help-inline"><spring:message code="pattern.date.string" text="YYYY-MM-DD" /></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="discontinuedText" text="discontinued" />:</label>
				<div class="input">				
					<form:input path="discontinued" type="text" name="discontinued" id="discontinued" placeholder="${pattern}"/>
					<form:errors path="discontinued" cssClass="error" />
					<span class="help-inline"><spring:message code="pattern.date.string" text="YYYY-MM-DD" /></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="company" text="company" />:</label>
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
			<input type="submit" value=<spring:message code="add" text="ok" /> class="btn primary">
			or <a href="dashboard " class="btn"><spring:message code="cancel" text="Cancel" /></a>
		</div>
	</form:form>
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js"></script>
	<script type="text/javascript" src="js/jquery.i18n.properties-min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			jQuery.validator.addMethod("regex", function(value, element, regexp) {
				if (regexp.constructor != RegExp)
					regexp = new RegExp(regexp);
				else if (regexp.global)
					regexp.lastIndex = 0;
				return this.optional(element) || regexp.test(value);
				},"Invalid format Date"
			);
			
			jQuery.validator.addMethod('greaterThan',function(value, element, param) {
				if ( (!(isNaN(Date.parse($(document.getElementById("introduced")).val())))) && (!(isNaN(Date.parse($(document.getElementById("discontinued")).val())))) ){
					console.log("les deux dates sont rentré donc comparés");
					return (Date.parse($(document.getElementById("introduced")).val()) <= Date.parse($(document.getElementById("discontinued")).val()));
				}
				else{
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
							regex: /^[0-9]{2}(\/)[0-9]{2}(\/)[0-9]{4}$/,
							//greaterThan: true
						},
						"discontinued": {
							regex: /^[0-9]{2}(\/)[0-9]{2}(\/)[0-9]{4}$/,
							//greaterThan: true
						}
					}
				});
			});
		});
	</script>
</section>
<jsp:include page="include/footer.jsp" />