package events;

import java.util.List;

import javax.ejb.EJB;

import details.EventDetails;
import services.*;

/**
 * Creates events.
 */
public class CreateEvent
{
	@EJB 
	private EventService createEventService;
	
	/** Event name. */
    private String name;
    
	/** Event description. */
    private String description;
    
	/** Event Date. */
    private String year, day, month;
    
	/** Start Time. */
    private String startHour, startMinute;
    
	/** End Time. */
    private String endHour, endMinute;
    
	/** Event Location. */
    private String location;
    
	/** Event Category. */
    private String category;
    
	/** Event Threshold. */
    private String threshold;
    	
    public String getName () {
        return name;
    }
    
    public void setName (final String name) {
        this.name = name;
    }
    
    public String getDescription () {
        return description;
    }
    
    public void setDescription (final String description) {
        this.description = description;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear (final String year) {
        this.year = year;
    }
    
    public String getDay () {
        return day;
    }
    
    public void setDay (final String day) {
        this.day = day;
    }

    public String getMonth () {
        return month;
    }
    
    public void setMonth (final String month) {
        this.month = month;
    }
    
    public String getStartHour () {
        return startHour;
    }
    
    public void setStartHour (final String starthour) {
        this.startHour = starthour;
    }
    
    public String getStartMinute () {
        return startMinute;
    }
    
    public void setStartMinute (final String startminute) {
        this.startMinute = startminute;
    }

    public String getEndHour () {
        return endHour;
    }
    
    public void setEndHour (final String endhour) {
        this.endHour = endhour;
    }
    
    public String getEndMinute () {
        return endMinute;
    }
    
    public void setEndMinute (final String endminute) {
        this.endMinute = endminute;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation (final String location) {
        this.location = location;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory (final String category) {
        this.category = category;
    }
    
    public String getThreshold() {
        return threshold;
    }
    
    public void setThreshold (final String threshold) {
        this.threshold = threshold;
    }
	
    public String getIntroText() {
    	return "Create a new event.";
    }
    
	public boolean create() {
		EventDetails event = new EventDetails();
		event.setName(name);
		event.setDescription(description);
		event.setStarttime(year, month, day, startHour, startMinute);
		event.setEndtime(year, month, day, endHour, endMinute);
		event.setLocation(location);
		event.setCategory(category);
		event.setThreshold(Integer.parseInt(threshold));
		
		createEventService.createEvent(event);
		return true;
	}
	
	public List<String> getAllEvents() {
		return createEventService.getAllEvents();
	}
}
