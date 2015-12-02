<!-- This page shows your own profile. Shown parameters are: ID, first name, last name,
username email and if you're a Tutor: gender, given lectures and free time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Comments</title></head>
<c:import url="template/header.jsp" />

<style>
#showCommentsTableIndex th{
	text-align:left;
	font-weight: bold;
}

#showCommentsTableContentRating{
	text-align:center;
	font-weight: normal;
}

#showCommentsTableContentText{
	text-align:left;
	font-weight: normal;
}
</style>

<body>
	<h1>Comments:</h1>
	
	<a href="http://localhost:8080/Stutor/showSortedCommentsUp?tutorId=${tutorId}">Sort by lowest</a> -
	<a href="http://localhost:8080/Stutor/showSortedCommentsDown?tutorId=${tutorId}">Sort by higest</a>
	<br>
	<br>
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
		<a href="http://localhost:8080/Stutor/hiddenProfile?userId=${tutorId}">Return</a>
	</div>
</body>
</html>