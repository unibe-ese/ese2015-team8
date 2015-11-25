<!-- This is the page where you can login if you've already signed up. You identify
yourself over the username and the password. The password is encrypted using Spring
Security. If you don't have an account yet, you can use the link below to sign up. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />


<html>
<head>
	<title>Login</title>
</head>

<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container-fluid">
     <div class="navbar-header">
      <a class="navbar-brand" href="http://localhost:8080/Stutor/afterLogin">STutor</a>
    </div>
 
    <div class="collapse navbar-collapse">
      <form class="navbar-form navbar-left" method="get" action="http://localhost:8080/Stutor/basicSearch" role="search">
        <div class="form-group">
          <input type="search" class="form-control" placeholder="Search..." name="q" >
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="http://localhost:8080/Stutor/logout">Log Out</a></li>
        <li><a href="http://localhost:8080/Stutor/options">Edit Profile</a></li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="jumbotron home home-fullscreen" id="home">
		<div class="mask"></div>
		<div class="mask"></div>
		<div class="container">
			<div class="header-info">
				<font color=ececed><h1>Welcome to STutor!</h1></font>
				<p>STutor helps you to find private tutors and to be sure that tutor is actually good at the subject!<br>
				Our client reviews mean you can select private tutors who will lift your grade, raise confidence<br>
				 and help you to discover the joy of learning through their private tuition journey.
				</p>
				<a href="#" class="btn btn-primary btn-lg">Get Started</a>
			</div>
		</div>
	</div>
	
	
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    	<font color=BE1E1E>
        	Your login attempt was not successful due to 
        	<c:choose>
        		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">wrong Password</c:when>
        		<c:otherwise>wrong username or no input</c:otherwise>
			</c:choose>
    	</font>
    	<br><br>
    </c:if>
    <c:url var="login" value="/login" />
	<form class="login-form" action="${login}" method="post" >
		<fieldset >
			<legend>Login Here</legend>
	        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    	      <font color=BE1E1E>
        	    Your login attempt was not successful due to 
        	     <c:choose>
        		  <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">wrong Password</c:when>
        		  <c:otherwise>wrong username or no input</c:otherwise>
			     </c:choose>
    	      </font>
    	     
             </c:if>
			<p>
			<label for="username">Username:</label>
			<input id="username" name="username" size="20" maxlength="50" type="text"/>
			</p>
			
			<p>
			<label for="password">Password:</label>
			<input id="password" name="password" size="20" maxlength="50" type="password"/>
			</p>
						
			<p><input type="submit" class="submitButton" value="Login"/></p>
			<a href="/Stutor/newAccount"> If you don't have an account, click here to sign up!</a>
		</fieldset>
	</form>
	<p class="message">${message}</p>
</body>
</html>





<%-- 	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if> --%>