package crime.review.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIRQueries;
import crime.review.database.PoliceStation;
import crime.review.database.PoliceStationQueries;

/**
 * Servlet implementation class PoliceStationServlet
 */
@WebServlet("/police_station")
public class PoliceStationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PoliceStationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//REQUEST FOR POLICE STATIOIN AND PENDING CASES OF A PARTICULAR DISTRICT
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int count = 0;
		FIRQueries firq = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		PoliceStationQueries psq = new PoliceStationQueries();
		List<PoliceStation> list_ps = psq.getPolice_station_from_district_id(Integer.parseInt(request.getParameter("district_id")));
		psq.close();
		
		out.println("<div id='mainDiv'><table id='mainTable'> " +
				"<h1>Pending Cases of " + request.getParameter("district_name") + "</h1>" +
				"<thead><tr><td><h1>Sl. No</h1></td>" +
				"<td><h1>Police Station</h1></td><td><h1>No. of Pending Cases</h1>" +
				"</td></tr></thead><tbody>");
		for(int i = 0 ; i < list_ps.size() ; i++) {
			PoliceStation ps = list_ps.get(i);
			firq = new FIRQueries();
			count = firq.get_count_of_pending_fir_from_ps(ps.getId());
			firq.close();
			out.println(String.valueOf("<tr class='ps'><td>"));
			out.println(String.valueOf(i+1));
			out.println(String.valueOf("</td><td><a href='#' id='"));
			out.println(String.valueOf(ps.getId() + "'>")); 
			out.println(String.valueOf(ps.getName()));
			out.println(String.valueOf("</a></td><td>"));
			out.println(String.valueOf(count));
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
