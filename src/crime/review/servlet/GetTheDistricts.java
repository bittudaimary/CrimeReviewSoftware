package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.District;
import crime.review.database.DistrictQueries;

/**
 * Servlet implementation class GetTheDistricts
 */
@WebServlet("/GetTheDistricts")
public class GetTheDistricts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheDistricts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DistrictQueries dq = new DistrictQueries();
		List<District> listOfAllDistricts = dq.getAllDistrict();
		dq.close();
		
		String districtOptions = "<select id='districts' name='district' required><option value=''>Please select a District</option>";
			
		for(int i = 0 ; i < listOfAllDistricts.size(); i++) {
			District d = listOfAllDistricts.get(i);
			districtOptions += "<option value='"+d.getId()+"'>"+d.getName()+"</option>";
		}
		districtOptions+= "</select>";
		dq.close();
		
		response.setContentType("text/html");
		response.getWriter().println(districtOptions);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
