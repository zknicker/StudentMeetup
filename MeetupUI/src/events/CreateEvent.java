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

    public String getName () {
        return name;
    }
    
    public void setName (final String name) {
        this.name = name;
    }

    public String getIntroText() {
    	return "Create a new event.";
    }
    
	public boolean create() {
		System.out.println("Creating event with name " + name);
		String eventInfo = createEventService.createEvent(name);
		return true;
	}
	
	public List<String> getAllEvents() {
		return createEventService.getAllEvents();
	}
}
