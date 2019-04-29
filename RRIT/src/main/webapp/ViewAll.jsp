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
  $("#myInput").change(function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
    
    <script>
    function checkAccess()
    {
    	 var val=$("#access").data("msg");
    	 if(val.length > 6)
    	 {
    		 document.getElementById("view").disabled=true;
    		 document.getElementById("new_candidate").disabled=true;
    	 }
    	 if( document.getElementById("new_candidate").disabled)
    	 {
    		 document.getElementById("new_candidate").title="You don't have the permission to perform this action";
    	 }
    	 if( document.getElementById("view").disabled)
    	 {
    		 document.getElementById("view").title="You don't have the access to perform this action";
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
  width: 150%;
}

td, th{
  border: 1px solid #c7bfbf;
  text-align: left;
  padding: 15px;
}
tbody:nth-child(even) {
  background-color: #ecebeb;
  
}


</style>
<body onload="checkAccess()">
<% String username = (String)request.getSession().getAttribute("name"); 
   String access = (String)request.getSession().getAttribute("access");	
	if(username==null){
		response.sendRedirect("index.jsp");
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
	<div class="containers" >
	<form action="logout" method="post">
			<p><input class="b" type="submit" style="float:right;background-color:#dc0000; font-size:16px; color:#fbfbfb;font-weight: bold;" value="Logout"/></p>
		</form>
		<form action="download" method="post">
			<p><input class="b" type="submit" style="float:right;background-color:#e2e2e2; margin-right:30px; font-size:16px; color:#043304;font-weight: bold;" value="Export to Excel"/></p>
			</form>
		<div class="agile_info" >
			<div class="w3land_form">
			
				<div class="left_grid_info">
					<img src="<c:url value='/assets/img/profile.png' />" alt="" height="200" width="180" style="margin-top:80px; max-width:100%;height:auto;" />
					<font size="5vw"><h2 style="margin-top:25px; font-family:Palatino Linotype;"> ${name}  <br> <span style="color:green;">  (${access})</span></h2>
					<h4 style="margin-top:7px; font-family:Palatino Linotype;">ID : ${id }</h4></font>
					<p><input class="a" style="margin-top:70px;" id="new_candidate" type="submit" onclick="location.href='Candidate.jsp'" value="New Candidate"/></p>
					<p><input class="a" type="submit" id="view" onclick="location.href='View.jsp'"  value="Update Candidate"/></p>
					<p><input class="a" type="submit"  style="background-color: #4CAF50; pointer-events: none;" value="View All Candidates" disabled/></p>
					
				</div>
			</div>
			
			<div class="w3_infos" style="margin-top:50px">
		
			<div style="margin-top:15px; margin-bottom:30px; width:21%;  background-color:#ffffff" class="input-group" >
						<span class="fa fa-sitemap" aria-hidden="true"></span>
    						<select style="padding:15px;color:dark;" name="domain" id="myInput">
							<option value="Cleared">Select</option>
						    <option value="dotnet">Dotnet</option>
						    <option value="java">JAVA</option>
						    </select>
					</div><span style="color:blue;">${message}</span>
		
			<table style="margin-top:20px">
			<tr>
					<th rowspan="3">Candidate ID</th>
					<th rowspan="3">Candidate Name</th>
					<th rowspan="3">Domain</th>
					<th rowspan="3">Experience</th>
					<th colspan="3"><center>Assessments</center></th> 
					</tr>
					<tr>
					<th><center>L1</center></th>
					<th><center>L2</center></th>
					<th><center>HR</center></th>
					</tr>
				<%
					try{
					connection = DriverManager.getConnection(connectionUrl+database, userid, password);
					statement=connection.createStatement();
					String sql ="select * from candidate_details";
					resultSet = statement.executeQuery(sql);
					while(resultSet.next()){
					%>
					<tbody id="myTable">
					<tr>
					<form action="downloadCV"  method="post" autocomplete="off">
					<td id="myCheck2"><%=resultSet.getInt("cand_id") %></td>
					<td><%=resultSet.getString("cand_name") %></td>
					<td><%=resultSet.getString("domain") %></td>
					<td><%=resultSet.getInt("experience") %></td>
					<td><%=resultSet.getString("l1") %></td>
					<td><%=resultSet.getString("l2") %></td>
					<td><%=resultSet.getString("hr") %></td>
					<td style="border: none;  background-color: #f5f5f5;"><input class="b"  type="submit" id="submit" title="Download CV" name="submit" style="color: transparent; " value=<%=resultSet.getInt("cand_id") %>><span class="fa fa-download" style="color: green;position:relative;left: -61px;" aria-hidden="true"></span><span id="mytext"></span></td>
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
				
					<input id="access" type="hidden" data-msg="${access}" />
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>