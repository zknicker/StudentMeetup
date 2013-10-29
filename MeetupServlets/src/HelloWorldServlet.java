

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

import examples.cse769.EJB.Service.HelloService;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /HelloServlet using the {@linkplain WebServlet
 * @HttpServlet}. The {@link HelloService} is injected by CDI.
 * </p>
 * 
 * @author Pete Muir
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/HelloWorldEJB")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";

   @EJB 
   private HelloService helloService;
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   /*
	   Context context = null;
	   try {
	     context = new InitialContext();
	     helloService = (HelloService) context.lookup( 
  	       "java:global/Example-EJB-Session-Entity/HelloService");
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	   */
	
	   PrintWriter writer = resp.getWriter();
       writer.println(PAGE_HEADER);
       writer.println("<h1>" + helloService.createHelloMessage("Rajiv") + "</h1>");
       writer.println(PAGE_FOOTER);
       writer.close();
   }

}