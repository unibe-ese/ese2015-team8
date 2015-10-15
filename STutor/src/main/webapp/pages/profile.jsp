<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>User Profile</h1>

<table>
	<tr>
        <tr><td>Id: </td><td><c:out value="${user.id}" /></td></tr>
        <tr><td>First Name: </td><td><c:out value="${user.firstName}"></c:out></td></tr>
        <tr><td>Last Name: </td><td><c:out value="${user.lastName}"></c:out></td></tr>
        <tr><td>Email: </td><td><c:out value="${user.email}"></c:out></td></tr>
	</tr>
</table>