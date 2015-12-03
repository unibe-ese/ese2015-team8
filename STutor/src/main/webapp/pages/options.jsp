<!-- On this page, you can edit your profile information. You can change everything, even your username and
email address. You can also change if you're a Tutor or not. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Options</title>
</head>

<c:import url="template/header.jsp" />

<body>
	<h1>Options</h1>

	<form:form method="post" modelAttribute="optionForm" action="optionsSaved" id="optionsForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<legend>Enter Your Information</legend>

			<c:set var="emailErrors"><form:errors path="email"/></c:set>
			<div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
				<label for="change">Change Email</label>
				<div class="controls">
					<form:input path="email" id="field-email" tabindex="1" maxlength="45" />
					<form:errors path="email" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
			<div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
				<label for="first">First Name</label>
				<div class="controls">
					<form:input path="firstName" id="field-firstName" tabindex="2" maxlength="35" />
					<form:errors path="firstName" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="lastNameErrors"><form:errors path="lastName" /></c:set>
			<div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
				<label for="last">Last Name</label>
				<div class="controls">
					<form:input path="lastName" id="field-lastName" tabindex="3" maxlength="35" />
					<form:errors path="lastName" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="usernameErrors"><form:errors path="username" /></c:set>
			<div class="control-group<c:if test="${not empty usernameErrors}"> error</c:if>">
				<label for="username">Username</label>
				<div class="controls">
					<form:input path="username" id="field-username" tabindex="3" maxlength="35" />
					<form:errors path="username" cssClass="help-inline" element="span" />
				</div>
			</div>

			<c:set var="passwordErrors"><form:errors path="password" /></c:set>
			<div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
				<label for="password">Password</label>
				<div class="controls">
					<form:input type="password" path="password" id="field-password" tabindex="3" maxlength="35" placeholder="new password" />
					<form:errors path="password" cssClass="help-inline" element="span" />
				</div>
			</div>
			
			<c:if test="${student.isTutor}">
				<c:set var="wageErrors"><form:errors path="wage" /></c:set>
				<div class="control-group<c:if test="${not empty wageErrors}"> error</c:if>">
					<label for="wage">Wage (Fr./h)</label>
					<div class="controls">
						<form:input type="wage" path="wage" id="field-wage" tabindex="4" maxlength="10" />
						<form:errors path="wage" cssClass="help-inline" element="span" />
					</div>
				</div>
			</c:if>

			<c:set var="isTutorErrors"> <form:errors path="isTutor" /> </c:set>
			<form:checkbox path="isTutor" value="true" label="Sign Up as Tutor" />

			<c:if test="${student.isTutor}">
				<c:set var="genderErrors"><form:errors path="gender" /></c:set>
				<form:radiobutton path="gender" value="male" label="Male" />
				<form:radiobutton path="gender" value="female" label="Female" />
			</c:if>

			<button type="submit" class="btn btn-primary btn-md">Submit</button>
			<a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/profile?userId=${student.id}" role="button">Cancel</a>

		</fieldset>

		<div class="form-actions"></div>

	</form:form>

	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4> ${page_error}
		</div>
	</c:if>

</body>


<c:import url="template/footer.jsp" />