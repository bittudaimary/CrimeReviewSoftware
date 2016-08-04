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
 * Servlet implementation class AddFIR
 */
@WebServlet(description = "Adds an FIR Object to the database", urlPatterns = { "/AddFIR" })
public class AddFIR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFIR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
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
		 FIRQueries firQr = new FIRQueries();
		 Boolean success = false;

		 
		 //Getting the parameters from the request objects
		 policeStationId = Integer.parseInt(request.getParameter("policeStation"));
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

		 
		 //Add the FIR object to the database
		 
		 try{
			 success = firQr.add(fir);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		 
		 response.setContentType("text/html");
		 response.getOutputStream().println(success);
	}

}
