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
  <%@ include file="common.jsp" %>  
	<br>
  <div class="container">
   
	 <div class="page_content">
	  ${page}
	 </div>
		 
		 <div class="page_content_inner">
		    <div class="form">
		    <c:url var="userSave" value="/user/save"/>
			<form:form method="POST" modelAttribute="user"  class="form-horizontal" id="myForm" role="form" action="${userSave}">
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="firstname">First Name:</label>
			      <div class="col-sm-5">
			        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm" placeholder="Enter First Name"/>
			        
			      </div>
			      <div class="has-error">
			            <span class="fname"></span>
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
			    </div>
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="lastname">Last Name:</label>
			      <div class="col-sm-5">
			        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"  placeholder="Enter Last Name"/>
			      </div>
			      <div class="has-error">
			            <span class="lname"></span>
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
			    </div>
   
			    <div class="form-group">    
			     <label class="control-label col-sm-2" for="gender">Gender:</label>    
			      <div class="col-sm-3">
			        <div >
			             <label> <form:radiobutton path="sex" value="M" />Male </label>
                          <label><form:radiobutton path="sex" value="F" />Female</label>
			        </div>
			       <div class="has-error">
			            <span class="gender"></span>
                        <form:errors path="sex" class="help-inline"/>
                    </div>
			      </div>
			    </div>
    
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="email">Email Id:</label>
			      <div class="col-sm-5">
			        <form:input type="text" path="email" id="email" class="form-control input-sm" placeholder="Enter email" onchange="validateEmail()"/>
			      </div>
			      <div class="has-error">
			            <span class="img"></span>
			            <span class="email"></span>
                        <form:errors path="email" class="help-inline"/>
                    </div>
              </div>
			     <div class="form-group">
			      <label class="control-label col-sm-2" for="phone-number">Phone Number:</label>
			      <div class="col-sm-5">
			        <form:input type="text" path="phoneNo" id="phoneno" class="form-control input-sm" placeholder="Enter Phone Number"/>
			      </div>
			      
			      <div class="has-error">
			            <span class="phone"></span>
                        <form:errors path="phoneNo" class="help-inline"/>
                    </div>
			    </div>
			    <div class="form-group">
			      <label class="control-label col-sm-2" for="email">Address:</label>
			      <div class="col-sm-5">
			        <form:input type="text" path="address" id="address" class="form-control input-sm" placeholder="Enter Address"/>
			        
			      </div>
			      <div class="has-error">
			            <span class="address"></span>
                        <form:errors path="address" class="help-inline"/>
                    </div>
			    </div>
                
			    <div class="form-group">        
			      <div class="col-sm-offset-2 col-sm-10">
			        <button type="button" class="btn btn-default" onclick="submitForm()">Submit</button>
			         <button type="reset" class="btn btn-default">reset</button>
			         <c:url var="home" value="/user/home"/>
			         <a href="${home }" class="btn btn-default" id="cancel">cancel</a>
			      </div>
			    </div>
		  </form:form>
		</div>		
		</div>
	</div>
</div>
</body>
</html>
