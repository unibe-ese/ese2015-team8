<!-- That's the notification itself. It says either that you got a contact request that you
can accept or decline or that your request was accepted and hence gives you the chance
to rate the Tutor. -->

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
	<c:choose>
		<c:when test="${notification.titel == 'Contact Request'}">
			<a href="http://localhost:8080/Stutor/notificationAccept">Accept</a>
			<br>
			<a href="http://localhost:8080/Stutor/notificationDecline">Decline</a>
			<br>
		</c:when>
		<c:when test="${notification.titel == 'Request Accepted'}">
			<p>Please <a href="http://localhost:8080/Stutor/rateTutor?tutorId=<c:out value='${tutorId}'/>">RATE</a> the Tutor once he finished his teaching</p>
		</c:when>
	</c:choose>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>