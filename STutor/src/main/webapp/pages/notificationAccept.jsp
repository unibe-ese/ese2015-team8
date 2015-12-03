<!-- If a Tutor accepts a request, he has to pay a fee before getting the student's information.
That's what this page serves for. -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<head>
	<title>Sign Up</title>
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
</head>

<c:import url="template/header.jsp" />

<body>
	<h1><c:out value="Payment" /></h1>
<body>
	<p>The fee is of <c:out value="${price}" /> Fr.-</p>
	<form action=null>Credit Card<br></form>

	<select name="selectOp" id="selectOp" style="height: 19px;" onchange="dynamicTextField()">
		<option value="visa">Visa</option>
		<option value="master">Mastercard</option>
	</select>
	
	<br><br>

	<form action=null>Card Number: <input type="text" onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" maxlength="16"><br>Code: <input type="text" onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" maxlength="3"><br></form>

	<div class="subButton">
		<a class="btn btn-default btn-md" href="paymentDone">Pay</a>
	</div>
</body>

<c:import url="template/footer.jsp" />