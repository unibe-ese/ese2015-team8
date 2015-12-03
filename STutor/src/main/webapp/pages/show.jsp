<!-- After you've signed up correctly, you get redirected to this page to 
inform you about the correct sign up. You can then go to the main page by
clicking on the link and get automatically logged in. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Complete</title></head>

<c:import url="template/header.jsp" />

<h1><c:out value="${text}"></c:out></h1>

<div>
	<a class="btn btn-primary btn-md" href="http://localhost:8080/Stutor/afterLogin" role="button">Back to main page</a>
</div>