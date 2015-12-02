<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		
		<link rel="stylesheet" type="text/css" href="css/style.css">

		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="css/images/favicon.ico" type="image/x-icon">
		
		<link rel="stylesheet" type="text/css" href="css/mailbox.css">
		<script src="js/mailbox.js"></script>				
	</head>
<body  onload="mailBoxColour(${user.notifications})"></body>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container-fluid">
     <div class="navbar-header">
      <a class="navbar-brand" href="http://localhost:8080/Stutor/afterLogin">STutor</a>
    </div>
 
    <div class="collapse navbar-collapse">
      <form class="navbar-form navbar-left" method="get" action="http://localhost:8080/Stutor/basicSearch" role="search">
        <div class="form-group">
          <input type="search" class="form-control" placeholder="lecture name..." name="q" >
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
      	<li id="mail-box">
      		<a href="notifications?userId=<c:out value="${user.id}"/>"><img src="img/mail45x39.png"/></a>
    	</li>
      	<li><a href="http://localhost:8080/Stutor/afterLogin">Main Page</a></li>
      	<li><a href="http://localhost:8080/Stutor/profile?userId=<c:out value="${user.id}"/>">Profile</a></li>
        <li><a href="http://localhost:8080/Stutor/logout">Log Out</a></li>       
      </ul>
    </div>
  </div>
</nav>