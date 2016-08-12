package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.FIR;
import crime.review.database.FIRQueries;
import crime.review.database.PoliceOfficer;
import crime.review.database.PoliceOfficerQueries;

/**
 * Servlet implementation class GetPaginatedPendingCases
 */
@WebServlet("/GetPaginatedPendingCases")
public class GetPaginatedPendingCases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPaginatedPendingCases() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting the parameters send from the url
		int pageNo =  Integer.parseInt(request.getParameter("pageNo"));
		int policeStationId =  Integer.parseInt(request.getParameter("policeStationId"));
		int pageSize = 5;
		
		int offset = (pageNo-1) * pageSize;
		FIRQueries firq = new FIRQueries();
		
		int totalNoOfCases =  firq.get_count_of_pending_fir_from_ps(policeStationId);		
		List<FIR> listOfPendingFIRs = firq.getPaginatedListOfPendingFIRs(pageSize,offset,policeStationId);
		
		String tblHead = "<div class='table'>"+
				"<div class='tr'>"+
					"<span class='td'><textarea class='Box' rows='1' cols='2'>#</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='10'>Case No.</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='30'>Section of Law</textarea></span>" +
					"<span class='td'><textarea class='Box' rows='1' cols='15'>I/O</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='20'>Action taken by IO</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='20'>Last CD with date</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='20'>Reason for Pending</textarea></span>"+
					"<span class='td'><textarea class='Box' rows='1' cols='25'>SPs Comments</textarea></span>"+
				"</div>";
		
		String tblRows="";
		PoliceOfficerQueries psq = new PoliceOfficerQueries();
		
		//Making sure that the no of rows displayed is not more than 5
		int noOfPendingFIRs = pageSize;
		if( listOfPendingFIRs.size() < pageSize)
			noOfPendingFIRs = listOfPendingFIRs.size();
				
		int serialNo = (pageNo-1)*pageSize+1;
		for(int i = 0; i < noOfPendingFIRs ; i++,serialNo++ )
		{
			FIR fir = listOfPendingFIRs.get(i);
			PoliceOfficer po = psq.getPoliceOfficer_from_id(fir.getPolice_officer_id());
			tblRows += "<div class='tr'><form method='post' class='crimeReviewForm'>";
			tblRows += "<input name='firId' type='hidden' value='"+fir.getFir_id()+"'/>";
			tblRows	+= "<span class='td'><textarea class='Box' rows='2' cols='2'>"+serialNo+"</textarea></span>";
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='10'>" + fir.getCase_no()+ "</textarea></span>";
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='30'>" + fir.getSection_of_law() + "</textarea></span>";			
			tblRows += "<span class='td'><textarea class='Box' rows='2' cols='15'>" + po.getFname() + " " + po.getLname() + "</textarea></span>";
			tblRows += "<span class='td'><textarea name='actionTakenByIO' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea name='lastCdWithDate' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea name='reasonForPending' rows='2' cols='20'></textarea></span>";
			tblRows += "<span class='td'><textarea name='spRemarks' rows='2' cols='25'></textarea></span>";
			tblRows += "<span class='td'><input type='submit' id='btnUpdate' value='Update Review'/></span></form></div>";
		}
		psq.close();
		
		//creating the complete table containing pending FIRs of Police Stations
		String tablePS = tblHead + tblRows +"</div>";
				
		String paginationLinks = "<div id='paginationLinksDiv'><table><tbody><tr>";
		String colOfLinks="";
		for(int k=1,  c = 0 ; c < totalNoOfCases; c+=5, k++)
		{
			colOfLinks+= "<td><a id='"+policeStationId+"'class='paginationLinks' href='#' value='"+k+"'>"+k+"</a></td>";
		}
		
		String pLinks= paginationLinks+ colOfLinks + "</tr></tbody></table></div>";	
		response.setContentType("text/html");
		response.getWriter().println(tablePS+pLinks);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}
