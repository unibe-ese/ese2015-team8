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
                        Sme welcome text Some welcome text Some welcome text Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
Some welcome text Some welcome text Some welcome text
                    </p>
                
            
        </div>
        </div>
       

</body>
</html>