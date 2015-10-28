<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:import url="template/header.jsp" />
<h1> <c:out value="${notification.titel}"/> </h1>

<body>
	<p><c:out value="${notification.message}" /> </p>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>