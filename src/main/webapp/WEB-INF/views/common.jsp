<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${pageContext.request.userPrincipal.name !=null }">

          <c:url value="/logout" var="logoutUrl"/>
                 <form action="${logoutUrl}" method="post" id="logoutForm">
								            <input type="hidden" 
								                    name="${_csrf.parameterName}"
								                    value="${_csrf.token}" />
				</form>   
								<script>
									    function formSubmit() {
									    	   document.getElementById("logoutForm").submit();
									    }
									</script> 
	<div style="text-align: center;">
           <h3>  <a href="#">${pageContext.request.userPrincipal.name}</a>
               <a href="javascript:formSubmit()">Logout</a>
             </h3>
    </div>

</c:if> 