<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Search Result</title></head>
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
	<h1>Search Result</h1>
	<div id="lectureList">
		<h1>Tutors for this Lecture:</h1>
		<div id="table_list">
			<ul><li>username</li><li>rating</li><li>grade</li><li>profile</li></ul>
			<c:forEach var="loop" items="${lectures}" varStatus="status">
			<ul>
				<li><c:out value="${tutors[status.index].username}" /></li>
				<li><c:out value="${tutors[status.index].rating}" /></li>
				<li><c:out value="${lectures[status.index].grade}" /></li>
				<li><a href="profile?userId=<c:out value="${tutors[status.index].id}"/>">See Profile</a></li>
			</ul>
			</c:forEach>
		</div>
	</div>
</body>
</html>