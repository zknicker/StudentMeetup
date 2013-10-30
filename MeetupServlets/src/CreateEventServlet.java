
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

import services.HelloService;

/**
 * Creates an event in the meetup application.
 */
@WebServlet("/CreateEvent")
public class CreateEventServlet extends HttpServlet {

	/** Generated Serial Version UID */
	private static final long serialVersionUID = 3983414797262131110L;
	
	@EJB
	private HelloService eventCreationService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		writer.println(getHeader());
		writer.println("<h1>Event Creator</h1><hr><br>");
		
		String eventName = req.getParameter("event_name");
		if (eventName != null) {
			String eventInfo = eventCreationService.createEvent(eventName);
			writer.println("Created a new event:<br> " + eventInfo + "<br><br>");
		}
		
		writer.println("<b>Create an event:</b><br>");
		writer.println("<form name=\"event_form\">");
		writer.println("<input type=\"text\" name=\"event_name\" />");
		writer.println("<input type=\"submit\" value=\"Submit\"/>");
		writer.println("</form>");
		
		writer.println(getFooter());
		writer.close();
	}

	/**
	 * Returns the header elements for the page.
	 */
	private String getHeader() {
		return "<html><head /><body>";
	}

	/**
	 * Returns the header elements for the page.
	 */
	private String getFooter() {
		return "</body></html>";
	}

}
