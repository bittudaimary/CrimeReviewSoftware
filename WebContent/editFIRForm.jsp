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
	$(document).ready(function(e) {
		//HIDE THE RESULT
		$("#result").hide();
		
		//Load the Major Heads 
		$("#majorHeadsDiv").load('GetTheMajorHeads',function(){
			$("#majorHead").val('${majorHeadId}');
		});
		
		//Load the Minor 
		$("#minorHeadsDiv").load('GetTheMinorHeads',function(){
			$("#minorHead").val('${minorHeadId}');
		});
		
		//Load the classifications of Offence
		$("#classOfOffenceDiv").load('GetTheClassOfOffence',function(){
			$("#classOfOffences").val('${classOfOffence}');
		});
		
		//Load the Police Officers
		$("#policeOfficersDiv").load('GetThePoliceOfficers',function(){
			$("#policeOfficers").val('${policeOfficerId}');
		});		
		
		//Submit the data to the AddFIR servlet
		$("#editFormDiv").submit(function(e) {
			$("#editForm").validate();
			var postData = $("#editForm").serializeArray();
			//alert (dataString);return false;
			$.ajax({
			    type: "POST",
			    url: "EditFIR",
			    data: postData,
			    success:function(html) 
	            {
			    	$("#addFormMessage").text("Edited Successfully");
			    	$("#result").show().delay(2000).fadeOut(function(){
			    		 $('body').fadeOut(200, function(){
			    			 window.location.href="editFIR.jsp";
			    	     });
			    	});
	            },
	            error: function(html) 
	            {
	            	$("#addFormMessage").text("Edit Failed");
			    	$("#result").show().delay(2000).fadeOut(function(){
			    		$("#district").focus();
			    	});
	            }
			 });
			e.preventDefault();
		});
		
		$("#btnReset").click(function() {
			$("#editForm").trigger("reset");
			$("#district").focus();
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
						<li><a href="reEndorse.jsp" >RE-ENDORSE FIR</a></li>
						<li><a href="disposeFIR.jsp" >DISPOSE FIR</a></li>
					</ul>
				</li>
				
				<li><a href="crimereview.html" >CRIME REVIEW</a></li>
				<li><a href="reports.html" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		<div id="mainDiv">
		<div id="editFormDiv">
			<form action="" id="editForm" method="post">
    				 	<table id ="mainTable">
    				 	<tr style="display:none;">
    				 		<td>FIR ID</td>
    				 		<td><input type="hidden" name="firId" value=<% out.println(request.getAttribute("firId"));  %>></td>
    				 	</tr>
						<tr>
							<td> District:</td>
							<td> <div id="districtsDiv"><select id='districts' name='district' disabled><option value=<% out.println(request.getAttribute("districtId")); %>><% out.println(request.getAttribute("districtName")); %><option></select></div></td>
						</tr>
						<tr>
							<td> Police Station:</td>
							<td>
							<input type="text" id="policeStationId" name="policeStationId" value=<% out.println(request.getAttribute("policeStationId")); %> hidden>
							<input type="text" id="policeStation" name="policeStation" value=<% out.println(request.getAttribute("policeStationName")); %> readonly>
							</td>
						</tr>
						<tr>
							<td> FIR No:</td>
							<td><input type="text" name="firNo" value=<% out.println(request.getAttribute("caseNo")); %> required></td>
						</tr>
						<tr>
							<td> Section of Law:</td>
							<td><textarea name="sectionOfLaw" required><% out.println(request.getAttribute("sectionOfLaw")); %></textarea></td>
						</tr>
						<tr>
							<td> Date of Registration:</td>
							<td><input type="date" name="dateOfRegistration" required value=<% out.println(request.getAttribute("dateOfRegistration")); %>/></td>
						</tr>
						<tr>
							<td> Date of Occurence:</td>
							<td><input type="date" name="dateOfOccurence" required value=<% out.println(request.getAttribute("dateOfOccurence")); %>/></td>
						</tr>
						<tr>
							<td> Place of Occurence:</td>
							<td><input type="text" name="placeOfOccurence" required value=<% out.println(request.getAttribute("placeOfOccurence")); %>/></td>
						</tr>
						<tr>
							<td> Investigating Officer: </td>
							<td> <div id="policeOfficersDiv"><select id='policeOfficers' name='policeOfficer'><option></option></select></div></td>
						</tr>
						
						<tr>
							<td> SR or NON-SR: </td>
							<td>
								<input type="radio" name="srOrNonSR" value="false" <% out.println(request.getAttribute("nonSr")); %>/>Non_SR 
								<input type="radio" name="srOrNonSR" value="true" <% out.println(request.getAttribute("sr")); %>/>SR
							</td>
						</tr>
						
						<tr>
							<td> Major Head:</td>
							<td> <div id="majorHeadsDiv"><select id='majorHead' name='majorHead'><option></option></select></div></td>
						</tr>
						<tr>
							<td> Minor Head:</td>
							<td> <div id="minorHeadsDiv"><select id='minorHead' name='minorHead'><option></option></select></div></td>
						</tr>
						<tr>
							<td> Classification of Offence:</td>
							<td> <div id="classOfOffenceDiv"><select id='classOfOffences' name='classOfOffence'><option></option></select></div></td>
						</tr>
						<tr>
							<td><input type="reset" id="btnReset" value="RESET" /></td>
							<td><input type="submit" id="editFormSubmit" value="EDIT FIR" /></td>
						</tr>						
						<tr id="result">
							<td> Result</td>
							<td>  <div id="addFormMessage"></div></td>
						</tr>
				 </table>
			</form>
			 <div id="addFormMessage"></div>
			</div>
		
		</div><!-- #content -->
		
		<div id="footer">
		Copyright © 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>