package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entities.*;

import javax.ejb.Stateless;

@Stateless
public class CalendarService {
    @PersistenceContext(unitName="create-event")
	EntityManager em;

	public List<String> getEventsByDateTime(String year, String month, String day, String hour) {
		List<String> results = new ArrayList<String>();
		
		List<Event> events = em.createQuery("from Event e where STARTTIME LIKE '"+ year + "-" + month + "-" +day+ " " + hour + ":%:%'").getResultList();
		if (events == null) {
			results.add("No events found.");
		} else {
			for (Event event : events) {
				results.add("Name: " + event.getName() + ", StartTime: "
			                + event.getStarttime().toLocaleString() + ", EndTime: "+ event.getEndtime().toLocaleString()
			                + ", Location: " +event.getLocation());
						
			}
		}
		
		return results;
	}
}
