<!-- As a Tutor, you can add a parameter to your profile that specifies when you
have leisure time to tutor. That gives the student additional information on 
which Tutor he should choose. The Tutor can pick a day and on that day a time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
	<title>Timeframe</title>
<style>.help-inline{color: #BE1E1E}</style>
</head>
<c:import url="template/header.jsp" />
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container-fluid">
     <div class="navbar-header">
      <a class="navbar-brand" href="http://localhost:8080/Stutor/afterLogin">STutor</a>
    </div>
 
    <div class="collapse navbar-collapse">
      <form class="navbar-form navbar-left" method="get" action="http://localhost:8080/Stutor/basicSearch" role="search">
        <div class="form-group">
          <input type="search" class="form-control" placeholder="Search..." name="q" >
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="http://localhost:8080/Stutor/logout">Log Out</a></li>
        <li><a href="http://localhost:8080/Stutor/options">Edit Profile</a></li>
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<h1></h1>

<form:form method="post" modelAttribute="timeframeForm" action="editedTimeframe" id="editTimeframeForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend></legend>
		
		<label class="control-label" for="field-day">Choose Day</label>
        <div class="controls">
        	<form:select path="day">
            	<form:option items="Monday" value="1" label="Monday"/>
            	<form:option items="Tuesday" value="2" label="Tuesday"/>
            	<form:option items="Wednesday" value="3" label="Wednesday"/>
            	<form:option items="Thursday" value="4" label="Thursday"/>
            	<form:option items="Friday" value="5" label="Friday"/>
            	<form:option items="Saturday" value="6" label="Saturday"/>
            	<form:option items="Sunday" value="7" label="Sunday"/>
            </form:select>
        </div>
        
        <c:set var="fromTimeErrors"><form:errors path="fromTime"/></c:set>
        <div class="control-group<c:if test="${not empty fromTimeErrors}"> error</c:if>">
            <label class="control-label" for="field-fromTime">From (hour):</label>
            <div class="controls">
                <form:input path="fromTime" id="field-fromTime" tabindex="1" maxlength="2"/>
                <form:errors path="fromTime" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="toTimeErrors"><form:errors path="toTime"/></c:set>
        <div class="control-group<c:if test="${not empty toTimeErrors}"> error</c:if>">
            <label class="control-label" for="field-toTime">To (hour):</label>
            <div class="controls">
                <form:input path="toTime" id="field-toTime" tabindex="2" maxlength="2"/>
                <form:errors path="toTime" cssClass="help-inline" element="span"/>
            </div>
        </div>
          <br>       
        <div class="form-actions">
            <button type="submit" class="btn btn-primary btn-md">Save changes</button>
            <a class="btn btn-default btn-md" href="http://localhost:8080/Stutor/afterLogin" role="button">Cancel</a>
            
        </div>
        <form:input path="id" id="field-id" type="hidden"/>
        
    </fieldset>
</form:form>