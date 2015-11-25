<!-- This page shows your own profile. Shown parameters are: ID, first name, last name,
username email and if you're a Tutor: gender, given lectures and free time. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head><title>Comments</title></head>
<c:import url="template/header.jsp" />

<style>
#showCommentsTableIndex th{
	text-align:left;
	font-weight: bold;
}

#showCommentsTableContentRating{
	text-align:center;
	font-weight: normal;
}

#showCommentsTableContentText{
	text-align:left;
	font-weight: normal;
}
</style>
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
<body>
	<h1>Comments:</h1>
	
	<table>
		<tr id="showCommentsTableIndex">
			<th>Rating</th>
			<th>Comment</th>
		</tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<th id="showCommentsTableContentRating"><c:out value="${comment.rating}" /></th>
				<th id="showCommentsTableContentText"><c:out value="${comment.comment}" /></th>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<div>
		<a href="http://localhost:8080/Stutor/hiddenProfile?userId=${tutorId}">return</a>
	</div>
	
	<br><br>
	<div>
		<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
	</div>
</body>
</html>