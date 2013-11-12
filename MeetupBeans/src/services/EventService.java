package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import details.EventDetails;
import entities.*;

import javax.ejb.Stateless;

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
		event.setThreshold(eventDetails.getThreshold());
		
		em.persist(event);
	}
	
	/**
	 * Returns a list of all of the events.
	 */
	public List<String> getAllEvents() {
		List<String> results = new ArrayList<String>();
		
		List<Event> events = em.createQuery("from Event e").getResultList();
		if (events == null) {
			results.add("No events found.");
		} else {
			for (Event event : events) {
				results.add("ID: " + event.getId() + ", Name: " + event.getName() + ", Description: " + event.getDescription() + ", StartTime: "
			                + event.getStarttime().toLocaleString() + ", EndTime: "+ event.getEndtime().toLocaleString()
			                + ", Location: " +event.getLocation() + ", Category: " + event.getCategory() + ", Min. Attendees: " + event.getThreshold());
						
			}
		}
		
		return results;
	}
}
