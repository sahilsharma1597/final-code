<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
        
        function noBack()
        {
        	
        	window.history.forward();
        	
        }
        setTimeout("noBack()",0);

    </script>
    <link type="text/css" href="<c:url value='/assets/css/font-awesome.min.css' />" rel="stylesheet" />
    <link type="text/css" href="<c:url value='/assets/css/styles.css' />" rel="stylesheet" />
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack():" onunload()="">

<div class="signupform">
	<div class="container">
		<div class="agile_info">
			<div class="w3l_form">
				<div class="left_grid_info">
					<h1>Welcome to The Resource Requisition Automation Tool</h1>
					<img src="<c:url value='/assets/img/recruiting.png' />"  alt="" height="450" widht="350" style="margin-top:100px; margin-right:30px" />
				</div>
			</div>
			<div class="w3_info">
				<h2>Login to your Account</h2>
				<p>Enter your details to login.</p>
				<form action="login" method="post" autocomplete="off">
					<label >Employee ID</label>
					<span class="fa fa-info-circle" title="Employee ID must comprise of 6 digits" aria-hidden="true"></span>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group" >
						<span class="fa fa-pencil" aria-hidden="true"></span>
						<input type="text" name="id" pattern="^([0-9]{6})$" placeholder="Enter Your EmployeeID" required=""> 
					</div>
					<label >Password</label>
					<span class="fa fa-info-circle" title="Password must have a lowercase, an uppercase, a digit and a special character" aria-hidden="true"></span>
					<div style="margin-top:15px; margin-bottom:30px" class="input-group">
						<span class="fa fa-lock" aria-hidden="true"></span>
						<input type="Password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,}$" name="password" placeholder="Enter Password" required="">
					</div> 
											
						<button class="btn btn-danger btn-block" style="margin-right:15px" type="submit">Login</button ><font color="red" style="font-weight: bold;" > ${message} </font>              
				</form>
				
				<p class="account1">Don't have an account? <a href="register.jsp">Register here</a></p>
			</div>
		</div>
	</div>
	
</div>
	
</body>
</html>