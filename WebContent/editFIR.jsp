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
		//Load the district
		$("#districtsDiv").load('GetTheDistricts');
		
		//Load the police stations on the selection of a district in the change event of the dropdownlist
		$("#districtsDiv").on("change","#districts", function(e){
				$districtId = $(this).val();
		    	$("#policeStationsDiv").load('GetThePoliceStations','districtId=' + $districtId );
		});
		
		//Load the case nos on the selection of a police station in the change of the dropdownlist 
		$("#policeStationsDiv").on("change","#policeStation", function(e){
				$policeStationId = $(this).val();
				$("#caseNosDiv").load('GetTheCaseNos','policeStationId=' + $policeStationId );
		});
		
		//Load the case nos on the selection of a police station in the change of the dropdownlist 
		$("#caseNosDiv").on("change","#caseNo", function(e){
			window.location.href = "editFIRForm.jsp?policeStationId=" + $policeStationId + "&caseNo=" + $(this).val();	
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
			<div id="editFIR">
				Please select a district<div id="districtsDiv"><select><option>Please select a District</option></select></div>
			<br>
				Please select a police station<div id="policeStationsDiv"><select><option>Please select a PS</option></select></div>
			<br>
				Please select a Case No<div id="caseNosDiv"><select><option>Please select a Case No.</option></select></div>
			</div>
			<div id="editFormDiv"></div>
		</div><!-- #content -->
		
		<div id="footer">
		Copyright © 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>