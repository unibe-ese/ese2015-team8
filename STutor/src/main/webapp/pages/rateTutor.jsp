<!-- When you - as a Student - have been accepted by a Tutor, you can rate that tutor on a 
scale from 1 to 6 and also leave a comment. This is for other Students to see if a Tutor
is suitable or not. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<title>Rate Tutor</title>
</head>

<c:import url="template/header.jsp" />

<body>
	<h1> Rate <c:out value="${tutor.username}"></c:out> </h1>

	<form:form method="post" modelAttribute="commentForm" action="saveTutorRating" id="commentForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<label class="control-label" for="field-rating">Choose a Rating</label>
			<div class="controls">
				<form:select path="rating">
					<form:option items="6" value="6" />
					<form:option items="5" value="5" />
					<form:option items="4" value="4" />
					<form:option items="3" value="3" />
					<form:option items="2" value="2" />
					<form:option items="1" value="1" />
				</form:select>
			</div>

			<c:set var="commentErrors"> <form:errors path="comment" /></c:set>
			<div class="control-group<c:if test="${not empty commentErrors}"> error</c:if>">
				<label class="control-label" for="field-comment">Comment</label>
				<div class="controls">
					<form:input path="comment" id="field-comment" tabindex="1" maxlength="500" />
					<form:errors path="comment" cssClass="help-inline" element="span" />
				</div>
			</div>

			<div class="form-actions">
				<button type="submit" class="btn btn-primary btn-md">Submit</button>
				<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/afterLogin" role="button">Back to main page</a>
			</div>
		</fieldset>
	</form:form>

	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4> ${page_error}
		</div>
	</c:if>
</body>

<c:import url="template/footer.jsp" />