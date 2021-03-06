<!-- On this page, you can sign up, using a unique username and email address, a password, 
  a first name and a last name. You can also pick a field that you're a Tutor. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<title>Sign Up</title>
</head>

<c:import url="template/header.jsp" />

<body>
	<h1>Sign Up Here!</h1>

	<form:form method="post" modelAttribute="signupForm" action="create" id="signupForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<legend>Enter Your Information</legend>

			<c:set var="emailErrors"><form:errors path="email" /></c:set>
			<div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
				<label for="email">Email</label>
				<div class="controls">
					<form:input path="email" id="field-email" tabindex="1" maxlength="45" placeholder="Email" />
					<form:errors path="email" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="firstNameErrors"><form:errors path="firstName" /></c:set>
			<div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
				<label for="first">First Name</label>
				<div class="controls">
					<form:input path="firstName" id="field-firstName" tabindex="2" maxlength="35" placeholder="First Name" />
					<form:errors path="firstName" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="lastNameErrors"><form:errors path="lastName" /></c:set>
			<div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
				<label for="last">Last Name</label>
				<div class="controls">
					<form:input path="lastName" id="field-lastName" tabindex="3" maxlength="35" placeholder="Last Name" />
					<form:errors path="lastName" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="usernameErrors"><form:errors path="username" /></c:set>
			<div class="control-group<c:if test="${not empty usernameErrors}"> error</c:if>">
				<label for="username">Username</label>
				<div class="controls">
					<form:input path="username" id="field-username" tabindex="3" maxlength="35" placeholder="Username" />
					<form:errors path="username" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="passwordErrors"><form:errors path="password" /></c:set>
			<div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
				<label for="password">Password</label>
				<div class="controls">
					<form:input type="password" path="password" id="field-password" tabindex="3" maxlength="35" placeholder="Password" />
					<form:errors path="password" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="isTutorErrors"><form:errors path="isTutor" /></c:set>
			<form:checkbox path="isTutor" value="true" label="Sign Up as Tutor" />

			<div class="form-actions">
				<button type="submit" class="btn btn-primary btn-md">Sign up</button>
				<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/" role="button">Cancel</a>
			</div>
		</fieldset>
	</form:form>


	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4>${page_error}
		</div>
	</c:if>
</body>

<c:import url="template/footer.jsp" />