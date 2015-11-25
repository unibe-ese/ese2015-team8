<!-- if a user wants to access a page, he is not allowed to access, he gets redirected to this 
page. It tells him that he is not allowed to access the page he wanted to access. This happens
only when he enters a link to a forbidden site manually. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Access denied!</title></head>

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
</nav><h1>Access denied!</h1>

<body>

<h1><a href="http://localhost:8080/Stutor/afterLogin" 
style="text-decoration:none; color:black; font-family: Tahoma;">Go back to main page</a></h1>

</body>