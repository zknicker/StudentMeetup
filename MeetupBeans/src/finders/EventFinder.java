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
    
   private final Long secondsInDay = 86400L;
    
    /**
     * Gets events by time.
     * 
     * @param year - the year corresponding to the desired event.
     * @param month - the month corresponding to the desired event.
     * @param day - the day corresponding to the desired event.
     * @return a list of {@link Event} matching the parameter criteria.
     * @throws ParseException on error parsing the inputted time information.
     */
    public List<Event> getEvents(String year, String month, String day) throws ParseException {
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
		
		@SuppressWarnings("unchecked")
		List<Event> events = query.getResultList();	

		return events;
    }
}
