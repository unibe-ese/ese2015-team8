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

		<form:form method="post" modelAttribute="refinedSearchForm"
			action="searchWFilters" id="refinedSearchForm" autocomplete="off">


			<c:set var="nameErrors"><form:errors path="name" />	</c:set>
			<label class="control-label" for="field-email">Lecture name</label>
				<form:input path="name" id="field-name" tabindex="1" maxlength="45"
					placeholder="Name" />
				<form:errors path="name" cssClass="help-inline" element="span" />

			<label class="control-label" for="field-university">
				University</label>

			<form:select path="university">
				<form:option value="-1">All universities</form:option>
				<form:options items="${universities}" itemValue="id" />
			</form:select>


			<label class="control-label" for="field-subject">Subject</label>

			<form:select path="subject">
				<form:option value="-1">All subjects</form:option>
				<form:options items="${subjects}" itemValue="id" />
			</form:select>

			<label class="control-label" for="field-gender">Gender</label>

			<form:select path="gender">
				<option>doesn't matter</option>
				<option>female</option>
				<option>male</option>
			</form:select>

			<label class="control-label" for="field-minGrade">minimum
				grade</label>

			<form:select path="minGrade">
				<option>0</option>
				<option>4.0</option>
				<option>4.5</option>
				<option>5.0</option>
				<option>5.5</option>
				<option>6.0</option>
			</form:select>



			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Apply Filter [NOT WORKING]</button>

			</div>

		</form:form>

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