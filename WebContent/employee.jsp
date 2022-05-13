<%@page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Registration</title>
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
			
			
				<h1>Employee Registration</h1>
			
				<form id="formEmployee" name="formEmployee" method="post" action="employee.jsp">


					Employee Name: <input id="employeeName" name="employeeName" type="text"
						class="form-control form-control-sm"> 
						
						<br>Employee Email: <input id="employeeEmail" name="employeeEmail" type="text"
						class="form-control form-control-sm"> 
						
						<br>Employee Age: <input id="empAge" name="empAge" type="text"
						class="form-control form-control-sm">
						
						<br>Phone Number: <input id="phone" name="phone" type="text"
						class="form-control form-control-sm">
						
						<br> NIC: <input id="nic" name="nic" type="text"
						class="form-control form-control-sm"> 
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary btn-lg btn-block"> <input type="hidden"
						id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
				</form>
				

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				</div>
				<br>
				<div id="divEmployeeGrid">
					<%
					    Employee empObj = new Employee();
						out.print(empObj.readEmployees());
					%>
				</div>
			</div>
		
	</div>
</body>
</html>