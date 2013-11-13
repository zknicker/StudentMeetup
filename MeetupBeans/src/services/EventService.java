package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import details.EventDetails;
import entities.*;

import javax.ejb.Stateless;

/**
 * Stateless session bean that provides functionality to applications wishing
 * to manipulate events.
 */
@Stateless
public class EventService {
    @PersistenceContext(unitName="event")
	EntityManager em;

    /**
     * Creates an event.
     */
	public void createEvent(EventDetails eventDetails) {
		Event event = new Event();
				
		event.setName(eventDetails.getName());
		event.setDescription(eventDetails.getDescription());
		event.setStartime(eventDetails.getStarttime());
		event.setEndtime(eventDetails.getEndtime());
		event.setLocation(eventDetails.getLocation());
		event.setCategory(eventDetails.getCategory());
		event.setStatus(0); // default status for events
		event.setThreshold(eventDetails.getThreshold());
		
		em.persist(event);
	}
	
	/**
	 * Returns a list of all of the events in the system. This isn't a very
	 * useful method. It's just used on a temporary page a user is redirected
	 * to after creating an event to quickly see all of the events in the system.
	 */
	public List<String> getAllEvents() {
		List<String> results = new ArrayList<String>();
		
		List<Event> events = em.createQuery("from Event e").getResultList();
		if (events == null) {
			results.add("No events found.");
		} else {
			for (Event event : events) {
				results.add("ID: " + event.getId() + ", Name: " + event.getName() + ", Description: " + event.getDescription() + ", StartTime: "
			            + event.getStarttime() + ", EndTime: "+ event.getEndtime() + ", Location: " +event.getLocation() + ", Category: " 
			            + event.getCategory() + ", Min. Attendees: " + event.getThreshold());
						
			}
		}
		
		return results;
	}
}
