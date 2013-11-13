package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import details.EventDetails;
import entities.*;
import finders.EventFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Provides functionality relating to rendering and using a calendar
 * of events.
 */
@Stateless
public class CalendarService {
	
	/** An event finder to retrieve events from the DB. */
    @EJB
	EventFinder eventFinder;
    
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
		List<EventDetails> result = new ArrayList<EventDetails>();
		
		List<Event> events = eventFinder.getEvents(year, month, day);

		for (Event event : events) {
			EventDetails eventDetails = new EventDetails();
			eventDetails.setName(event.getName());
			eventDetails.setDescription(event.getDescription());
			eventDetails.setStartime(event.getStarttime());
			eventDetails.setEndtime(event.getEndtime());
			eventDetails.setLocation(eventDetails.getLocation());
			eventDetails.setCategory(event.getCategory());
			eventDetails.setStatus(event.getStatus());
			eventDetails.setThreshold(event.getThreshold());
			
			result.add(eventDetails);			
		}
		
		return result;
    }
}
