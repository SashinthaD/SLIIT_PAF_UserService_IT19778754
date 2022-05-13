<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

		<a class="navbar-brand" href="#">ElectroGrid</a>
		<form class="form-inline">

			<a href="customer.jsp"><input id="btnHome" name="btnHome"
				type="button" value="Home" class="btn btn-primary btn-lg"></a>
			&nbsp &nbsp &nbsp<input id="btnLogout" name="btnLogout" type="button"
				value="Login" class="btn btn-primary btn-lg">
		</form>





	</nav><br>
	<div class="container">
		<div class="row justify-content-center">
	
		<div class="col-7">
			
			
				<h1>Customer Registration</h1>
			
				<form id="formCustomer" name="formCustomer" method="post" action="customer.jsp">


					Customer Name: <input id="cusName" name="cusName" type="text"
						class="form-control form-control-sm"> 
						
						<br>Customer Address: <input id="cusAddress" name="cusAddress" type="text"
						class="form-control form-control-sm"> 
						
						<br>Customer Email: <input id="cusEmail" name="cusEmail" type="text"
						class="form-control form-control-sm">
						
						<br> Phone Number: <input id="cusPhone" name="cusPhone" type="text"
						class="form-control form-control-sm"> 
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary btn-lg btn-block"> <input type="hidden"
						id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
				</form>
				

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				</div>
				<br>
				<div id="divCustomerGrid">
					<%
					    Customer cusObj = new Customer();
						out.print(cusObj.readCustomers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>