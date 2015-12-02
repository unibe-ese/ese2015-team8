<!-- That's the main page after the login for the tutor. He can  see his profile
or his notifications and he can add lectures and his scheduals. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Main Page</title>
</head>

<c:import url="template/header.jsp" />

<h1>Welcome</h1>



<body>
<div class="panel panel-default">
 <div class="panel-heading">${welcomeMsg}</div>
 <div class="panel-body">See Notifications, Profile on the top right corner<br>
Some welcome text Some welcome text Some welcome text Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
 </div>
</div>
</body>
</html>