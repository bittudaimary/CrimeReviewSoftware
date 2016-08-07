package crime.review.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.ClassofOffence;
import crime.review.database.ClassofOffenceQueries;
import crime.review.database.District;
import crime.review.database.DistrictQueries;
import crime.review.database.FIR;
import crime.review.database.FIRQueries;
import crime.review.database.MajorHead;
import crime.review.database.MajorHeadQueries;
import crime.review.database.MinorHead;
import crime.review.database.MinorHeadQueries;
import crime.review.database.PoliceOfficer;
import crime.review.database.PoliceOfficerQueries;
import crime.review.database.PoliceStation;
import crime.review.database.PoliceStationQueries;

/**
 * Servlet implementation class EditFIRForm
 */
@WebServlet("/EditFIRForm")
public class EditFIRForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFIRForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sr="",nonSr="";
		Boolean srOrNonSr=false;
		
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
		
		//setting parameter of request
		request.setAttribute("firId", editFIR.getFir_id());;
		request.setAttribute("districtId", d.getId());
		request.setAttribute("districtName", d.getName());
		request.setAttribute("policeStationId", ps.getId());
		request.setAttribute("policeStationName", ps.getName());
		request.setAttribute("caseNo", editFIR.getCase_no());
		request.setAttribute("sectionOfLaw", editFIR.getSection_of_law());
		request.setAttribute("dateOfRegistration", editFIR.getDate_of_registration());
		request.setAttribute("dateOfOccurence", editFIR.getDate_of_occurrence());
		request.setAttribute("placeOfOccurence", editFIR.getPlace_of_occurence());
		request.setAttribute("policeOfficerId", editFIR.getPolice_officer_id());
		request.setAttribute("majorHeadId", editFIR.getMajor_head_id());
		request.setAttribute("minorHeadId", editFIR.getMinor_head_id());
		request.setAttribute("classOfOffence", editFIR.getClass_of_offence());
		
		srOrNonSr = editFIR.getSr_or_nonsr();
		if(srOrNonSr){
			sr="checked";
		}else{
			nonSr="checked";
		}
		request.setAttribute("sr", sr);
		request.setAttribute("nonSr", nonSr);
		
		RequestDispatcher rs = request.getRequestDispatcher("editFIRForm.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
