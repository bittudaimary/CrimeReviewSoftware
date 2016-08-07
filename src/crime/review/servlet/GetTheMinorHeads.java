package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.MinorHead;
import crime.review.database.MinorHeadQueries;

/**
 * Servlet implementation class GetTheMinorHeads
 */
@WebServlet("/GetTheMinorHeads")
public class GetTheMinorHeads extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheMinorHeads() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MinorHeadQueries mhq = new MinorHeadQueries();
		List<MinorHead> listOfMinorHeads = mhq.getAllMinorHead();
				
		
		String mhOptions = "<select id='minorHead' name='minorHead' required><option value=''>Please select a Minor Head</option>";
			
		for(int i = 0 ; i < listOfMinorHeads.size(); i++) {
			MinorHead mh = listOfMinorHeads.get(i);
			mhOptions += "<option value='"+mh.getId()+"'>"+mh.getName()+"</option>";
		}
		mhOptions+= "</select>";
		mhq.close();

		response.setContentType("text/html");
		response.getWriter().println(mhOptions);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
