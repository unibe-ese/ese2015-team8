<!-- if a user wants to access a page, he is not allowed to access, he gets redirected to this 
page. It tells him that he is not allowed to access the page he wanted to access. This happens
only when he enters a link to a forbidden site manually. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Access denied!</title></head>

<c:import url="template/header.jsp" />
<h1>Access denied!</h1>

<body>

<h1><a class="btn btn-primary btn-md" role="button" href="http://localhost:8080/Stutor/afterLogin" 
>Go back to main page</a></h1>

</body>