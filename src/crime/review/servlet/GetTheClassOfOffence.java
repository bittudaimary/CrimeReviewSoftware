package crime.review.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crime.review.database.ClassofOffence;
import crime.review.database.ClassofOffenceQueries;


/**
 * Servlet implementation class GetTheClassOfOffence
 */
@WebServlet("/GetTheClassOfOffence")
public class GetTheClassOfOffence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTheClassOfOffence() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClassofOffenceQueries cq = new ClassofOffenceQueries();
		List<ClassofOffence> listOfClassOfOffences = cq.getAllClassofOffence();
		
		String classOfOffenceOptions = "<select id='classOfOffences' name='classOfOffence'><option>Please select a Classification</option>";
			
		for(int i = 0 ; i < listOfClassOfOffences.size(); i++) {
			ClassofOffence cof = listOfClassOfOffences.get(i);
			classOfOffenceOptions += "<option value='"+cof.getId()+"'>"+cof.getName()+"</option>";
		}
		classOfOffenceOptions+= "</select>";
		
		cq.close();
		
		response.setContentType("text/html");
		response.getWriter().println(classOfOffenceOptions);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
