package crime.review.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.Dispose;
import crime.review.database.DisposeQueries;
import crime.review.database.FIRQueries;

/**
 * Servlet implementation class DisposeFIR
 */
@WebServlet("/DisposeFIR")
public class DisposeFIR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisposeFIR() {
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
		int policeStationId;
		String dateOfDispose;
		Boolean finalForm = false;
		String caseNo;
		Boolean success = false;
		
		//get the parameters values from request object
		policeStationId = Integer.parseInt(request.getParameter("policeStation"));
		caseNo = request.getParameter("caseNo");
		dateOfDispose = request.getParameter("dateOfDispose");
		finalForm = Boolean.parseBoolean(request.getParameter("finalForm"));
		
		FIRQueries firq = new FIRQueries();
		int firId = firq.getFIRIdFromPsAndCaseNo(policeStationId, caseNo);
		firq.close();
		
		//ADD DISPOSE TO THE DATA BASE 
		try{
			 if(firId!=0){
					Dispose dispose = new Dispose();
					dispose.setDate_of_disposal(dateOfDispose);
					dispose.setFir_id(firId);
					dispose.setType_of_final_form(finalForm);
					
					DisposeQueries disposeQ = new DisposeQueries();
					success = disposeQ.add(dispose);
					disposeQ.close();
				}
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		 
		 response.setContentType("text/html");
		 response.getOutputStream().println(success);
	}

}
