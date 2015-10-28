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
		
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/style-desktop.css">
				
	</head>
	<body class="no-sidebar">
	
		<!-- Header -->
			<div id="header-wrapper">
				<header id="header" class="container">
				
					<!-- Logo -->
						<div id="logo">
							
							<form id="tfnewsearch" method="get" action="http://localhost:8080/Stutor/basicSearch">
		        				<input type="text" class="tftextinput" name="q" size="12" placeholder="Search..."
		        				required maxlength="120"><input class="submitButton" type="button" value="Search" >
							</form>
							
							
							
							<a href="http://localhost:8080/Stutor/afterLogin"><img src="img/logo.png" align="left"></a>
							
							
							<h1><a href="http://localhost:8080/Stutor/afterLogin"><b>Welcome to STutor</b></a></h1>
						    
											
						</div>
						
						<input type="button" onclick="location.href='http://localhost:8080/Stutor/searchInitialisation';"  style="float: right;" value="Search 2" >
		        		<input type="button" onclick="location.href='http://localhost:8080/Stutor/logout';"  style="float: right;" value="log out" >
							
						
						
						<br></br>
					
				</header>
			</div>
			

			
