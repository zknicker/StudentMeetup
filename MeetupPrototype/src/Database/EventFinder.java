package Database;

import java.util.*;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Finds rows of events in the database for different critera. In all cases, the row object model,
 * EventGateway, is returned either as a single object or a list.
 */
public class EventFinder {

    /**
     * Finds an event in the database with the specified event ID.
     *
     * @param eventId - the event ID of the desired event.
     * @return the event with the desired event Id.
     * @throws SQLException on error querying the database.
     */
	public EventGateway findEvent(long eventId) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        ResultSet results = statement.executeQuery("SELECT * FROM EVENTS WHERE EVENT_ID = " + eventId) ;
        results.next();
       
        EventGateway eventGateway = EventGateway.create(results);
        statement.close();
        
        return eventGateway;
	}

    /**
     * Finds all events with a given category (case sensitive).
     * TODO - remove case sensitivity requirement.
     *
     * @param category - the desired category.
     * @return the events with the desired category.
     * @throws SQLException on error querying the database.
     */
	public List<EventGateway> findEventsByCategory(String category) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        List<EventGateway> result = new ArrayList<EventGateway>();
        ResultSet results = statement.executeQuery("SELECT * FROM EVENTS WHERE CATEGORY = '" + category + "'") ;
       
        while (results.next())
        {
            EventGateway eventGateway = EventGateway.create(results);
            result.add(eventGateway);
        }
        statement.close();
   
		return result;
	}
	
}
	
	