<!--  
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
			-->