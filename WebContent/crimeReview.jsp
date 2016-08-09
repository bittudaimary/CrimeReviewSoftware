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
				$districtId = $(this).val();
		    	$("#policeStationsDiv").load('GetThePoliceStations','districtId=' + $districtId );
		});
				
		$("#policeStationsDiv").on("change","#policeStation",function(e){
	    	 $policeStationId = $(this).val();
	    	 $("#crimeReviewDiv").load('GetTheCrimeReviewCases','policeStationId=' + $policeStationId );
		});
		
		$("#crimeReviewDiv").on("change","#policeStation",function(e){
	    	 $policeStationId = $(this).val();
	       	 $("#crimeReviewDiv").load('GetTheCrimeReviewCases','policeStationId=' + $policeStationId );
		});
		
		$("#crimeReviewDiv").on("submit",".crimeReviewForm",function(e){
			var postData = $(this).serializeArray();
			$.ajax({
			    type: "POST",
			    url: "AddCrimeReview",
			    data: postData,
			    success:function(html) 
	            {
			    	alert(html);
	            },
	            error: function(html) 
	            {
	            	alert(html);
	            }
			 });
			 e.preventDefault();
		});
		
		$("#crimeReviewDiv").on("click",".paginationLinks",function(e){
			var pageNo = $(this).attr('value');
			var policeStationId =$(this).attr('id');
			
			$.ajax({
			    type: "GET",
			    url: "GetPaginatedPendingCases?pageNo="+pageNo+"&policeStationId="+policeStationId,
			    success:function(html) 
	            {
			    	alert(html);
	            },
	            error: function(html) 
	            {
	            	alert(html);
	            }
			 });
			 e.preventDefault();
			return false;
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
					<li><a href="reports.jsp" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
				<center><h2>Crime Review</h2> </center>
				<span>Please select a district</span>
				<div id="districtsDiv">
					<select>
						<option>Please select a District</option>
					</select>
				</div>
				<br>
				<span>Please select a police station</span>
				<div id="policeStationsDiv">
					<select>
						<option>Please select a Police Station</option>
					</select>
				</div>
				<div id="crimeReviewDiv">
				
				</div>
		</div><!-- #content -->
		
		<div id="footer">
		Copyright © 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>