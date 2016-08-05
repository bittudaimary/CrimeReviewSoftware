<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crime Review Software</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type = "text/javascript" src = "jquery.min.js"></script>
<script type = "text/javascript" src = "jquery.validate.min.js"></script>
<script type = "text/javascript">
	$(document).ready(function() {
		//Load the districts drop down list
		$("#districtsDiv").load('GetTheDistricts');
		
		//Load the Major Heads 
		$("#majorHeadsDiv").load('GetTheMajorHeads');
		
		//Load the Minor Heads
		$("#minorHeadsDiv").load('GetTheMinorHeads');
		
		//Load the Police Officers
		$("#policeOfficersDiv").load('GetThePoliceOfficers');
		
		//Load the classifications of Offence
		$("#classOfOffenceDiv").load('GetTheClassOfOffence');
		
		
		//Load the police stations on the selection of a district in the change event of the dropdownlist
		$("#districtsDiv").on("change","#districts", function(e){
				alert("Hello");
				$districtId = $(this).val();
		    	$("#policeStationsDiv").load('GetThePoliceStations','districtId=' + $districtId );
		});
		
		
		$("#policeStaionsDiv").on("change","#policeStation", function(e){
	    	 alert("you selected something");
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
						<li><a href="addFir.jsp" >ADD FIR</a></li>
						<li><a href="contact.html" >EDIT FIR</a></li>
						<li><a href="contact.html" >RE-ENDORSE FIR</a></li>
						<li><a href="contact.html" >DISPOSE FIR</a></li>
					</ul>
				</li>
				
				<li><a href="crimeReview.jsp" >CRIME REVIEW</a></li>
				<li><a href="reports.html" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
			<div id="crimeReview">
				Please select a district<div id="districtsDiv"><select name='district'><option>Please select a District<option></option></select></div>
				<br>
				Please select a police station<div id="policeStationsDiv"><select><option>Please select a PS<option></select></div>
				<div id="crimeReviewDiv"></div>
			</div>
		</div><!-- #content -->
		
		<div id="footer">
		Copyright © 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>