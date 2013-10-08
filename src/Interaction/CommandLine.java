package Interaction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import ORM.DatabaseConnection;
import ORM.UserGateway;
import User.User;

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
        statement.execute("DROP TABLE USERS");
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
        statement.execute("CREATE TABLE IF NOT EXISTS EVENTS(" +
        		"EVENT_ID INT PRIMARY KEY," + 
        		"EVENT_NAME VARCHAR(255)" +
        		// ...
        		// ...
        		// ...
        		")");
        
        statement.execute("CREATE USER IF NOT EXISTS root PASSWORD 'alpine'");
        statement.execute("GRANT ALL ON USERS TO root");
    	statement.execute("GRANT ALL ON EVENTS TO root");
    	
    	// Try an insert.
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
        statement.close() ;
        connection.close();
    }
}
