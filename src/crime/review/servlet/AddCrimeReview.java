package crime.review.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.CrimeReview;
import crime.review.database.CrimeReviewQueries;

/**
 * Servlet implementation class AddCrimeReview
 */
@WebServlet("/AddCrimeReview")
public class AddCrimeReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCrimeReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 int fir_id;
		 String date_of_review;
		 String action_taken;
		 String last_cd_with_date;
		 String reason_for_pending;
		 String remarks;
		 
		 //Get the parameters		 
		 fir_id = Integer.parseInt(request.getParameter("firId"));
		 date_of_review = getCurrentDate();
		 action_taken = request.getParameter("actionTakenByIO");
		 last_cd_with_date = request.getParameter("lastCdWithDate");
		 reason_for_pending = request.getParameter("reasonForPending");
		 remarks = request.getParameter("spRemarks");
		 
		 //Set the parameters to CrimeReview Object
		 CrimeReview cr = new CrimeReview();
		 cr.setAction_taken(action_taken);
		 cr.setDate_of_review(date_of_review);
		 cr.setFir_id(fir_id);
		 cr.setLast_cd_with_date(last_cd_with_date);
		 cr.setReason_for_pending(reason_for_pending);
		 cr.setRemarks(remarks);
		 
		 //Add the crime review data
		 Boolean success=false;
		 try{
			 CrimeReviewQueries crq =  new CrimeReviewQueries();
			 success = crq.add(cr);
			 crq.close();
		 }
		 catch(Exception e){
	 		System.out.println(e.getMessage());
		}
		response.setContentType("text/html");
		response.getOutputStream().println(success);
	
	}
	
	String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return sdf.format(date);
	}

}
