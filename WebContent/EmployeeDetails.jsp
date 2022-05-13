<%@page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registered Employee Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/employee.js"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
		<a class="navbar-brand" href="#">ElectroGrid</a>
		<form class="form-inline">

			<a href="employee.jsp"><input id="btnHome" name="btnHome"
				type="button" value="Home" class="btn btn-primary btn-lg"></a>
			&nbsp &nbsp &nbsp<input id="btnLogout" name="btnLogout" type="button"
				value="Login" class="btn btn-primary btn-lg">
		</form>





	</nav><br>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-7">
			
			
				<h1>Registered Employee Details</h1>
			

				

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				</div>
				<br>
				<div id="divEmployeeGrid">
					<%
					    Employee empObj = new Employee();
						out.print(empObj.viewProfile());
					%>
				</div>
			</div>
		
	</div>
</body>
</html>