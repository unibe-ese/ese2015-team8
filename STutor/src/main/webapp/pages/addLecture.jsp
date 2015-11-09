<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
	<title>Add a Lecture </title>
<style>.help-inline{color: #BE1E1E}</style>
</head>
<c:import url="template/header.jsp" />
<h1>~~here you can add a lecture to teach~~ ((This page is for tutors only--restrict later))</h1>

<form:form method="post" modelAttribute="lectureForm" action="addedLecture" id="addLectureForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Add the lecture you want to teach</legend>
		
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
        
        <c:set var="gradeErrors"><form:errors path="grade"/></c:set>
        <div class="control-group<c:if test="${not empty gradeErrors}"> error</c:if>">
            <label class="control-label" for="field-grade">Grade</label>
            <div class="controls">
                <form:input path="grade" id="field-grade" tabindex="2" maxlength="45" placeholder="Grade"/>
                <form:errors path="grade" cssClass="help-inline" element="span"/>
            </div>
        </div>
                
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Add this lecture.</button>
            <button type="button" onclick="location.href='http://localhost:8080/Stutor/afterLogin';" class="btn">Cancel</button>
        </div>
        
        
        <c:if test="${page_error != null }">
          <div class="alert alert-error">
            <h4>Error!</h4>
                ${page_error}
        </div>
        </c:if>
    </fieldset>
</form:form>
