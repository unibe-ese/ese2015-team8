<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>Student Profile</h1>

<table>
	<tr>
        <tr><td>Id: </td><td><c:out value="${student.id}" /></td></tr>
        <tr><td>First Name: </td><td><c:out value="${student.firstName}" /></td></tr>
        <tr><td>Last Name: </td><td><c:out value="${student.lastName}" /></td></tr>
        <tr><td>Username: </td><td><c:out value="${student.username}" /></td></tr>
        <tr><td>Email: </td><td><c:out value="${student.email}" /></td></tr>
	</tr>
</table>