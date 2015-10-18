<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>Sign Up As Tutor!</h1>

<form:form method="post" modelAttribute="signupForm" action="createTutor" id="signupForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Enter Your Informations</legend>

        <c:set var="sexeErrors"><form:errors path="sexe"/></c:set>
    	<form:checkbox path="sexe" value="male" label="Male" />
    	<form:checkbox path="sexe" value="female" label="Female" />
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