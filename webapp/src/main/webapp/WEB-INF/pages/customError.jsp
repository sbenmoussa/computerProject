<jsp:include page="include/header.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<section id="main">
<h3> <spring:message code="errorpage" text="default text" />  <br/> ( ${errorMessage} )</h3>
</section>
<jsp:include page="include/footer.jsp" />