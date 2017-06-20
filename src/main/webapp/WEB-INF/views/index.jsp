<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="ec" uri="/WEB-INF/tld/function.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${page}</title>
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
		 <c:url var="addUser" value="/user/addUser"/>
		    <a href="${addUser}"><span class="glyphicon glyphicon-plus"> Add User</span></a> <br><br><br>
		              <c:choose>
			             <c:when test="${empty msg}">
			             </c:when>
			             <c:otherwise>
			                 <h3 style="color: green">${msg }</h3>
			             </c:otherwise>
			           </c:choose>
		    
					  <div class="content">
							<table id="t01">
							  <tr>
							    <th>S.N.</th>
							    <th>First Name</th>
							    <th>Last Name</th>
							    <th>Sex</th>
							    <th>Email</th>
							    <th>Phone No</th>
							    <th>Address</th>
							    <th>Action</th>
							  </tr>
							  <c:set var="count" value="0" scope="page" />
							  <c:forEach items="${userlist}" var="users">
							  <c:set var="count" value="${count + 1}" scope="page"/>
							  <tr>
							    <td>${count}</td>
							    <td>${users.firstName}</td>
							    <td>${users.lastName}</td>
							    <td>${users.sex}</td>
							    <td>${users.email}</td>
							    <td>${users.phoneNo}</td>
							    <td>${users.address}</td>
							   
							   <c:set var="encryptedId" value="${ec:encrypt(users.id)}"/>
							   
							    <c:url var="editUser" value="/user/editUser/">
							      <c:param name="id" value="${encryptedId}"></c:param>
							    </c:url>
							    
							    
							    <td><a href="${editUser }" ><span class="glyphicon glyphicon-edit"></span></a>
							    &nbsp;&nbsp;<a id="deleteuser${users.id}" href="#" data-value="${users.id}" onclick="deleteUser(${users.id})"><span class="glyphicon glyphicon-remove"></span></a>
							    </td>
							  </tr>
							  </c:forEach>
							</table>
							   <input type="hidden"  id="url" data-value="${deleteUser }"/>
							    <c:url var="deleteUser" value="/user/deleteUser/"/>
					</div>
					
					   <c:choose>
						<c:when test="${user.id!=null && user.id>0}">
						   <c:choose>
				             <c:when test="${empty msgs}">
				             </c:when>
				             <c:otherwise>
				                 <p style="color: red">${msgs }</p>
				             </c:otherwise>
				           </c:choose>
							<div class="form">
		                    <c:url var="userSave" value="/user/save"/>
							<form:form method="POST" modelAttribute="user"  class="form-horizontal" role="form" action="${userSave}">
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="firstname">First Name:</label>
							      <div class="col-sm-5">
							        <form:input type="text" path="firstName" id="firstName" class="form-control input-sm" placeholder="Enter First Name"/>
							      </div>
							      <div class="has-error">
				                        <form:errors path="firstName" class="help-inline"/>
				                    </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="lastname">Last Name:</label>
							      <div class="col-sm-5">
							        <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"  placeholder="Enter Last Name"/>
							      </div>
							      <div class="has-error">
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
				                        <form:errors path="sex" class="help-inline"/>
				                    </div>
							      </div>
							    </div>
				    
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="email">Email Id:</label>
							      <div class="col-sm-5">
							        <form:input type="text" path="email" id="email" class="form-control input-sm" placeholder="Enter email"/>
							      </div>
							      <div class="has-error">
				                        <form:errors path="email" class="help-inline"/>
				                    </div>
				              </div>
							     <div class="form-group">
							      <label class="control-label col-sm-2" for="phone-number">Phone Number:</label>
							      <div class="col-sm-5">
							        <form:input type="text" path="phoneNo" id="phoneno" class="form-control input-sm" placeholder="Enter Phone Number"/>
							      </div>
							      
							      <div class="has-error">
				                        <form:errors path="phoneNo" class="help-inline"/>
				                    </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="email">Address:</label>
							      <div class="col-sm-5">
							        <form:input type="text" path="address" id="address" class="form-control input-sm" placeholder="Enter Address"/>
							        
							      </div>
							      <div class="has-error">
				                        <form:errors path="address" class="help-inline"/>
				                    </div>
							    </div>
				                <form:input type="hidden" path="id"/>
							    <div class="form-group">        
							      <div class="col-sm-offset-2 col-sm-10">
							        <button type="submit" class="btn btn-default">Submit</button>
							        <c:url var="home" value="/user/home"/>
			                      <a href="${home }" class="btn btn-default">cancel</a>
							      </div>
							    </div>
						  </form:form>
		             </div>	
				</c:when>
			<c:otherwise>
						
			</c:otherwise>
		 </c:choose>
		</div>
	</div>
</div>
</body>
</html>
