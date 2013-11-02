package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entities.*;

import javax.ejb.Stateless;

@Stateless
public class CreateEventService {
    @PersistenceContext(unitName="create-event")
	EntityManager em;

	public String createEvent(String name, String description) {
		Event event = new Event();
		
		event.setName(name);
		event.setDescription(description);
		
		em.persist(event);
		long beanID = event.getId();
		
		return "ID =" + beanID + "<br>Name: " + name + "<br>Description: " + description;
	}
	
	public List<String> getAllEvents() {
		List<String> results = new ArrayList<String>();
		
		List<Event> events = em.createQuery("from Event e").getResultList();
		if (events == null) {
			results.add("No events found.");
		} else {
			for (Event event : events) {
				results.add("ID: " + event.getId() + ", Name: " + event.getName() + ", Description: " + event.getDescription());
			}
		}
		
		return results;
	}
}
