package crime.review.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIR;
import crime.review.database.FIRQueries;

/**
 * Servlet implementation class EditFIR
 */
@WebServlet("/EditFIR")
public class EditFIR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFIR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firId;	
		String firNo;
		 String sectionOfLaw;
		 String dateOfRegistration;
		 String dateOfOccurence;
		 int policeOfficerId;
		 int policeStationId;
		 String placeOfOccurence;
		 int majorHeadId;
		 int minorHeadId;
		 Boolean srOrNonSR;
		 int classOfOffence;
		 
		 FIR fir = new FIR();
		 Boolean success = false;

		 
		 //Getting the parameters from the request objects
		 firId = Integer.parseInt(request.getParameter("firId"));
		 policeStationId = Integer.parseInt(request.getParameter("policeStationId"));
		 firNo = request.getParameter("firNo");
		 sectionOfLaw = request.getParameter("sectionOfLaw");
		 dateOfRegistration = request.getParameter("dateOfRegistration");
		 dateOfOccurence=request.getParameter("dateOfOccurence"); 
		 policeOfficerId = Integer.parseInt(request.getParameter("policeOfficer"));
		 placeOfOccurence = request.getParameter("placeOfOccurence");
		 majorHeadId = Integer.parseInt(request.getParameter("majorHead"));
		 minorHeadId = Integer.parseInt(request.getParameter("minorHead"));
		 classOfOffence = Integer.parseInt (request.getParameter("classOfOffence"));
		 srOrNonSR =  Boolean.parseBoolean(request.getParameter("srOrNonSR"));
		
		 
		 //Setting the FIR Object
		 fir.setFir_id(firId);
		 fir.setPolice_station_id(policeStationId);
		 fir.setCase_no(firNo);
		 fir.setSection_of_law(sectionOfLaw);
		 fir.setDate_of_registration(dateOfRegistration);
		 fir.setDate_of_occurrence(dateOfOccurence);
		 fir.setPlace_of_occurence(placeOfOccurence);
		 fir.setPolice_officer_id(policeOfficerId);
		 fir.setMajor_head_id(majorHeadId);
		 fir.setMinor_head_id(minorHeadId);
		 fir.setClass_of_offence(classOfOffence);
		 fir.setSr_or_nonsr(srOrNonSR);

		 //Edit the FIR object to the database
		 
		 try{
			 FIRQueries firQr = new FIRQueries();
			 success = firQr.update(fir);
			 firQr.close();
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		 
		 response.setContentType("text/html");
		 response.getOutputStream().println(success);
	}

}
