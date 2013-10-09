package ORM;

import java.sql.*;
import java.util.Date;

import Event.Event;

/**
 * Gateway methods for interacting with an event in the database.
 */
public class EventGateway {

    /** A connection to the database this gateway references. */
    Connection connection;

	/** Unique Event ID */
    public long eventId;

    /** The title of the event */
    public String title;
    
    /** The description of the event */
    public String eventDescription;

    /** The location of the event. */
    public String location;
    
    /** Represents the category of the event (e.g sports, cards, tv) */
    public int category;

    /** The starting time of the event. */
    public String startTime;

    /** The ending time of the event. */
    public String endTime;

    /** Details if the event has been confirmed or not. */
    public int confirmationStatus;

    /** The minimum number of users that must register for the event. */
    public int thresholdNumber;
    
    /**
     * Creates a new instance of the EventGateway.
     */
    private EventGateway() {
    	connection = DatabaseConnection.getDatabaseConnection();
    }
    /**
     * Creates a new instance of the UserGateway using an existing User.
     */
    public static EventGateway create(Event event) {
    	EventGateway eventGateway = new EventGateway();
    	
    	eventGateway.eventId = event.eventId;
    	eventGateway.title = event.title;
    	eventGateway.eventDescription = event.eventDescription;
    	eventGateway.location = event.location;
    	eventGateway.category = event.category;
    	eventGateway.startTime = event.startTime;
    	eventGateway.endTime = event.endTime;
    	eventGateway.confirmationStatus = event.confirmationStatus;
    	eventGateway.thresholdNumber = event.thresholdNumber;
    	
    	return eventGateway;
    }

    /** 
     * Insert's the event into the database. 
     * 
     * @throws SQLException on error in SQL syntax. 
     */
    public void insert() throws SQLException {
    	
    	Statement statement = connection.createStatement();
    	String values = String.format("%d, '%s', '%s', '%s', '%d', '%s', '%s', %d, '%d'", eventId, title, eventDescription, 
    			location, category, startTime, endTime, confirmationStatus, thresholdNumber);
        statement.execute("INSERT INTO EVENTS VALUES (" + values + ")"); 
        
        statement.close() ;
    }

    /** 
     * Updates the record in the database for this user. 
     */
    public void update() throws SQLException {
    	Statement statement = connection.createStatement();
        statement.execute("UPDATE EVENTS SET TITLE = '" + title + "', DESCRIPTION = '" + eventDescription + "', LOCATION = '" + 
    			           location + "', CATEGORY = " + category + ", END_TIME = '" + endTime + "', START_TIME = '" + startTime +
    			           "', STATUS = " + confirmationStatus + ", THRESHOLD = " + thresholdNumber + " WHERE EVENT_ID = " + eventId);
        
        statement.close() ;
    }

    /** 
     * Delete this record of the event from the database. 
     */
    public void delete() throws SQLException { 
    	Statement statement = connection.createStatement();
    	 statement.execute("DELETE FROM EVENTS WHERE EVENT_ID = " + eventId);
         statement.close() ;
    }
}

