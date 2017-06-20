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
<title>Not Found</title>
       <link href="<c:url value='/resources/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
       <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css">
       <script src="<c:url value='/resources/js/jquery.min.js'/>" type="text/javascript"></script>
       <script src="<c:url value='/resources/js/bootstrap.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div class="main">
  <div class="container">
      <div class="page_content">
	  
	 </div>
		 
		 <div class="page_content_inner">
		    <div style="text-align: center;" class="form">
		    <h1 style="text-align: center;">${exception.message}</h1>
            <h3>${errorMessage}</h3>
           <c:url var="home" value="/user/home"/>
			                      <a href="${home }" >Go Home</a>
		</div>		
		</div>
	</div>
</div>
</body>
</html>
