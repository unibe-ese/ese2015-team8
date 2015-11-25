<!-- This page shows your own profile. Shown parameters are: ID, first name, last name,
username email and if you're a Tutor: gender, given lectures and free time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Profile</title></head>
<c:import url="template/header.jsp" />

<body>
<h1>Student Profile</h1>

	<table>
		<tr><td>First Name: </td><td><c:out value="${student.firstName}" /></td></tr>
		<tr><td>Last Name: </td><td><c:out value="${student.lastName}" /></td></tr>
		<tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
		<tr><td>Email: </td><td><c:out value="${student.email}" /></td></tr>
		<c:choose>
			<c:when test="${student.isTutor}">
				<tr><td>Gender: </td><td><c:out value="${student.gender}" /></td></tr>
				<tr><td>Wage: </td><td><c:out value="${student.wage} Fr./h" /></td></tr>
			</c:when>
		</c:choose>
	</table>
	
	<c:choose>
		<c:when test="${student.isTutor}">
			<div id="lectureList">
				<h1>Gives the following Lecture:</h1>
				<c:forEach items="${lectures}" var="lecture">
					<div id="table">
						<label><c:out value="${lecture}" /></label>
						<a href="http://localhost:8080/Stutor/editLecture?id=${lecture.id}">Edit</a>
						<a href="http://localhost:8080/Stutor/deleteLecture?id=${lecture.id}">Remove</a>
					</div>
				</c:forEach>
			</div>
			<div id="timeframeList">
				<h1>Is free during:</h1>
				<c:forEach items="${timeframes}" var="timeframe">
					<div id="table">
						<label><c:out value="${timeframe}" /></label>
						<a href="http://localhost:8080/Stutor/editTimeframe?id=${timeframe.id}">Edit</a>
						<a href="http://localhost:8080/Stutor/deleteTimeframe?id=${timeframe.id}">Remove</a>
					</div>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>
	<a href="http://localhost:8080/Stutor/options">Edit Profile Information</a>
	<br><br>
	<div>
		<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	</div>
</body>
</html>