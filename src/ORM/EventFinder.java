package ORM;

import java.sql.*;
import java.util.*;

import ORM.DatabaseConnection;
import ORM.EventGateway;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class EventFinder {
	
	public EventFinder()
	{}
	

	public EventGateway findEvent(long eventId) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        ResultSet results = statement.executeQuery("SELECT * FROM EVENTS WHERE EVENT_ID = " + eventId) ;
        results.next();
       
        EventGateway eventGateway = EventGateway.load(results);
        statement.close();
        
        return eventGateway;
   
	}
	
	
	public List<EventGateway> findEventsByCategory(String category) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        List<EventGateway> result = new ArrayList<EventGateway>();
        ResultSet results = statement.executeQuery("SELECT * FROM EVENTS WHERE CATEGORY = '" + category + "'") ;
       
        while (results.next())
        {
        EventGateway eventGateway = EventGateway.load(results);
        result.add(eventGateway);
        }
        statement.close();
   
		return result;
	}
	
}
	
	