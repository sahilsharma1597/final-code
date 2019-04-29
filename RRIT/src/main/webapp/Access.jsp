<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Welcome</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});



</script>
    <script type="text/javascript">
    
    	function checkAssessments()
    	{
    		var val = $("#springMsg").data("msg");
 			//alert(val);
 			if(val!=null)
    		{
 				document.getElementById("demo").innerHTML = val;
 				document.getElementById("demo").style.visibility="visible";
 				document.getElementById("demo").style.color="green";
    		}
    	}
    	
    	</script>
  
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
	
</head>

<style>
body {
	padding: 0;
	margin: 0;
	background: #fff;
	min-height: 100vh;
    	background: linear-gradient(to left, #f5f5f5 80%, #fff 20%);
	font-family: 'Raleway', sans-serif;
}
.w3_info {
    flex-basis: 50%;
	-webkit-flex-basis: 45%;
    box-sizing: border-box;
	padding: 3em 4em;
    /* -webkit-box-shadow: 1px 0px 15px 0px rgba(0,0,0,0.2);
    -moz-box-shadow: 1px 0px 15px 0px rgba(0,0,0,0.2);
    box-shadow: 1px 0px 15px 0px rgba(0,0,0,0.2); */
}

input.a
{
 	background-color: #3BABD1; /* Green */
	color: white;
  	padding: 18px 32px;
  	text-align: center;
  	text-decoration: none;
  	display: inline-block;
  	font-size: 16px;
	width: 210px;
	margin-top:29px;
	border-radius:25px;
	webkit-transition-duration: 0.4s; /* Safari */
 	transition-duration: 0.4s;
}

input.b
{
padding: 8px 27px;
border-radius:8px;
}

input.a:hover {
  background-color: #3BABD1; /* Green */
  color: white;
  text-align: center;
  font-size: 18px;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 190%;
}

td, th {
  border: 1px solid #c7bfbf;
  text-align: left;
  padding: 17px;
}
tbody:nth-child(even) {
  background-color: #ecebeb;
  
}


</style>
<body onload="checkAssessments()">

<% String username = (String)request.getSession().getAttribute("name"); 
String access = (String)request.getSession().getAttribute("access");
	if(username==null){
		response.sendRedirect("index.jsp");
	}
	else
	{
		if(access.length() >6)
		{
			response.sendRedirect("ViewAll.jsp");
		}
	}
	%>
	
<%
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "db";
String userid = "root";
String password = "root";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

<div class="signupform">
	<div class="containers">
	
	<form action="logout" method="post">
			<p><input class="b" type="submit" style="float:right;background-color:#dc0000; font-size:16px; color:#fbfbfb;font-weight: bold;" value="Logout"/></p>
		</form>
		<div class="agile_info">
			<div class="w3land_form">
				<div class="left_grid_info">
					<img src="<c:url value='/assets/img/profile.png' />" alt="" height="200" width="180" style="margin-top:80px; max-width:100%;height:auto;" />
					<font size="5vw"><h2 style="margin-top:25px; font-family:Palatino Linotype;"> ${name}  <br> <span style="color:green;">  (${access})</h2>
					<h4 style="margin-top:7px; font-family:Palatino Linotype;">ID : ${id }</h4></font>
					<p><input class="a" style="margin-top:70px;"  type="submit" onclick="location.href='Candidate.jsp'" value="New Candidate"/></p>
					<p><input class="a" type="submit" style="background-color: #3BABD1;" onclick="location.href='View.jsp'" value="Update Candidate"/></p>
					<p><input class="a" type="submit" onclick="location.href='ViewAll.jsp'" value="View All Candidates"/></p>
				</div>
			</div>
			
			<div class="w3_infos">
				<em id="hide" style="margin-top:80px;">
				<span style=" margin-left:5px; margin-bottom:10px; font-size:20px; border: none; color:red; background-color: #f5f5f5; text-align: left;" id="demo"></span></em>
				<table style="margin-top:15px;">
				<thead>
				<tr>
					<th>Employee ID</th>
					<th>Employee Name</th>
					<th><center>Access</center></th> 
					</tr></thead>
				<%
					try{
					connection = DriverManager.getConnection(connectionUrl+database, userid, password);
					statement=connection.createStatement();
					String sql ="select emp_id,emp_name,emp_access from employee_details";
					resultSet = statement.executeQuery(sql);
					while(resultSet.next()){
					%>
					<tbody id="myTable">
					<tr>
					<form action="access" method="post" autocomplete="off">
						<td><%=resultSet.getInt("emp_id") %></td>
						<td><%=resultSet.getString("emp_name") %></td>
						<td><center><input type="checkbox" id="myAccess" name="myAccess" value="Granted" <%=(resultSet.getInt("emp_access")==1)?"checked":""%>></center></td>
						<td style="border: none;  background-color: #f5f5f5; width:60px;"><input class="b" type="submit" id="submit" name="submit" style="color: transparent; " value=<%=resultSet.getInt("emp_id") %>><span style="position:relative;left: 30px; top:-26px;">Save</span></td>
						</form>
						
					</tr>
					<%
					}
					System.out.println();
					connection.close();
					} catch (Exception e) {
					e.printStackTrace();
					}
				%>
				
				 <input id="springMsg" type="hidden" data-msg="${message}"/>

				 
				</tbody>
				</table>
		</div>
	</div>
</div>
	
</body>
</html>