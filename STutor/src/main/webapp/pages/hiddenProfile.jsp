<!-- This is a the profile of another student that a user can see. It only reveals the username, 
not the real name. Also shown are the rating, gender and if he's a Tutor when
he has time. The important element on this page is the contact button with which a 
Student can send a request to the Tutor, which he can either accept or decline. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Profile</title></head>
<c:import url="template/header.jsp" />

<body>
	<h1>Tutor Profile</h1>

	<table>
		<tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
		<tr><td>Rating: </td><td><c:out value="${student.rating}" /> <a href="showComments?tutorId='${student.id}'">see</a></td></tr>
		<tr><td>Gender: </td><td><c:out value="${student.gender}" /></td></tr>
	</table>
	
	<c:choose>
		<c:when test="${student.isTutor}">
			<div id="lectureList">
				<h1>Gives the following Lecture:</h1>
				<c:forEach items="${lectures}" var="lecture">
					<div id="table">
						<label><c:out value="${lecture}" /></label>
					</div>
				</c:forEach>
			</div>
			<div id="timelapsList">
				<h1>Is free during:</h1>
				<c:forEach items="${timelapses}" var="timelaps">
					<div id="table">
						<label><c:out value="${timelaps}" /></label>
					</div>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>
	<br><br>
	<input type="button" onclick="location.href='http://localhost:8080/Stutor/confirmContact';"  value="contact" >
	<div>
		<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	</div>
</body>
</html>