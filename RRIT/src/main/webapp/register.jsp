<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
        
    </script>
	<link type="text/css" href="<c:url value='/assets/css/font-awesome.min.css' />" rel="stylesheet" />
    <link type="text/css" href="<c:url value='/assets/css/styles.css' />" rel="stylesheet" />
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
<body>
<div class="signupform">
	<div class="container">
		<div class="agile_info">
			<div class="w3l_form">
				<div class="left_grid_info">
					<h1>Welcome to The Resource Requisition Automation Tool</h1>
					<img src="<c:url value='/assets/img/recruiting.png' />" alt="" height="450" widht="350" style="margin-top:100px; margin-right:30px" />
				</div>
			</div>
			<div class="w3_info">
				<h2 style="margin-bottom:35px;">Create a new Account</h2>
				<form action="reg" method="post" autocomplete="off">
					<label >Employee ID</label>
					<span class="fa fa-info-circle" title="Employee ID must comprise of 6 digits" aria-hidden="true"></span>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group" >
						<span class="fa fa-pencil" aria-hidden="true"></span>
						<input type="text" name="id" pattern="^([0-9]{6})$" placeholder="Enter Your EmployeeID" required="">
					</div>	
					<label >Name</label>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group" >
						<span class="fa fa-envelope" aria-hidden="true"></span>
						<input type="text" name="name" placeholder="Name" required=""> 
					</div>
					<label >Password</label>
					<span class="fa fa-info-circle" title="Password must have a lowercase, an uppercase, a digit and a special character" aria-hidden="true"></span>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group">
						<span class="fa fa-lock" aria-hidden="true"></span>
						<input type="Password" id="password" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,}$" placeholder="Enter Password" required="">
					</div>	
					<label >Confirm Password</label>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group">
						<span class="fa fa-check-circle-o" aria-hidden="true"></span>
						<input type="Password" id="cpassword" name="cpassword" placeholder="Re-Enter Password" required="">
					</div>					
					<button class="btn btn-danger btn-block" style="margin-right:15px" type="submit">SignUp</button >  <font color="red" style="font-weight: bold;" > ${message} </font>        
				</form autocomplete="off">
			<p class="account1">Already having an account? <a href="index.jsp">Login Here</a></p>
				
				
			</div>
		</div>
	</div>
	
</div>
</body>
</html>