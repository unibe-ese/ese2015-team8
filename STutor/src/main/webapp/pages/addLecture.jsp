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

<form:form method="post" modelAttribute="addLectureForm" action="addedLecture" id="addLectureForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Add the lecture you want to teach</legend>

        <label class="control-label" for="field-team">Do you want to join a team?</label>
            <div class="controls">
         <form:select path="lecture">
                  	<form:options items="${lectures}" itemValue="id"/>
                </form:select>
                </div>
                
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Add this lecture.</button>
            <button type="button" onclick="location.href='http://localhost:8080/Stutor';" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>
