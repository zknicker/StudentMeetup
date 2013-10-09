package Interaction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import ORM.DatabaseConnection;
import ORM.UserGateway;
import ORM.EventGateway;
import User.User;
import Event.Event;

/**
 * Command Line access to the Student Meetup application.
 */
public class CommandLine {

    /**
     * Main execution.
     *
     * @param args - arguments to the application.
     * @throws Exception on error in program.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Student Meetup application.");
        
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
        		"CATEGORY INT," +
        		"START_TIME VARCHAR(255)," +
        		"END_TIME VARCHAR(255)," +
        		"STATUS INT," + 
        		"THRESHOLD INT" +
        		")");
        
        statement.execute("CREATE USER IF NOT EXISTS root PASSWORD 'alpine'");
        statement.execute("GRANT ALL ON USERS TO root");
    	statement.execute("GRANT ALL ON EVENTS TO root");
    	
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
    	
    	//Update all attributes for the user 
    	userGateway.handle = "New Handle";
    	userGateway.firstName = "Updated First Name";
    	userGateway.lastName = "Modified Last Name";
    	userGateway.email = "Altered email";
    	userGateway.password = "Changed Password";
    	userGateway.rating = 45.4f;
    	userGateway.notificationPreference = true;
    	userGateway.update();
    	
    	//Delete the user from the User table
    	//userGateway.delete();
    	
    	
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
    	event.category = 10;	     
    	event.startTime = "2013-05-01 15:30";
    	event.endTime = "2013-05-01 17:30";
    	event.confirmationStatus = 1;
    	event.thresholdNumber = 4;
    	
    	EventGateway eventGateway = EventGateway.create(event);
    	eventGateway.insert();
    	
    	
         //modification of values and full update
    	eventGateway.title = "modified title";
    	eventGateway.eventDescription = "modified description";
    	eventGateway.category = 7;
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
