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
	<form class="login-form" action="j_spring_security_check" method="post" >
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
			<label for="j_username">Username:</label>
			<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
			</p>
			
			<p>
			<label for="j_password">Password:</label>
			<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
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