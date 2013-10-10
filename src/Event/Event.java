package Event;
/**
 * An event in the application. Also persisted in a database; this can be managed using
 * the ORM classes EventGateway and EventFinder.
 */
public class Event {
	
	/** Unique Event ID */
    public long eventId;
    
    /** The title of the event */
    public String title;

    /** The description of the event */
    public String eventDescription;

    /** The location of the event. */
    public String location;
    
    /** Represents the category of the event (e.g sports, cards, tv) */
    public String category;

    /** The starting time of the event. */
    public String startTime;

    /** The ending time of the event. */
    public String endTime;

    /** Details if the event has been confirmed or not. */
    public int status;

    /** The minimum number of users that must register for the event. */
    public int threshold;
     
}
