package ORM;

import java.sql.*;
import java.util.Date;

import User.User;

/**
 * Gateway methods for interacting with a user in the database.
 */
public class UserGateway {

    /** A connection to the database this gateway references. */
    Connection connection;

    /** Unique User ID */
    public long userId;

    /** The user's handle. */
    public String handle;

    /** The user's first name. */
    public String firstName;

    /** The user's last name. */
    public String lastName;

    /** The user's password. */
    public String password;

    /** The user's email address. */
    public String email;

    /** The user's rating. */
    public double rating;

    /** The user's notification preference. */
    public boolean notificationPreference;
    
    /**
     * Creates a new instance of the UserGateway.
     */
    private UserGateway() {
    	connection = DatabaseConnection.getDatabaseConnection();
    }
    /**
     * Creates a new instance of the UserGateway using an existing User.
     */
    public static UserGateway create(User user) {
    	UserGateway userGateway = new UserGateway();
    	
    	userGateway.userId = user.userId;
    	userGateway.handle = user.handle;
    	userGateway.firstName = user.firstName;
    	userGateway.lastName = user.lastName;
    	userGateway.email = user.email;
    	userGateway.password = user.password;
    	userGateway.rating = user.rating;
    	userGateway.notificationPreference = user.notificationPreference;
    	
    	return userGateway;
    }

    /** 
     * Insert's the user into the database. 
     * 
     * @throws SQLException on error in SQL syntax. 
     */
    public void insert() throws SQLException {
    	
    	Statement statement = connection.createStatement();
    	String values = String.format("%d, '%s', '%s', '%s', '%s', '%s', %f, '%s'", userId, handle, 
    			firstName, lastName, password, email, rating, ((Boolean)notificationPreference).toString());
        statement.execute("INSERT INTO USERS VALUES (" + values + ")"); 
        
        statement.close() ;
    }

    /** 
     * Updates the record in the database for this user. 
     */
    public void update() {

    }

    /** 
     * Delete this record of the user from the database. 
     */
    public void delete() {

    }
}
