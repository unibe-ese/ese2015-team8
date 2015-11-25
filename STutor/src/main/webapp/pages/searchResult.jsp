<!-- After the simple search, you get some results. If they're not to your 
convenience, there's a bar on the left to refine your search. Here you can also
set the University, the subject the minimum grade you expect from the Tutor 
and what gender he/she should be. -->

<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Search Result</title>
</head>
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
<style>
#table_list {
	list-style-type: none;
	margin: 0;
	padding: 0px;
}

#table_list ul li {
	display: inline-block;
	width: 150px;
	padding: 0px;
	padding-left: 15px;
}

#filter {
	height: 100%;
	width: 300px;
	float: left;
	padding: 10px;
	padding-right: 5px;
	border-right: thick solid #0892d0;
}
</style>

<body>
	<h1>Search Results</h1>

	<div id="filter">
		<h4>Refine your search:</h4>

		<form:form method="post" modelAttribute="refinedSearchForm"
			action="searchWFilters" id="refinedSearchForm" autocomplete="off">


			<c:set var="nameErrors"><form:errors path="name" />	</c:set>
			<label class="control-label" for="field-email">Lecture name</label>
				<form:input path="name" id="field-name" tabindex="1" maxlength="45"
					placeholder="Name" />
				<form:errors path="name" cssClass="help-inline" element="span" />

			<label class="control-label" for="field-university">
				University</label>

			<form:select path="university">
				<form:option value="-1">All universities</form:option>
				<form:options items="${universities}" itemValue="id" />
			</form:select>


			<label class="control-label" for="field-subject">Subject</label>

			<form:select path="subject">
				<form:option value="-1">All subjects</form:option>
				<form:options items="${subjects}" itemValue="id" />
			</form:select>

			<label class="control-label" for="field-gender">Gender</label>

			<form:select path="gender">
				<form:options items="${gender}" />
			</form:select>

			<label class="control-label" for="field-minGrade">minimum
				grade</label>

			<form:select path="minGrade">
				<form:option value="0">0</form:option>
				<form:option value="4.0">4.0</form:option>
				<form:option value="4.5">4.5</form:option>
				<form:option value="5.0">5</form:option>
				<form:option value="5.5">5.5</form:option>
				<form:option value="6.0">6.0</form:option>
			</form:select>
			
			<label class="control-label" for="field-minGrade">Sort by: </label>
			<form:select path="sortBy">
				<form:option value="name">-</form:option>
				<form:option value="tutor.rating">Rating</form:option>
				<form:option value="grade">Grade</form:option>
				<form:option value="tutor.wage">Fee</form:option>
			</form:select>



			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Apply Filter</button>

			</div>

		</form:form>

	</div>



	<div id="lectureList">

		<h1>Tutors for this Lecture:</h1>
		<div id="table_list">
			<ul>
				<li>username</li>
				<li>rating</li>
				<li>grade</li>
				<li>wage (Fr./h)</li>
				<li>University</li>
				<li>profile</li>
				
			</ul>
			<c:forEach var="loop" items="${lectures}" varStatus="status">
				<ul>
					<li><c:out value="${lectures[status.index].tutor.username}" /></li>
					<li><c:out value="${lectures[status.index].tutor.rating}" /></li>
					<li><c:out value="${lectures[status.index].grade}" /></li>
					<li><c:out value="${lectures[status.index].tutor.wage}" /></li>
					<li><c:out value="${lectures[status.index].university}" /></li>
					<li><a
						href="hiddenProfile?userId=<c:out value="${lectures[status.index].tutor.id}"/>">See
							Profile</a></li>
				</ul>
			</c:forEach>
		</div>
	</div>





</body>
</html>