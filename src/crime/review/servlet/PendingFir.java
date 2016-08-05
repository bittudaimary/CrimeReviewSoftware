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
 * Servlet implementation class PendingFir
 */
@WebServlet("/pendingfir")
public class PendingFir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingFir() {
        super();
        // TODO Auto-generated constructor stub
    }

	//RETURNS ALL THE PENDING FIR WITH RESPECT TO POLICE STATION
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		FIRQueries firq = new FIRQueries();
		List<FIR> list_fir = firq.getPendingFIROfPoliceStation(Integer.parseInt(request.getParameter("ps_id")));
		firq.close();
		
		//creating the header
		String tblHead = "<div id='mainDiv'><table id='mainTable'> " +
								"<h3>Pending Cases of " + request.getParameter("ps_name") + "</h3>" +
									"<thead><tr><td><h3>Sl. No</h3></td>" +
										"<td><h3>Case No.</h3></td><td><h3>Section of Law</h3>" +
											"</td><td><h3>Investigating Officer</h3></td></tr></thead><tbody>";
		
		//creating the rows
		String tblRows = "";
		PoliceOfficerQueries psq = new PoliceOfficerQueries();
		
		for(int i = 0 ; i < list_fir.size() ; i++) {
			FIR fir = list_fir.get(i);
			tblRows += "<tr class='ps'><td>"+ (i+1)+"</td>";
			tblRows += "<td>" + fir.getCase_no()+ "</td>";
			tblRows += "<td> U/S " + fir.getSection_of_law() + "</td>";			
			PoliceOfficer po = psq.getPoliceOfficer_from_id(fir.getPolice_officer_id());
			tblRows += "<td>" + po.getFname() + " " + po.getLname() + "</td></tr>";
		}
		
		psq.close();
				
		//creating the complete table containing pending FIRs of Police Stations
		String tablePS = tblHead + tblRows +"</tbody></table></div>";
		
		response.setContentType("text/html");
		response.getWriter().println(tablePS);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
