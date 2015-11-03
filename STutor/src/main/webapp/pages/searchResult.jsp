<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Search Result</title>
</head>
<c:import url="template/header.jsp" />

<style>
#table_list {
	list-style-type: none;
	margin: 0;
	padding: 0px;
}

#table_list ul li {
	display: inline-block;
	width: 150px;
	padding: 0px;
	padding-left: 15px;
}

#filter {
	height: 100%;
	width: 300px;
	float: left;
	padding: 10px;
	padding-right: 5px;
	border-right: thick solid #0892d0;
}
</style>

<body>
	<h1>Search Results</h1>

	<div id="filter">
		<h4>Refine your search:</h4>
		<form>


			<label class="control-label" for="field-university">University</label>
			<div class="controls">
				<form:select path="university">

					<form:options items="${universities}" itemValue="id" />
				</form:select>
			</div>

			<div class="form-actions">
				<button type="submit"> Apply (NOT WORKING YET)</button>
			</div>

		</form>

	</div>



	<div id="lectureList">

		<h1>Tutors for this Lecture:</h1>
		<div id="table_list">
			<ul>
				<li>username</li>
				<li>rating</li>
				<li>grade</li>
				<li>profile</li>
			</ul>
			<c:forEach var="loop" items="${lectures}" varStatus="status">
				<ul>
					<li><c:out value="${tutors[status.index].username}" /></li>
					<li><c:out value="${tutors[status.index].rating}" /></li>
					<li><c:out value="${lectures[status.index].grade}" /></li>
					<li><a
						href="hiddenProfile?userId=<c:out value="${tutors[status.index].id}"/>">See
							Profile</a></li>
				</ul>
			</c:forEach>
		</div>
	</div>





</body>
</html>