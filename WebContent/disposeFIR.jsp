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
		
		
		
		//Submit the data to the DisposeFIR servlet
		$("#disposeFormDiv").submit(function(e) {
			$("#disposeForm").validate();
			
			var postData = $("#disposeForm").serializeArray();
			//alert (dataString);return false;
			$.ajax({
			    type: "POST",
			    url: "DisposeFIR",
			    data: postData,
			    success:function(html) 
	            {
			    	$("#addFormMessage").text("Dispose Successfull");
			    	$("#result").show().delay(2000).fadeOut(function(){
			    		$("#disposeForm").trigger("reset");	
			    	});
	            },
	            error: function(html) 
	            {
	            	$("#addFormMessage").text("Dispose Failed");
			    	$("#result").show().delay(2000).fadeOut(function(){
			    		$("#disposeForm").trigger("reset");	
			    	});
	            }
			 });
			 e.preventDefault();
		});
		
		$("#btnReset").click(function() {
			$("#disposeForm").trigger("reset");
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
				
				<li><a href="crimereview.html" >CRIME REVIEW</a></li>
				<li><a href="reports.html" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
		<div id="disposeFormDiv">
			<form action="" id="disposeForm" method="post">
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
							<td><div id="caseNosDiv"><select><option>Please select a Case No.</option></select></div></td>
						</tr>
						<tr>
							<td> Date of Dispose</td>
							<td><input type="date" name="dateOfDispose" required/></td>
						</tr>
						<tr>
							<td> Type of Final Form </td>
							<td><input type="radio" name="finalForm" value="true" checked="checked"/>Charge-Sheet
								<input type="radio" name="finalForm" value="false"/>Not Charge-Sheet
							</td>
						</tr>
						<tr>
							<td><input type="reset" id="btnReset" value="RESET" /></td>
							<td><input type="submit" id="disposeFormSubmit" value="DISPOSE FIR" /></td>	
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
		Copyright © 2016 Bittu & Mahesh Co.
		</div><!-- #footer -->
		
	</div><!-- #container -->
</body>
</html>