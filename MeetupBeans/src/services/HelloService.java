package services;

import javax.persistence.*;

import entities.*;

import javax.ejb.Stateless;

@Stateless
public class HelloService {
    @PersistenceContext(unitName="create-event")
	EntityManager em;

	public String createEvent(String name) {
		Event event = new Event();
		
		event.setName(name);
		
		em.persist(event);
		long beanID = event.getId();
		
		return "ID =" + beanID + "<br>Name: " + name;
	}
}
