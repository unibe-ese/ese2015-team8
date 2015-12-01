<!-- This page shows your own profile. Shown parameters are: ID, first name, last name,
username email and if you're a Tutor: gender, given lectures and free time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
	<title>Profile</title>
	<link rel="stylesheet" type="text/css" href="css/popupStyle.css">
	<script src="js/popup.js"></script>
</head>
<c:import url="template/header.jsp" />

<body>
<table class="table-nonfluid">
  <tr>
    <td><h1>User Profile</h1></td>
    <td><br>&nbsp;&nbsp;<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/options" role="button">Edit</a></td></tr>
    </table>


	<table class="table table-hover">
		<tr><td>First Name: </td><td><c:out value="${student.firstName}" /></td></tr>
		<tr><td>Last Name: </td><td><c:out value="${student.lastName}" /></td></tr>
		<tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
		<tr><td>Email: </td><td><c:out value="${student.email}" /></td></tr>
		<c:choose>
			<c:when test="${student.isTutor}">
				<tr><td>Gender: </td><td><c:out value="${student.gender}" /></td></tr>
				<tr><td>Wage: </td><td><c:out value="${student.wage} Fr./h" /></td></tr>
				<tr><td>Rating: </td><td><c:out value="${student.rating}" /> </td></tr>
			</c:when>
		</c:choose>
	</table>
	<c:choose>
			<c:when test="${student.isTutor}">
			<a class="btn btn-primary btn-md" href="showComments?tutorId=${student.id}" role="button">See students' comments</a>
			</c:when>
		</c:choose>
	<c:choose>
		<c:when test="${student.isTutor}">
			<div id="lectureList">
				<h1>Lecture(s) given:</h1>
				<table class="table table-hover">
				 <c:forEach items="${lectures}" var="lecture">
				        <tr><td><c:out value="${lecture}" /></td>
						<td><a href="http://localhost:8080/Stutor/editLecture?id=${lecture.id}">Edit</a>
						<!-- Popup window for lectures deletion-->
							<div id="popUpDivL${lecture.id}" class="white_content" style="display:none">
								<h3>Are you sure you want to delete this Lecture?</h3>
								<button type="button" onclick="location.href='/Stutor/deleteLecture?id=${lecture.id}'"> YES </button>
								<button type="button" onclick="closePopup('L',${lecture.id});" >NO</button>
							</div>
							<a href="#" onclick="openPopup('L',${lecture.id});">Remove</a>
						<!-- Popup window end-->
						
					    </td></tr>
				</c:forEach>
				</table>
			</div>
			<div id="timeframeList">
				<h1>Is free during:</h1>
				<c:forEach items="${timeframes}" var="timeframe">
					<div id="table">
						<label><c:out value="${timeframe}" /></label>
						<a href="http://localhost:8080/Stutor/editTimeframe?id=${timeframe.id}">Edit</a>
						<!-- Popup window for timeframe deletion-->
							<div id="popUpDivT${timeframe.id}" class="white_content" style="display:none">
								<h3>Are you sure you want to delete this Timeframe?</h3>
								 <a class="btn btn-success btn-md" href="location.href='/Stutor/deleteTimeframe?id=${timeframe.id}'" role="button">Yes</a>
								 <button type="button" onclick="location.href='/Stutor/deleteTimeframe?id=${timeframe.id}'"> YES </button>
								<button type="button" onclick="closePopup('T',${timeframe.id});" >NO</button>
							</div>
							<a href="#" onclick="openPopup('T',${timeframe.id});">Remove</a>
						<!-- Popup window end-->
					</div>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>
	
	<!-- Fade background when popup -->
	<div id="fade" class="black_overlay" style="display:none"></div>
	
	<br>
	<div>
		<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	</div>
</body>
</html>