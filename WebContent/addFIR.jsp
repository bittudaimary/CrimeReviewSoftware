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
		//HIDE THE RESULT
		$("#result").hide();
		
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
		
		
		
		//Submit the data to the AddFIR servlet
		$("#addFormDiv").submit(function(e) {
			$("#addForm").validate();
			
			var postData = $("#addForm").serializeArray();
			alert (postData);
			$.ajax({
			    type: "POST",
			    url: "AddFIR",
			    data: postData,
			    success:function(html) 
	            {
			    	$("#addFormMessage").text("FIR Added Successfully");
			    	$("#result").show().delay(2000).fadeOut(function(){
			    		$("#addForm").trigger("reset");	
			    		$("#districts").focus();
			    	});
	            },
	            error: function(html) 
	            {
	            	$("#addFormMessage").text("FIR Add Failed");
	            	$("#result").show().delay(2000).fadeOut(function(){
			    		$("#addForm").trigger("reset");	
			    		$("#districts").focus();
			    	});	
	            }
			 });
			 e.preventDefault();
		});
		
		$("#btnReset").click(function() {
			$("#addForm").trigger("reset");
			$("#districts").focus();
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
<<<<<<< HEAD
						<li><a href="editFIR.jsp" >EDIT FIR</a></li>
						<li><a href="reEndorse.jsp" >RE-ENDORSE FIR</a></li>
						<li><a href="disposeFIR.jsp" >DISPOSE FIR</a></li>
=======
						<li><a href="contact.html" >EDIT FIR</a></li>
						<li><a href="contact.html" >RE-ENDORSE FIR</a></li>
						<li><a href="contact.html" >DISPOSE FIR</a></li>
>>>>>>> refs/remotes/origin/connect_database
					</ul>
				</li>
				
				<li><a href="crimereview.html" >CRIME REVIEW</a></li>
				<li><a href="reports.jsp" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
		<div id="addFormDiv">
			<form action="" id="addForm" method="post">
    				 	<table id ="mainTable">
						<tr>
							<td> District:</td>
							<td> <div id="districtsDiv"><select name='district'><option>Please select a District<option></select></div></td>
						</tr>
						<tr>
							<td> Police Station:</td>
							<td><div id="policeStationsDiv"><select name='policeStation'><option>Please select a PS<option></select></div></td>
						</tr>

						<tr>
							<td> FIR No:</td>
							<td><input type="text" name="firNo" placeholder="eg. 12(03)15" required/></td>
						</tr>
						<tr>
							<td> Section of Law:</td>
							<td><textarea name="sectionOfLaw" placeholder="eg. 302/34 IPC" required  cols="60" rows="3"/></textarea></td>
						</tr>
						<tr>
							<td> Date of Registration:</td>
							<td><input type="date" name="dateOfRegistration" required/></td>
						</tr>
						<tr>
							<td> Date of Occurence:</td>
							<td><input type="date" name="dateOfOccurence" required/></td>
						</tr>
						<tr>
							<td> Place of Occurence:</td>
							<td><input type="text" name="placeOfOccurence" placeholder="eg. Tura Bazaar" required/></td>
						</tr>
						<tr>
							<td> Investigating Officer: </td>
							<td> <div id="policeOfficersDiv"><select name='policeOfficer'><option>Please select a I/O</option></select></div></td>
						</tr>
						
						<tr>
							<td> SR or NON-SR: </td>
							<td><input type="radio" name="srOrNonSR" value="false" checked="checked"/>Non_SR 
								<input type="radio" name="srOrNonSR" value="true"/>SR
							</td>
						</tr>
						
						<tr>
							<td> Major Head:</td>
							<td> <div id="majorHeadsDiv"><select name='majorHead'><option>Please select a Major Head</option></select></div></td>
						</tr>
						<tr>
							<td> Minor Head:</td>
							<td> <div id="minorHeadsDiv"><select name='minorHead'><option>Please select a Minor Head</option></select></div></td>
						</tr>
						<tr>
							<td> Classification of Offence:</td>
							<td> <div id="classOfOffenceDiv"><select name='classOfOffences'><option>Please select a Classification</option></select></div></td>
						</tr>
						<tr>
							<td><input type="reset" id="btnReset" value="RESET" /></td>
							<td><input type="submit" id="addFormSubmit" value="ADD FIR" /></td>
							
						</tr>						
						<tr id="result">
							<td> Result</td>
							<td>  <div id="addFormMessage"></div></td>
						</tr>
				 </table>
			</form>
			</div>

		</div><!-- #content -->
		
		<div id="footer">
		Copyright &copy; 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>