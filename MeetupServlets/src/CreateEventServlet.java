
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
	private HelloService helloService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
		writer.println(getHeader());
		writer.println("<h1>" + helloService.createEvent("Zach Knickerbocker")
				+ "</h1>");
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
