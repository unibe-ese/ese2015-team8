<!-- Every User has a notification page where he can see his list of notifications. As a Student, you
can send a request to a Tutor and thus get notified about whether he accepted or declined. As a 
Tutor, you can do that, too, since you're also a Student, but you can also get request notifications.
That's what this page serves for.-->

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
	
		
		<table class="table table-hover">
			<thead>
			  <tr>
			   <th>Status</th>
			   <th>Title</th>
			   <th>Date</th>
			   <th>Actions</th>
			  </tr>
			</thead>
			<tbody>
			<c:forEach var="notification" items="${notificationList}" >
			<tr>
				<td><c:out value="${notification.status}" /></td>
				<td><c:out value="${notification.titel}" /></td>
				<td><c:out value="${notification.date}" /></td>
				<td><a href="readNotification?notificationId=<c:out value="${notification.id}"/>">read</a>
				<a href="deletedNotification?notificationId=<c:out value="${notification.id}"/>">remove</a>
				</td>
				
				
			</tr>
			</c:forEach>
			</tbody>
			</table>
		
	
</body>
</html>