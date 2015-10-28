<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Notifications</title></head>
<c:import url="template/header.jsp" />

<style>
#table_list {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#table_list ul li{
	display: inline-block;
	width: 150px;
	
}
</style>

<body>
	<h1>Notifications:</h1>
	<div id="lectureList">
		<div id="table_list">
			<ul><li>status</li><li>titel</li><li>date</li><li>open</li></ul>
			<c:forEach var="notification" items="${notificationList}" >
			<ul>
				<li><c:out value="${notification.status}" /></li>
				<li><c:out value="${notification.titel}" /></li>
				<li><c:out value="${notification.date}" /></li>
				<li><a href="readNotification?notificationId=<c:out value="${notification.id}"/>">read</a></li>
			</ul>
			</c:forEach>
		</div>
	</div>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>