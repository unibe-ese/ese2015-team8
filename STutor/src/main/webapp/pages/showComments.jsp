<!-- This page shows your own profile. Shown parameters are: ID, first name, last name,
username email and if you're a Tutor: gender, given lectures and free time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
	<title>Comments</title>
	<link rel="stylesheet" type="text/css" href="css/showComment.css">
</head>

<c:import url="template/header.jsp" />

<body>
	<h1>Comments:</h1>
	
	<a href="http://localhost:8080/Stutor/showSortedCommentsUp?tutorId=${tutorId}">Sort by lowest</a> -
	<a href="http://localhost:8080/Stutor/showSortedCommentsDown?tutorId=${tutorId}">Sort by higest</a>
	
	<br><br>
	
	<table class="table table-hover">
		<tr id="showCommentsTableIndex">
			<th>Rating</th>
			<th>Comment</th>
		</tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<th id="showCommentsTableContentRating"><c:out value="${comment.rating}" /></th>
				<th id="showCommentsTableContentText"><c:out value="${comment.comment}" /></th>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	
	<div>
		<a class="btn btn-primary btn-md" role="button" href="http://localhost:8080/Stutor/hiddenProfile?userId=${tutorId}">Return</a>
	</div>
</body>

<c:import url="template/footer.jsp" />