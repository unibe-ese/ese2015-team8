<!-- Special page for the Tutor. If he checks the box for being a Tutor during the 
sign up, he afterwards has to say if he's male or female on this page. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Sign Up</title></head>

<c:import url="template/header.jsp" />

<h1>Sign Up As Tutor!</h1>

<form:form method="post" modelAttribute="signupForm" action="createTutor" id="tutorSignupForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter Your Informations</legend>

        <c:set var="genderErrors"><form:errors path="gender"/></c:set>
    	<form:radiobutton path="gender" value="male" label="Male"/>
    	<form:radiobutton path="gender" value="female" label="Female"/>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Sign up</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>




	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>