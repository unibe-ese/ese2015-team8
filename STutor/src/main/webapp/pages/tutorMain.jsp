<!-- That's the main page after the login for the tutor. He can  see his profile
or his notifications and he can add lectures and his scheduals. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
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
<h1>Welcome</h1>

<h2>${welcomeMsg}
</h2>

<body>
	<a href="profile?userId=<c:out value="${user.id}"/>">See Profile</a>
	<br>
	<a href="notifications?userId=<c:out value="${user.id}"/>">Notifications</a>
	<br><br>
	<a href="<c:url value="addLecture" />" > Add Lectures</a>
	<br>
	<a href="<c:url value="addTimeframe" />" > Add Timeframe</a>
</body>
</html>