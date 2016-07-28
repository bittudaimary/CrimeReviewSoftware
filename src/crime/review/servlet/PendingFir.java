package crime.review.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIR;
import crime.review.database.FIRQueries;
import crime.review.database.PoliceStation;
import crime.review.database.PoliceStationQueries;

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
		int count = 0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		FIRQueries firq = new FIRQueries();
		List<FIR> list_fir = firq.getPendingFIROfPoliceStation(Integer.parseInt(request.getParameter("ps_id")));
		firq.close();
		out.println("<div id='mainDiv'><table id='mainTable'> " +
				"<h1>Pending Cases of " + request.getParameter("ps_name") + "</h1>" +
				"<thead><tr><td><h1>Sl. No</h1></td>" +
				"<td><h1>Case No.</h1></td><td><h1>Section of Law</h1>" +
				"</td></tr></thead><tbody>");
		for(int i = 0 ; i < list_fir.size() ; i++) {
			FIR fir = list_fir.get(i);
			out.println(String.valueOf("<tr class='ps'><td>"));
			out.println(String.valueOf(fir.getFir_id()));
			out.println(String.valueOf("</td>	<td><a href='#' id='"));
			out.println(String.valueOf(fir.getFir_id() + "'>")); 
			out.println(String.valueOf(fir.getCase_no()));
			out.println(String.valueOf("</a></td><td>"));
			out.println(String.valueOf(fir.getSection_of_law()));
			out.println(String.valueOf("</td></tr>"));
		}
		out.println("</tbody></table></div>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
