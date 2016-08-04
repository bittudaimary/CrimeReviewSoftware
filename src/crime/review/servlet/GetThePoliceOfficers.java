package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.PoliceOfficer;
import crime.review.database.PoliceOfficerQueries;

/**
 * Servlet implementation class GetThePoliceOfficers
 */
@WebServlet("/GetThePoliceOfficers")
public class GetThePoliceOfficers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetThePoliceOfficers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PoliceOfficerQueries poq = new PoliceOfficerQueries();
		List<PoliceOfficer> listOfPoliceOfficers = poq.getAllPoliceOfficers();
				
		
		String polOffOptions = "<select id='policeOfficers' name='policeOfficer'><option>Please select a Investigating Officer</option>";
			
		for(int i = 0 ; i < listOfPoliceOfficers.size(); i++) {
			PoliceOfficer po = listOfPoliceOfficers.get(i);
			polOffOptions += "<option value='"+po.getId()+"'>"+po.getFname()+ " "+ po.getMname() +" "+ po.getLname()+"</option>";
		}
		polOffOptions+= "</select>";
		poq.close();

		response.setContentType("text/html");
		response.getWriter().println(polOffOptions);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
