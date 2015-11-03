<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:import url="template/header.jsp" />

<style type="text/css">
.subButton a {
    border-bottom: 1px solid #777777;
    border-left: 1px solid #000000;
    border-right: 1px solid #333333;
    border-top: 1px solid #000000;
    color: #000000;
    display: block;
    height: 2.5em;
    padding: 0 1em;
    width: 5em;       
    text-decoration: none;       
}
</style>

<h1> <c:out value="Payement"/> </h1>

<body>
	<p>The fea is of <c:out value="${price}"/> Fr.-</p>
	
	<form action=null>
		Credit Card: <input type="text"><br>
		Card Number: <input type="text"><br>
		Code: <input type="text"><br>
	</form>
	
	<div class="subButton">
    	<a href="paymentDone">Pay</a>
	</div>
</body>
<a href="http://localhost:8080/Stutor/afterLogin">Back to main page</a>
</html>