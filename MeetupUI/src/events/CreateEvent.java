package events;

import java.util.List;

import javax.ejb.EJB;

import services.*;

/**
 * Creates events.
 */
public class CreateEvent
{
	@EJB 
	private CreateEventService createEventService;
	
	/** Event name. */
    private String name;
	/** Event description. */
    private String description;
	/** Event Date. */
    private String year,day,month;
	/** Start Time. */
    private String starthour,startminute;
	/** End Time. */
    private String endhour,endminute;
	/** Event Location. */
    private String location;
	/** Event Category. */
    private String category;
	/** Event Threshold. */
    private String threshold;
    
	private String dayToView,monthToView,yearToView;
	
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
    
    public String getStarthour () {
        return starthour;
    }
    
    public void setStarthour (final String starthour) {
        this.starthour = starthour;
    }
    
    public String getStartminute () {
        return startminute;
    }
    
    public void setStartminute (final String startminute) {
        this.startminute = startminute;
    }

    public String getEndhour () {
        return endhour;
    }
    
    public void setEndhour (final String endhour) {
        this.endhour = endhour;
    }
    
    public String getEndminute () {
        return endminute;
    }
    
    public void setEndminute (final String endminute) {
        this.endminute = endminute;
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
	public void setMonthToView(String newMonthToView) {
		monthToView = newMonthToView;
	}
	public String getMonthToView() {
		return monthToView;
	}
	
	public void setDayToView(String newDayToView) {
		dayToView = newDayToView;
	}
	public String getDayToView() {
		return dayToView;
	}
	
	public void setYearToView(String newYearToView) {
		yearToView = newYearToView;
	}
	public String getYearToView() {
		return yearToView;
	}


    public String getIntroText() {
    	return "Create a new event.";
    }
    
	public boolean create() {
		System.out.println("Creating event with name " + name);
		String eventInfo = createEventService.createEvent(name,description,year,day,month,starthour,startminute,endhour,endminute,
				            location,category,threshold);
		return true;
	}
	
	public List<String> getAllEvents() {
		return createEventService.getAllEvents();
	}
}
