<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<!--
	Verti by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link rel="stylesheet" type="text/css" href="css/skel.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/style-desktop.css">
				
	</head>
	<body class="no-sidebar">
	
		<!-- Header -->
			<div id="header-wrapper">
				<header id="header" class="container">
				
					<!-- Logo -->
						<div id="logo">
							<a href="http://localhost:8080/Stutor/afterLogin"><img alt="icon" src="img/st(260x150).png" align="left"></a>
							<h1><a href="http://localhost:8080/Stutor/afterLogin" 
							style="text-decoration:none; color:black; font-family: Tahoma;">Welcome to STutor</a></h1>
							
							<button type="button" onclick="location.href='http://localhost:8080/Stutor/logout';" class="btn">Logout</button>
							<form id="tfnewsearch" method="get" action="http://localhost:8080/Stutor/logout">
		        				<input type="text" class="tftextinput" name="q" size="10" 
		        				maxlength="120"><input type="submit" value="search" class="tfbutton">
							</form>
							
						</div>
						<br></br>
					
				</header>
			</div>
			

			
