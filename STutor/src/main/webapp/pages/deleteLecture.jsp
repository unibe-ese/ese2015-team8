<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Lecture</title>
</head>
<body>
<h3>
Are you sure you want to delete this lecture? (temporary jsp..)
</h3>

${lecture}

<button type="button" onclick="location.href='/Stutor/deletedLecture?id=${lecture.id}'"> YES </button>
<button type="button" onclick="location.href='/Stutor/profile?userId=${user.id}'"> Cancel </button>
</body>
</html>