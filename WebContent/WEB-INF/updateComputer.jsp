<jsp:include page="include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>

<section id="main">

	<c:if test="${ computer == null }" var="result">	
		<%
			response.sendRedirect("dashboard.jsp");
		%>
	</c:if>

	<h1>Update Computer</h1>

	
	
	<form action="UpdateComputer" method="POST" id="formulaire">
		<fieldset>
			<input type="hidden" name="idUpdate" id="idUpdate" value='${idUpdate }' />
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" id="name" value='${ computer.split(",")[1] }' required/>
					<span class="help-inline"></span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate"  id="introducedDate" value='${ computer.split(",")[2] }'/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" id="discontinuedDate" value='${ computer.split(",")[3] }'/>
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
								<c:when test='${ company.split(",")[0]  == computer.split(",")[4] }'>
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
			or <a href="Dashboard"  class="btn">Cancel</a>
		</div>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>	
	<script>

	$(document).ready(function(){ 
		boolean $changement = false;
		var $name = $('#name'), 
		$intro = $('#introducedDate'), 
		$disc = $('#discontinuedDate'), 
		$comp = $('#company'),
		$envoi = $('#envoi'), 
		$erreur =$('#erreur');
			
		$envoi.click(function(e){
			console.log("boutton update cliqué donc vérification");
	    	e.preventDefault(); // on annule la fonction par défaut du bouton d'envoi

	    	// puis on lance la vérification sur tous les champs :
	    	if($name.val().equals("")) { 
	    		$erreur.css('display', 'block'); // on affiche le message d'erreur
	    		$name.css({ // on rend le champ rouge
	            borderColor : 'red',
	            color : 'red'
	        	});
	    		$changement = false;
	    	}
	    	else{
	    		if($name.val() !=   computer.split(",")[1]  ){
	    			$changement = true;
	    		}
	    	}
	    
	    	if($intro.val().equals("")) { 
	    		$erreur.css('display', 'block'); // on affiche le message d'erreur
	    		$intro.css({ // on rend le champ rouge
	            	borderColor : 'red',
	            	color : 'red'
	       		 });
	    		$changement = false;
	    	}
	    	else{
	    		if($intro.val() != computer.split(",")[2]){
	    			$changement = true;
	    		}
	    	}
	    
	    	if($disc.val().equals("")) { 
	    		$erreur.css('display', 'block'); // on affiche le message d'erreur
	    		$disc.css({ // on rend le champ rouge
	            	borderColor : 'red',
	            	color : 'red'
	        	});
	    		$changement = false;
	   		 }
	    	else{
	    		if($disc.val() !=  computer.split(",")[3] ){
	    			$changement = true;
	    		}
	   		 }
	    
	    
	   		 if($comp.val().equals("")) { 
	    		$erreur.css('display', 'block'); // on affiche le message d'erreur
	    		$comp.css({ // on rend le champ rouge
	            	borderColor : 'red',
	            	color : 'red'
	       		 });
	    		$changement = false;
	    	}
	    	else{
	    		if($comp.val() != computer.split(",")[4] ){
	    			$changement = true;
	    		}
	    	}
	    
	    	if($changement){
	    		$('#formulaire').submit();
	    		console.log("Le formulaire est envoyé car changement dans les données");
	   		 }
		});
	});
	</script>
	
</section>
<!--     -->
		<!---->
<jsp:include page="include/footer.jsp" />