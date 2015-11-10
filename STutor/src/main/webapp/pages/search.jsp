<!-- Every user is able to search for Tutors on a given lecture. For that, he has to 
set the University, sunject and lecture name. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Search Page</title></head>
<c:import url="template/header.jsp" />
<h1>Search Page</h1>

<form:form method="post" modelAttribute="searchForm" action="search" id="searchForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Choose Search options</legend>
		
		<label class="control-label" for="field-university">Choose University</label>
        <div class="controls">
        	<form:select path="university">
            	<form:options items="${universities}" itemValue="id"/>
            </form:select>
        </div>
		
        <label class="control-label" for="field-subject">Choose Subject</label>
        <div class="controls">
        	<form:select path="subject">
            	<form:options items="${subjects}" itemValue="id"/>
            </form:select>
        </div>
        
        <c:set var="nameErrors"><form:errors path="name"/></c:set>
        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
            <label class="control-label" for="field-email">Name</label>
            <div class="controls">
                <form:input path="name" id="field-name" tabindex="1" maxlength="45" placeholder="Name"/>
                <form:errors path="name" cssClass="help-inline" element="span"/>
            </div>
        </div>
                
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Search</button>
            <button type="button" onclick="location.href='http://localhost:8080/Stutor/afterLogin';" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>
</html>