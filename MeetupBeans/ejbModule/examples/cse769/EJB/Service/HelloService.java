package examples.cse769.EJB.Service;
import java.util.Date;

import javax.persistence.*;
import examples.cse769.EJB.*;
import examples.cse769.EJB.Entity.*;
import javax.ejb.Stateless;

@Stateless
public class HelloService {
    @PersistenceContext(unitName="examples-769-EJB")
	EntityManager em;

	public String createHelloMessage(String name) {
		HelloServiceEntity newEntity = new HelloServiceEntity();
		
		long currentTime = new Date().getTime();
		newEntity.setId(currentTime); //No need to set if auto-generated
		String message = "Hello my home dog " + name + currentTime + "!";
		newEntity.setTheMessage(message);
		em.persist(newEntity);
		long beanID = newEntity.getId();
		return "Servlet Session Bean Entity " + "ID =" + beanID + " " + message;
	}
}
