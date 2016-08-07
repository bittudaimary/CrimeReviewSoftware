<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crime Review Software</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type = "text/javascript" src = "jquery.min.js"></script>
<script type = "text/javascript">
$(document).ready(function() {
	$("#mainDiv").load('district');
    $("#mainDiv").on("click",".district a", function(e){
    	$("#mainDiv").load('police_station','district_id=' + $(this).attr("id") + '&district_name=' + $(this).text());
	});
    
    $("#mainDiv").on("click",".ps a", function(e){
    	$("#mainDiv").load('pendingfir','ps_id=' + $(this).attr("id") + '&ps_name=' + $(this).text());
	});
    
    $("#home").click(function(){
    	$("#mainDiv").load('district');
    });
 });
</script> 	
</head>
<body>
	<div id="container">
		
		<div id="header">
		<div id="logo">
			<img src="head.jpg" alt="Your browser does not support this Image"></img>
		</div>
		<nav>
			<ul>
            	<li id="home"><a href="index.jsp">HOME</a></li>
				<li><a href="#">FIR</a>
					<ul>
						<li><a href="addFIR.jsp" >ADD FIR</a></li>
						<li><a href="editFIR.jsp" >EDIT FIR</a></li>
						<li><a href="contact.html" >RE-ENDORSE FIR</a></li>
						<li><a href="disposeFIR.jsp" >DISPOSE FIR</a></li>
					</ul>
				</li>
				
				<li><a href="crimeReview.jsp" >CRIME REVIEW</a></li>
				<li><a href="reports.html" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
		</div><!-- #content -->
		
		<div id="footer">
		Copyright (C) 2016 Bittu & Mahesh Latest
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>