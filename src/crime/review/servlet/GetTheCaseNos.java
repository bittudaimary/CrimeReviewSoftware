package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIRQueries;

/**
 * Servlet implementation class GetTheCaseNos
 */
@WebServlet("/GetTheCaseNos")
public class GetTheCaseNos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheCaseNos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int policeStationId = Integer.parseInt(request.getParameter("policeStationId"));

		FIRQueries firq = new FIRQueries();
		List<String> listOfCaseNos = firq.getCaseNosofPoliceStation(policeStationId);
		firq.close();
				
		
		String psOptions = "<select id='caseNo' name='caseNo' required><option value=''>Pls select a Case Nos</option>";
			
		for(int i = 0 ; i < listOfCaseNos.size(); i++) {
			String caseNo = listOfCaseNos.get(i);
			psOptions += "<option value='"+caseNo+"'>"+caseNo+"</option>";
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
