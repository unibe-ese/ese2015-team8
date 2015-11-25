<!-- Every User has a notification page where he can see his list of notifications. As a Student, you
can send a request to a Tutor and thus get notified about whether he accepted or declined. As a 
Tutor, you can do that, too, since you're also a Student, but you can also get request notifications.
That's what this page serves for.-->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Notifications</title></head>
<c:import url="template/header.jsp" />

<style>
#table_list {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#table_list ul li{
	display: inline-block;
	width: 150px;
	
}
</style>

<body>
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
</nav>	<h1>Notifications:</h1>
	<div id="lectureList">
		<div id="table_list">
			<ul><li>status</li><li>titel</li><li>date</li><li>open</li></ul>
			<c:forEach var="notification" items="${notificationList}" >
			<ul>
				<li><c:out value="${notification.status}" /></li>
				<li><c:out value="${notification.titel}" /></li>
				<li><c:out value="${notification.date}" /></li>
				<li><a href="readNotification?notificationId=<c:out value="${notification.id}"/>">read</a></li>
								<li><a href="deletedNotification?notificationId=<c:out value="${notification.id}"/>">remove</a></li>
				
			</ul>
			</c:forEach>
		</div>
	</div>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>