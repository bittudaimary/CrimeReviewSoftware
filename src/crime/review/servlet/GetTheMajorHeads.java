package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.MajorHead;
import crime.review.database.MajorHeadQueries;


/**
 * Servlet implementation class GetTheMajorHeads
 */
@WebServlet("/GetTheMajorHeads")
public class GetTheMajorHeads extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheMajorHeads() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MajorHeadQueries mhq = new MajorHeadQueries();
		List<MajorHead> listOfMajorHeads = mhq.getAllMajorHead();
		mhq.close();	
		
		String mhOptions = "<select id='majorHead' name='majorHead' required><option value=''>Please select a Major Head</option>";
			
		for(int i = 0 ; i < listOfMajorHeads.size(); i++) {
			MajorHead mh = listOfMajorHeads.get(i);
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
