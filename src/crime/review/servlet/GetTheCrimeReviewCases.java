package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIR;
import crime.review.database.FIRQueries;
import crime.review.database.PoliceOfficer;
import crime.review.database.PoliceOfficerQueries;

/**
 * Servlet implementation class GetTheCrimeReviewCases
 */
@WebServlet("/GetTheCrimeReviewCases")
public class GetTheCrimeReviewCases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheCrimeReviewCases() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //Creating a table using DIV object instead of the table
		String tblHead = "<div class='table'>"+
							"<div class='tr'>"+
								"<span class='td'><textarea class='Box' rows='1' cols='2'>#</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='10'>Case No.</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='30'>Section of Law</textarea></span>" +
								"<span class='td'><textarea class='Box' rows='1' cols='15'>I/O</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='20'>Action taken by IO</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='20'>Last CD with date</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='20'>Reason for Pending</textarea></span>"+
								"<span class='td'><textarea class='Box' rows='1' cols='25'>SPs Comments</textarea></span>"+
							"</div>";
		
		int policeStationId = Integer.parseInt(request.getParameter("policeStationId"));
		FIRQueries firq = new FIRQueries();
		List<FIR> listOfPendingFIRs = firq.getPendingFIROfPoliceStation(policeStationId);
		firq.close();
		
		
		String tblRows="";
		PoliceOfficerQueries psq = new PoliceOfficerQueries();
		
		
		for(int i = 0; i < listOfPendingFIRs.size() ; i++ ){
			FIR fir = listOfPendingFIRs.get(i);
			PoliceOfficer po = psq.getPoliceOfficer_from_id(fir.getPolice_officer_id());
			tblRows += "<div class='tr'><form method='post' class='crimeReviewForm' id='"+fir.getFir_id()+"'>";
			tblRows	+= "<span class='td'><textarea class='Box' rows='2' cols='2'>"+ (i+1)+"</textarea></span>";
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='10'>" + fir.getCase_no()+ "</textarea></span>";
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='30'>" + fir.getSection_of_law() + "</textarea></span>";			
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='15'>" + po.getFname() + " " + po.getLname() + "</textarea></span>";
			tblRows += "<span class='td'><textarea id='actionTakenByIO' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea id='lastCdWithDate' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea id='reasonForPending' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea id='spRemarks' rows='2' cols='25'></textarea></span>";
			tblRows += "<span class='td'><input type='submit' id='btnUpdate' value='Update Review'/></span></form></div>";
		}
		psq.close();
		
		//creating the complete table containing pending FIRs of Police Stations
		String tablePS = tblHead + tblRows +"</div>";
				
		response.setContentType("text/html");
		response.getWriter().println(tablePS);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
