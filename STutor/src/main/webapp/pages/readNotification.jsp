<!-- That's the notification itself. It says either that you got a contact request that you
can accept or decline or that your request was accepted and hence gives you the chance
to rate the Tutor. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
<h1> <c:out value="${notification.titel}"/> </h1>

<body>
	<p><c:out value="${notification.message}" /> </p>
	<c:choose>
		<c:when test="${notification.titel == 'Contact Request'}">
			<a href="http://localhost:8080/Stutor/notificationAccept">Accept</a>
			<br>
			<a href="http://localhost:8080/Stutor/notificationDecline">Decline</a>
			<br>
		</c:when>
		<c:when test="${notification.titel == 'Request Accepted'}">
			<p>Please <a href="http://localhost:8080/Stutor/rateTutor?notificationId=<c:out value='${notification.id}'/>">RATE</a> the Tutor once he finished his teaching</p>
		</c:when>
	</c:choose>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>