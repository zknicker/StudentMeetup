package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CalendarService {
    @PersistenceContext(unitName="create-event")
	EntityManager em;
     

	public List<String> getEventsByDateTime(String year, String month, String day, String hour) {

		List<String> results = new ArrayList<String>();
		
		if(month.length() < 2)
			 month = ("0" + month);
		if(hour.length() < 2)
			hour = ("0" + hour);
		if(day.length() < 2)
			day = ("0" + day);

		String wildcard = "%:%'";
		String QueryString = "select e from Event e where STARTTIME LIKE '" + year + "-" + month + "-" + day + " " + hour + ":" +wildcard;
		System.out.println(QueryString);
		Query query = em.createQuery(QueryString);
		
		List<Event> events = query.getResultList();		

		if (events.isEmpty()) {
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
