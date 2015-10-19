<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />
<h1>--Main Page--</h1>

<html>
<head>
	<!-- <link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/> -->
	<title>Login</title>
</head>

<body>
	<form class="login-form" action="j_spring_security_check" method="post" >
		<fieldset>
			<legend>Login Here</legend>
			
			<p>
			<label for="j_username">Username</label>:
			<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/>
			</p>
			
			<p>
			<label for="j_password">Password</label>:
			<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
			</p>
			
			<p><input type="submit" value="Login"/></p>
		</fieldset>
	</form>
	<p class="message">${message}</p>
</body>
</html>





<a href="/Skeleton/newAccount"> If you don't have an account, click here to sign up!</a>

<%-- 	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if> --%>