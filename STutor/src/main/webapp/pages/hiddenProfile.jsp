<!-- This is a the profile of another student that a user can see. It only reveals the username, 
not the real name. Also shown are the rating, gender and if he's a Tutor when
he has time. The important element on this page is the contact button with which a 
Student can send a request to the Tutor, which he can either accept or decline. -->

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
    		<td><h1>Tutor Profile</h1></td>
   			<td><br>&nbsp;&nbsp;<a class="btn btn-success btn-lg" href="#" onclick="openPopup('','');" role="button">Contact Tutor</a></td>   			
   		</tr>
    </table>
    
    <!-- popup -->
    <div id="popUpDiv" class="white_content" style="display:none">
		<h3>Are you sure you want to contact this Tutor?</h3>
		<button type="button" onclick="location.href='/Stutor/contact'"> YES </button>
		<button type="button" onclick="closePopup('','');" >NO</button>
	</div>
	
	<!-- Fade background when popup -->
	<div id="fade" class="black_overlay" style="display:none"></div>
	
	<table class="table table-hover">
		<tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
		<tr><td>Rating: </td><td><c:out value="${student.rating}" /> </td></tr>
		<tr><td>Gender: </td><td><c:out value="${student.gender}" /></td></tr>
		<tr><td>Wage: </td><td><c:out value="${student.wage} Fr./h" /></td></tr>
	</table>
	 <c:choose>
			<c:when test="${student.isTutor}">
			<a class="btn btn-primary btn-md" href="showComments?tutorId=${student.id}" role="button">See students' comments</a>
			</c:when>
		</c:choose>
	 
	<c:choose>
	<c:when test="${student.id == searchingStudent.id}">
		<c:redirect url="http://localhost:8080/Stutor/profile?userId=${searchingStudent.id}"/>
	</c:when>
		<c:when test="${student.isTutor}">
			<div id="lectureList">
				<h1>Lecture(s) given:</h1>
				<table class="table table-hover">
				 <c:forEach items="${lectures}" var="lecture">
				        <tr><td><c:out value="${lecture}" /></td>
						</tr>
				</c:forEach>
				</table>
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
	<div>
		<a class="btn btn-primary btn-md" role="button" href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	</div>
</body>
</html>