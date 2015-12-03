<!-- After the simple search, you get some results. If they're not to your 
convenience, there's a bar on the left to refine your search. Here you can also
set the University, the subject the minimum grade you expect from the Tutor 
and what gender he/she should be. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Search Result</title>
</head>

<c:import url="template/header.jsp" />

<body>
	<div class="row">
		<div class="col-md-4">
			<div class="filter">
				<h1>Search Results</h1>

				<h4>Refine your search:</h4>

				<form:form method="post" modelAttribute="refinedSearchForm" action="searchWFilters" id="refinedSearchForm" autocomplete="off">
					<c:set var="nameErrors">
						<form:errors path="name" />
					</c:set>
					
					<label class="control-label" for="field-email">Lecture name</label>
					
					<form:input path="name" id="field-name" tabindex="1" maxlength="45"
						placeholder="Name" />
					<form:errors path="name" cssClass="help-inline" element="span" />

					<label class="control-label" for="field-university">University</label>

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
						<form:options items="${gender}" />
					</form:select>

					<label class="control-label" for="field-minGrade">minimum grade</label>

					<form:select path="minGrade">
						<form:option value="0">0</form:option>
						<form:option value="4.0">4.0</form:option>
						<form:option value="4.5">4.5</form:option>
						<form:option value="5.0">5</form:option>
						<form:option value="5.5">5.5</form:option>
						<form:option value="6.0">6.0</form:option>
					</form:select>

					<label class="control-label" for="field-minGrade">Sort by:</label>
					
					<form:select path="sortBy">
						<form:option value="name">-</form:option>
						<form:option value="tutor.rating">Rating</form:option>
						<form:option value="grade">Grade</form:option>
						<form:option value="tutor.wage">Wage</form:option>
					</form:select>

					<br>

					<div class="form-actions">
						<button type="submit" class="btn btn-primary btn-md">Apply Filter</button>
					</div>
				</form:form>
			</div>
		</div>

		<div class="col-md-8">
			<h1>Tutors for this Lecture:</h1>
			<div id="table_list">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Username</th>
							<th>Rating</th>
							<th>Grade</th>
							<th>Wage (Fr./h)</th>
							<th>University</th>
							<th>Profile</th>
						</tr>
					</thead>
					<c:forEach var="loop" items="${lectures}" varStatus="status">
						<tbody>
							<tr>
								<td><c:out value="${lectures[status.index].tutor.username}" /></td>
								<td><c:out value="${lectures[status.index].tutor.rating}" /></td>
								<td><c:out value="${lectures[status.index].grade}" /></td>
								<td><c:out value="${lectures[status.index].tutor.wage}" /></td>
								<td><c:out value="${lectures[status.index].university}" /></td>
								<td><a href="hiddenProfile?userId=<c:out value="${lectures[status.index].tutor.id}"/>">See Profile</a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>

<c:import url="template/footerr.jsp" />