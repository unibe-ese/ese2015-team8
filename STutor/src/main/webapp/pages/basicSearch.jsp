<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Profile</title></head>
<c:import url="template/header.jsp" />
<h1>Search Page</h1>

<table>
<%-- 	<tr><td>Id: </td><td><c:out value="${student.id}" /></td></tr>
	<tr><td>First Name: </td><td><c:out value="${student.firstName}" /></td></tr>
	<tr><td>Last Name: </td><td><c:out value="${student.lastName}" /></td></tr>
	<tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
	<tr><td>Email: </td><td><c:out value="${student.email}" /></td></tr> --%>
	<tr><td>Search Results: </td></tr>
</table>

	<table>
		<tr><td>Name of a tutor of this lecture: </td><td><c:out value="${basicSearch}" /></td></tr>
	</table>

<body>
	<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	<br><br>
	<a href="<c:url value="logout" />" > Logout</a>
</body>
</html>