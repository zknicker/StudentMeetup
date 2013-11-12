package services;

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
 * Provides functionality relating to rendering and using a calendar
 * of events.
 */
@Stateless
public class CalendarService {
    @PersistenceContext(unitName="event")
	EntityManager em;
     
    private final Long secondsInDay = 86400L;
    
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
		long searchTimeLowerBound = date.getTime() / 1000;
		long searchTimeUpperBound = searchTimeLowerBound + secondsInDay;
		
		String queryString = "select e from Event e where STARTTIME >= " + searchTimeLowerBound + " AND STARTTIME < " + searchTimeUpperBound + " ORDER BY STARTTIME";
		Query query = em.createQuery(queryString);
		List<Event> queryResult = query.getResultList();	

		for (Event event : queryResult) {
			EventDetails eventDetails = new EventDetails();
			eventDetails.setName(event.getName());
			eventDetails.setDescription(event.getDescription());
			eventDetails.setStartime(event.getStarttime());
			eventDetails.setEndtime(event.getEndtime());
			eventDetails.setLocation(eventDetails.getLocation());
			eventDetails.setCategory(event.getCategory());
			eventDetails.setThreshold(event.getThreshold());
			
			events.add(eventDetails);			
		}
		
		return events;
    }
}
