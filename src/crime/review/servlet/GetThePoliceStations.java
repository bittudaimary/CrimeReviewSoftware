package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.PoliceStation;
import crime.review.database.PoliceStationQueries;

/**
 * Servlet implementation class GetThePoliceStations
 */
@WebServlet("/GetThePoliceStations")
public class GetThePoliceStations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetThePoliceStations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int districtId = Integer.parseInt(request.getParameter("districtId"));

		PoliceStationQueries psq = new PoliceStationQueries();
		List<PoliceStation> listOfPoliceStations = psq.getPolice_station_from_district_id(districtId);
				
		
		String psOptions = "<select id='policeStation' name='policeStation'><option>Pls select a police station</option>";
			
		for(int i = 0 ; i < listOfPoliceStations.size(); i++) {
			PoliceStation ps = listOfPoliceStations.get(i);
			psOptions += "<option value='"+ps.getId()+"'>"+ps.getName()+"</option>";
		}
		psOptions+= "</select>";
		
		response.setContentType("text/html");
		response.getWriter().println(psOptions);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
