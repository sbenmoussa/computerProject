<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import ="com.projet.computerdata.model.Computer" %>

<section id="main">

	<c:if test="${ computer == null }" var="result">	
		<%
			response.sendRedirect("dashboard.jsp");
		%>
	</c:if>

	<h1>Update Computer</h1>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js">
	$(document).ready(function(){ 
		boolean $changement = false;
		var $name = $('#name'), 
		$intro = $('#introducedDate'), 
		$disc = $('#discontinuedDate'), 
		$comp = $('#company'),
		$envoi = $('#envoi'), 
		$erreur =$('#erreur'), $champ = $('.champ'); });
	
	$envoi.click(function(e){
	    e.preventDefault(); // on annule la fonction par défaut du bouton d'envoi

	    // puis on lance la vérification sur tous les champs :
	    if($name.val() == "") { 
	    	$erreur.css('display', 'block'); // on affiche le message d'erreur
	    	$name.css({ // on rend le champ rouge
	            borderColor : 'red',
	            color : 'red'
	        });
	    	$changement = false;
	    }
	    else{
	    	if($name.val() !=  computer.getName() ){
	    		$changement = true;
	    	}
	    }
	    
	    if($intro.val() == "") { 
	    	$erreur.css('display', 'block'); // on affiche le message d'erreur
	    	$intro.css({ // on rend le champ rouge
	            borderColor : 'red',
	            color : 'red'
	        });
	    	$changement = false;
	    }
	    else{
	    	if($intro.val() == computer.getIntroduced()){
	    		$changement = true;
	    	}
	    }
	    
	    if($disc.val() == "") { 
	    	$erreur.css('display', 'block'); // on affiche le message d'erreur
	    	$disc.css({ // on rend le champ rouge
	            borderColor : 'red',
	            color : 'red'
	        });
	    	$changement = false;
	    }
	    else{
	    	if($disc.val() ==  computer.getDiscontinued() ){
	    		$changement = true;
	    	}
	    }
	    
	    
	    if($comp.val() == "") { 
	    	$erreur.css('display', 'block'); // on affiche le message d'erreur
	    	$comp.css({ // on rend le champ rouge
	            borderColor : 'red',
	            color : 'red'
	        });
	    	$changement = false;
	    }
	    else{
	    	if($comp.val() == computer.getCompany().getId() ){
	    		$changement = true;
	    	}
	    }
	    
	    if($changement){
	    	$('#formulaire').submit();
	    }

	});
	</script>
	
	<form action="UpdateComputer" method="POST" id="formulaire">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="name" value="${ computer.getName() }" required/>
					<span class="help-inline"></span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate"  id="introducedDate" value="${ computer.getIntroduced() }"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedDate" value="${ computer.getDiscontinued() }"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company" id="company">
					<option value="0">--</option>
						<c:forEach items="${ companies }" var="company" varStatus="boucle">
							<c:choose>
								<c:when test="${ company.getId() } ==  computer.getCompany().getId() }">
									<option selected value="${ company.getId() }">${ company.getName() }</option>
								</c:when>
									
    							<c:otherwise>
    								<option value="${ company.getId() }">${ company.getName() }</option>
    							</c:otherwise>
							</c:choose>
						</c:forEach>

					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="UPDATE" id="envoi" class="btn primary">
			or <a href="dashboard.jsp"  	class="btn">Cancel</a>
		</div>
	</form>
</section>
<!--     -->
		<!---->
<jsp:include page="include/footer.jsp" />