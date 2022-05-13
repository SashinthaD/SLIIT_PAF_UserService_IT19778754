<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Customer Search</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/css/bootstrap.min.css">

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
  </nav>
  <br><br><br><br><br>
  
  <div class="col-md-8 mx-auto bg-light rounded p-4">
        <h5 class="text-center font-weight-bold">Employee Search</h5>
        
        
        <form action="EmployeeDetails.jsp" method="post" class="p-3">
          <div class="input-group">
            <input type="text" name="search" id="search" class="form-control form-control-lg rounded-0 border-info" placeholder="Search..." autocomplete="off" required>
            <div class="input-group-append">
              <input type="submit" id="hidEmployeeIDSave" name="submit" value="Search" class="btn btn-info btn-lg rounded-0">
            </div>
          </div>
        </form>
      </div>

</html>