package Interaction;
import XML.DataInjector;
import Event.Event;
import Database.*;
import User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A demonstration of the Student Meetup ORM layer. Creates users and events, and then does some
 * basic management on them (e.g. delete, select).
 *
 * This is more or less a sandbox to do some quick tests.
 *
 * TODO - replace this with unit tests, and make the demonstration more significant.
 */
public class Demonstration {

    /**
     * Main execution.
     *
     * @param args - arguments to the application.
     * @throws Exception on error in program.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Student Meetup application demo.");
        
        Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        
              
        // Create the Users table if it does not exist.
        statement.execute("DROP TABLE IF EXISTS USERS");
        statement.execute("CREATE TABLE IF NOT EXISTS USERS(" +
        		"USER_ID LONG PRIMARY KEY," +
        		"HANDLE VARCHAR(255)," +
        		"FIRST_NAME VARCHAR(255)," +
        		"LAST_NAME VARCHAR(255)," +
        		"PASSWORD VARCHAR(255)," +
        		"EMAIL VARCHAR(255)," +
        		"RATING DOUBLE," +
        		"NOTIFICATION_PREFERENCE VARCHAR(5)" +
        		")");
        
        // Create the Events table if it does not exist.
        statement.execute("DROP TABLE IF EXISTS EVENTS");    
        statement.execute("CREATE TABLE IF NOT EXISTS EVENTS(" +
        		"EVENT_ID LONG PRIMARY KEY," + 
        		"TITLE VARCHAR(255)," +
        		"DESCRIPTION VARCHAR(255)," +
        		"LOCATION VARCHAR(255)," +
        		"CATEGORY VARCHAR(255)," +
        		"START_TIME VARCHAR(255)," +
        		"END_TIME VARCHAR(255)," +
        		"STATUS INT," + 
        		"THRESHOLD INT" +
        		")");
        
        statement.execute("CREATE USER IF NOT EXISTS root PASSWORD 'alpine'");
        statement.execute("GRANT ALL ON USERS TO root");
    	statement.execute("GRANT ALL ON EVENTS TO root");
    	
    	
    	//Process XML files if included on command line
    	
/*        if (args.length > 0)	
        {
        	
        }*/
    	DataInjector dataInjector = new DataInjector();
    	
    	
    	
    	// Try an insert of a user.
    	Date date = new Date();
    	User user = new User();
    	user.userId = date.getTime(); // this should be an auto-incrementing field... fix later
    	user.handle = "zknicker";
    	user.firstName = "Zach";
    	user.lastName = "Knickerbocker";
    	user.email = "zknicker@gmail.com";
    	user.password = "s3c43t";
    	user.rating = 99.2f;
    	user.notificationPreference = false;
    	
    	UserGateway userGateway = UserGateway.create(user);
    	userGateway.insert();
    	
    	user = new User();
    	user.userId = 3456; // this should be an auto-incrementing field... fix later
    	user.handle = "dch9";
    	user.firstName = "Dave";
    	user.lastName = "Holmes";
    	user.email = "dh@gmail.com";
    	user.password = "234sd2";
    	user.rating = 99.2f;
    	user.notificationPreference = false;
    	userGateway = UserGateway.create(user);
    	userGateway.insert();
    	
    	//Update all attributes for the user 
    	userGateway.handle = "New Handle";
    	userGateway.firstName = "Updated First Name";
    	userGateway.lastName = "Modified Last Name";
    	userGateway.email = "Altered email";
    	userGateway.password = "Changed Password";
    	userGateway.rating = 45.4f;
    	userGateway.notificationPreference = true;
    	
        /** Test code to update all user attributes */
    	//userGateway.update();

    	
        /** Test code to delete user based on id */
    	//userGateway.delete();
    	
        
        /** Test code to pull back user based on id */
    	UserFinder userFinder =  new UserFinder();
        //userGateway = userFinder.findUser(1);
    	
        /** Test code to pull back a collection of users */
    	//List<UserGateway> userSet = new ArrayList<UserGateway>();
    	//userSet = userFinder.findUsersByNotificationPreference(false);
    	
    	//for(int i=0;i<userSet.size();i++){
    	//    System.out.println(userSet.get(i).handle);
    	//} 
    	
    	
        // Try a select (this will go into a finder class)
    	ResultSet results = statement.executeQuery( "SELECT * FROM USERS" ) ;

    	// Loop through the result set.
    	while (results.next()) {
    		int numFields = 8;
    		for(int i = 0; i < numFields; i++) {
    			String terminator = (i == numFields - 1) ? "\n" : " : ";
    			System.out.print(results.getString(i + 1) + terminator);
    		}
    	}

    	results.close() ;
    	
    	
    	// Try an insert of an event.
    	date = new Date();
    	Event event = new Event();
     	event.eventId = date.getTime();
    	event.title = "Pickup Basketball";
    	event.eventDescription = "Friendly hoops game on Friday night";
    	event.location = "Jesse Owens North";
    	event.category = "Sports";	     
    	event.startTime = "2013-05-01 15:30";
    	event.endTime = "2013-05-01 17:30";
    	event.status = 1;
    	event.threshold = 4;
    	
    	EventGateway eventGateway = EventGateway.create(event);
    	eventGateway.insert();
    	
        event = new Event();
     	event.eventId = 3214546;
    	event.title = "Poker Night";
    	event.eventDescription = "Low stakes poker game";
    	event.location = "Ohio Union";
    	event.category = "Sports";	     
    	event.startTime = "2013-05-01 15:30";
    	event.endTime = "2013-05-01 17:30";
    	event.status = 1;
    	event.threshold = 4;
    	
        eventGateway = EventGateway.create(event);
    	eventGateway.insert();
    	
    	eventGateway.eventDescription = "modified description";
    	
    	/**Test code to pull back a single event **/
    	EventFinder eventFinder =  new EventFinder();
        eventGateway = eventFinder.findEvent(3214546);
        System.out.println(eventGateway.eventDescription);
        
        
        /** Test code to pull back a collection of events */
     	List<EventGateway> eventSet = new ArrayList<EventGateway>();
     	eventSet = eventFinder.findEventsByCategory("Sports");
     	
     	for(int i=0;i<eventSet.size();i++){
     	    System.out.println(eventSet.get(i).category);
     	} 
    	
    	
         //modification of values and full update
    	eventGateway.title = "modified title";
    	eventGateway.eventDescription = "modified description";
    	eventGateway.category = "Misc.";
    	eventGateway.location = "new location";
    	eventGateway.startTime = "2004-05-01 15:30";
    	eventGateway.endTime = "2005-05-01 15:30";   	
    	eventGateway.confirmationStatus = 0;
    	eventGateway.thresholdNumber = 3;
    	eventGateway.update();
    	
    	
    	//remove the record from the table
    	
    	//eventGateway.delete();
    	
        // Try a select (this will go into a finder class)
    	ResultSet eventResults = statement.executeQuery( "SELECT * FROM EVENTS" ) ;
    	
    	while (eventResults.next()) {
    		int numFields = 9;
    		for(int i = 0; i < numFields; i++) {
    			String terminator = (i == numFields - 1) ? "\n" : " : ";
    			System.out.print(eventResults.getString(i + 1) + terminator);
    		}
    	}
    
    	eventResults.close() ; 	
  
        statement.close() ;
        connection.close();
    }
}
