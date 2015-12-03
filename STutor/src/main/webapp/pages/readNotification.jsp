<!-- That's the notification itself. It says either that you got a contact request that you
can accept or decline or that your request was accepted and hence gives you the chance
to rate the Tutor. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<c:import url="template/header.jsp" />

<h1>
	<c:out value="${notification.titel}" />
</h1>

<body>
	<p>${notification.message}</p>
	<c:choose>
		<c:when test="${notification.titel == 'Contact Request'}">

			<a class="btn btn-primary btn-md" href="http://localhost:8080/Stutor/notificationAccept">Accept</a>
			<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/notificationDecline">Decline</a>
				
			<br>
			
		</c:when>
		<c:when test="${notification.titel == 'Request Accepted'}">
			<p>Please <a href="http://localhost:8080/Stutor/rateTutor?notificationId=<c:out value='${notification.id}'/>">RATE</a> the Tutor once he finished his teaching</p>
		</c:when>
		<c:when test="${notification.titel == 'Welcome'}">
			<p> You can access your Profile <a href="http://localhost:8080/Stutor/profile?userId=<c:out value='${notification.toStudentId}'/>">HERE</a> or on the <a href="http://localhost:8080/Stutor/afterLogin">Main Page</a></p>
		</c:when>
	</c:choose>
	
	<br>
	
	<div>
		<a class="btn btn-primary btn-md" href="notifications?userId=<c:out value="${notification.toStudentId}"/>">Back to Notifications</a>
	</div>
</body>

<c:import url="template/footer.jsp" />

</html>