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
	<link rel="stylesheet" type="text/css" href="css/mailbox.css">
	<script src="js/mailbox.js"></script>	
	<title>Main Page</title>
</head>

<c:import url="template/header.jsp" />

<body  onload="mailBoxColour(${notificationNumber})">

<div id="mail-box">
	<a id="mail-image" href="notifications?userId=<c:out value="${user.id}"/>"><img src="img/mail113x84.png"/></a>
	<p id="mail-text">${notificationNumber}</p>
</div> </body>

    
  
<div class="row">
            <div class="col-lg-4">
               
		         <a href="http://localhost:8080/Stutor/profile?userId=<c:out value="${user.id}"/>"><img class="img-responsive img-rounded" src="css/images/profile.jpg"></a>
               
            </div>
            <div class="col-lg-7">
                
                    
                    <h2>${welcomeMsg}</h2>
                   
                    <p>
                      STutor is a learning platform that is easy to use for both: tutors and students. And no one can prove it better than our clients! 
                    </p>
                     <blockquote>
                         We are so pleased to have an excellent long term working relationship with Lizelle. She is always professional, dependable, and her expertise is outstanding. We are truly fortunate to have her as our tutor
                        <footer>Evelyn, student</footer>
                       </blockquote>
                       <blockquote>
                         As a tutor who also works full time, it is a great time saver and asset to find a web service that gives my profile lots of web exposure, manages credit checks and client payments, and has an easy to maintain platform that is up to date and appealing
                        <footer>Mary, tutor</footer>
                       </blockquote>
                     <blockquote>
                         I had no idea where to start finding a tutor for a project I was working on. STutor did all the work for me and I found someone excellent!
                        <footer>Jim, student</footer>
                       </blockquote>
            
        </div>
        </div>
       

</body>
</html>