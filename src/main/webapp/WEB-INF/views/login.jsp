<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage User</title>
  <%@ include file="header.jsp" %>
</head>
<body>
<div class="main">
<h1> Manage User</h1><br>
  <div class="container">
        <font color="red">
		   ${SPRING_SECURITY_LAST_EXCEPTION.message}
        </font>
	 <div class="page_content">
	  Login
	 </div>
		 
		 <div class="page_content_inner">
		    <div class="form">
		    <c:url var="login" value="/login"/>
			<form method="POST"  class="form-horizontal" role="form" action="${login}">
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="username">username:</label>
			      <div class="col-sm-5">
			        <input type="text"  id="username" name="username" class="form-control input-sm" placeholder="Enter Username"/>
			      </div>
			      
			    </div>
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="password">password:</label>
			      <div class="col-sm-5">
			        <input type="password"  id="password" name="password" class="form-control input-sm"  placeholder="Enter Password"/>
			      </div>
			      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    </div>
   
                
			      <div class="form-group">        
			      <div class="col-sm-offset-2 col-sm-10">
			        <button type="submit" class="btn btn-default">Submit</button>
			         <button type="reset" class="btn btn-default">reset</button>
			       </div>
			    </div>
		  </form>
		</div>		
		</div>
	</div>
</div>
</body>
</html>
