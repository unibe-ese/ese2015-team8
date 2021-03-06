<!-- As a Tutor, you can add a lecture that you have attended to your profile. For that,
you have to set the University where you attended the lecture, the subject the lecture
belongs to (like programming 1 belongs to Computer Science) and which grade you got
in the exam. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<head>
	<title>Edit this lecture</title>
</head>

<c:import url="template/header.jsp" />

<body>

	<h1>Edit chosen lecture:</h1>

	<form:form method="post" modelAttribute="lectureForm" action="editedLecture" id="editLectureForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<legend>Edit this lecture</legend>

			<label for="uni">Choose University</label>
			<div class="controls">
				<form:select path="university">
					<form:options items="${universities}" itemValue="id" />
				</form:select>
			</div><br>
			
			<label for="subject">Choose Subject</label>
			<div class="controls">
				<form:select path="subject">
					<form:options items="${subjects}" itemValue="id" />
				</form:select>
			</div><br>
			
			<c:set var="nameErrors"><form:errors path="name" /></c:set>
			<div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
				<label for="name">Name</label>
				<div class="controls">
					<form:input path="name" id="field-name" tabindex="1" maxlength="45" placeholder="Name" />
					<form:errors path="name" cssClass="help-inline" element="span" />
				</div>
			</div><br>
			
			<c:set var="gradeErrors"><form:errors path="grade" /></c:set>
			<div class="control-group<c:if test="${not empty gradeErrors}"> error</c:if>">
				<label for="grade">Grade</label>
				<div class="controls">
					<form:input path="grade" id="field-grade" tabindex="2" maxlength="45" placeholder="Grade" />
					<form:errors path="grade" cssClass="help-inline" element="span" />
				</div>
			</div><br>
			
			<div class="form-actions">
				<button type="submit" class="btn btn-primary btn-md">Save changes</button>
				<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/profile?userId=<c:out value="${user.id}"/>" role="button">Cancel</a>
			</div>

			<c:if test="${page_error != null }">
				<div class="alert alert-error">
					<h4>Error!</h4>${page_error}
				</div>
			</c:if>
		</fieldset>
	</form:form>
</body>

<c:import url="template/footer.jsp" />
