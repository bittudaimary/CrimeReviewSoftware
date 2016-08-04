package crime.review.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.District;
import crime.review.database.DistrictQueries;
import crime.review.database.FIRQueries;

/**
 * Servlet implementation class PoliceStationServlet
 */
@WebServlet("/district")
public class DistrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistrictServlet() {
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
		DistrictQueries dq = new DistrictQueries();
		List<District> list_d = dq.getAllDistrict();
		dq.close();
			
		out.println("<div id='mainDiv'><table id='mainTable'> " +
				"<h1>Pending Cases of Meghalaya Police</h1>" +
				"<thead><tr><td><h1>Sl. No</h1></td>" +
				"<td><h1>District</h1></td><td><h1>No. of Pending Cases</h1>" +
				"</td></tr></thead><tbody>");
		for(int i = 0 ; i < list_d.size() ; i++) {
			District d = list_d.get(i);
			firq = new FIRQueries();
			count = firq.get_count_of_pending_fir(d.getId());
			firq.close();
			out.println(String.valueOf("<tr class='district'><td>"));
			out.println(String.valueOf(d.getId()));
			out.println(String.valueOf("</td>	<td><a href='#' id='"));
			out.println(String.valueOf(d.getId() + "'>")); 
			out.println(String.valueOf(d.getName()));
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
