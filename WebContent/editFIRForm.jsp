<%@
	page import="crime.review.database.*"
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	int policeStationId = Integer.parseInt(request.getParameter("policeStationId"));
	String caseNo = request.getParameter("caseNo");
	
	PoliceStationQueries psq = new PoliceStationQueries();
	PoliceStation ps = psq.getPolice_station_from_id(policeStationId);
	psq.close();
	
	DistrictQueries dq = new DistrictQueries();
	District d = dq.getDistrict_from_id(ps.getDistrict_id());
	dq.close();
		
	FIRQueries firq =  new FIRQueries();
	FIR editFIR = firq.getFIRFromPSAndCaseNo(policeStationId,caseNo);
	firq.close();
	
	PoliceOfficerQueries poq = new PoliceOfficerQueries();
	PoliceOfficer po = poq.getPoliceOfficer_from_id(editFIR.getPolice_officer_id());
	poq.close();
	
	MajorHeadQueries majorhq = new MajorHeadQueries();
	MajorHead majorHead = majorhq.getMajorHead_from_id(editFIR.getMajor_head_id());
	majorhq.close();
	
	MinorHeadQueries minorhq = new MinorHeadQueries();
	MinorHead minorHead = minorhq.getMinorHead_from_id(editFIR.getMinor_head_id());
	minorhq.close();
	
	ClassofOffenceQueries coq = new ClassofOffenceQueries();
	ClassofOffence co = coq.getClassofOffence_from_id(editFIR.getClass_of_offence());
	coq.close();
	
	Boolean srNonSr = editFIR.getSr_or_nonsr();
%>
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
		//Load the Major Heads 
		$("#majorHeadsDiv").load('GetTheMajorHeads');
		
		//Load the Minor 
		$("#minorHeadsDiv").load('GetTheMinorHeads');
		
		//Load the classifications of Offence
		$("#classOfOffenceDiv").load('GetTheClassOfOffence');
		
		//Load the Police Officers
		$("#policeOfficersDiv").load('GetThePoliceOfficers');		
			
		//Submit the data to the AddFIR servlet
		$("#editFormDiv").submit(function() {
			$("#editForm").validate();
			
			var postData = $("#editForm").serializeArray();
			//alert (dataString);return false;
			$.ajax({
			    type: "POST",
			    url: "EditFIR",
			    data: postData,
			    success:function(html) 
	            {
			    	//alert(html);
			    	window.location.href = "editFIR.jsp";
	            },
	            error: function(html) 
	            {
	                $("#addFormMessage").html(html);
	            }
			 });
			e.preventDefault();
		});
		
		$("#btnReset").click(function() {
			$("#editForm").trigger("reset");
		});
	});
	$(document).on("DOMNodeInserted",function(e){
		if($(e.target).is('#policeOfficers')){
			$("#policeOfficers").val(<% out.println(po.getId());%>);
		}
		if($(e.target).is('#majorHead')){
			$("#majorHead").val(<% out.println(majorHead.getId());%>);
		}
		if($(e.target).is('#minorHead')){
			$("#minorHead").val(<% out.println(minorHead.getId());%>);
		}
		if($(e.target).is('#classOfOffences')){
			$("#classOfOffences").val(<% out.println(co.getId());%>);
		}
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
		<div id="editFormDiv">
			<form action="" id="editForm" method="post" %>>
    				 	<table id ="mainTable">
    				 	<tr style="display:none;">
    				 		<td>FIR ID</td>
    				 		<td><input type="hidden" name="firId" value=<% out.println(editFIR.getFir_id()); %>></td>
    				 	</tr>
						<tr>
							<td> District:</td>
							<td> <div id="districtsDiv"><select id='districts' name='district' disabled><option value=<%out.println(d.getId());%>><%out.println(d.getName()); %><option></select></div></td>
						</tr>
						<tr>
							<td> Police Station:</td>
							<td>
							<input type="text" id="policeStationId" name="policeStationId" value=<% out.println(ps.getId()); %> hidden>
							<input type="text" id="policeStation" name="policeStation" value=<% out.println(ps.getName()); %> readonly>
							</td>
						</tr>
						<tr>
							<td> FIR No:</td>
							<td><input type="text" name="firNo" value=<% out.println(editFIR.getCase_no()); %> readonly></td>
						</tr>
						<tr>
							<td> Section of Law:</td>
							<td><textarea name="sectionOfLaw" required><% out.println(editFIR.getSection_of_law()); %></textarea></td>
						</tr>
						<tr>
							<td> Date of Registration:</td>
							<td><input type="date" name="dateOfRegistration" required value=<% out.println(editFIR.getDate_of_registration()); %>/></td>
						</tr>
						<tr>
							<td> Date of Occurence:</td>
							<td><input type="date" name="dateOfOccurence" required value=<% out.println(editFIR.getDate_of_occurrence()); %>/></td>
						</tr>
						<tr>
							<td> Place of Occurence:</td>
							<td><input type="text" name="placeOfOccurence" required value=<% out.println(editFIR.getPlace_of_occurence()); %>/></td>
						</tr>
						<tr>
							<td> Investigating Officer: </td>
							<td> <div id="policeOfficersDiv"><select id='policeOfficers' name='policeOfficer'><option></option></select></div></td>
						</tr>
						
						<tr>
							<td> SR or NON-SR: </td>
							<td>
								<input type="radio" name="srOrNonSR" value="false" <% if(!srNonSr) { out.println("checked"); } %>/>Non_SR 
								<input type="radio" name="srOrNonSR" value="true" <% if(srNonSr){ out.println("checked"); }%>/>SR
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