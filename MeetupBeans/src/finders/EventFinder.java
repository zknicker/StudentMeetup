package finders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import details.EventDetails;
import entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Provides functionality for retrieving events from the datastore by
 * varying sets of parameters and conditions.
 */
@Stateless
public class EventFinder {
	
	/** The entity manager corresponding to events. */
    @PersistenceContext(unitName="event")
	EntityManager em;
    
    /**
     * Gets events by time.
     * 
     * @param year - the year corresponding to the desired event.
     * @param month - the month corresponding to the desired event.
     * @param day - the day corresponding to the desired event.
     * @return a list of {@link EventDetails} matching the parameter criteria.
     * @throws ParseException on error parsing the inputted time information.
     */
    public List<EventDetails> getEvents(String year, String month, String day) throws ParseException {
		List<EventDetails> events = new ArrayList<EventDetails>();
		
		if(month.length() < 2)
			 month = ("0" + month);
		if(day.length() < 2)
			day = ("0" + day);

		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(year + "-" + month + "-" + day);
		long millisSinceEpoch = date.getTime();
		long secondsSinceEpoch = millisSinceEpoch / (60 * 1000);
		
		String wildcard = "%:%'";
		String QueryString = "select e from Event e where STARTTIME LIKE '" + year + "-" + month + "-" + day + " " + "05" + ":" +wildcard;
		System.out.println(QueryString);
		Query query = em.createQuery(QueryString);
		
		List<Event> queryResult = query.getResultList();		

		return events;
    }

	public List<String> getEventsByDateTime(String year, String month, String day, String hour) {

		List<String> results = new ArrayList<String>();
		
		if(month.length() < 2)
			 month = ("0" + month);
		if(hour.length() < 2)
			hour = ("0" + hour);
		if(day.length() < 2)
			day = ("0" + day);

		String wildcard = "%:%'";
		String QueryString = "from Event e";
		System.out.println(QueryString);
		Query query = em.createQuery(QueryString);
		
		List<Event> events = query.getResultList();		

		if (events.isEmpty()) {
			results.add("No events found.");
		} else {
			for (Event event : events) {
				results.add("Name: " + event.getName() + ", StartTime: "
			                + event.getStarttime() + ", EndTime: "+ event.getEndtime()
			                + ", Location: " +event.getLocation());			
			}
		}

		return results;
	}
	
	
}
