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
		$("#editFormSubmit").click(function(e){
			if($("#editForm").valid()){
				e.preventDefault();
				$policeStationId = $("#policeStation").val();
				$caseNo = $("#caseNo").val();
				window.location.href="EditFIRForm?policeStationId=" + $policeStationId + "&caseNo="+ $caseNo;
			}
		});
		
		$("#btnReset").click(function() {
			$("#editForm").trigger("reset");
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
						<li><a href="editFIR.jsp" >EDIT FIR</a></li>
						<li><a href="reEndorse.jsp" >RE-ENDORSE FIR</a></li>
						<li><a href="disposeFIR.jsp" >DISPOSE FIR</a></li>
					</ul>
				</li>
				
				<li><a href="crimeReview.jsp" >CRIME REVIEW</a></li>
				<li><a href="reports.html" >REPORTS</a></li>
			</ul>
		</nav>		
		</div><!-- #header -->
		
		<div id="mainDiv">
		<div id="editFormDiv">
			<form action="" id="editForm" method="post">
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
					<td><input type="reset" id="btnReset" value="RESET" /></td>
					<td><input type="submit" id="editFormSubmit" value="GET FIR" /></td>	
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