package Database;

import User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
     * Creates a new instance of the UserGateway from an existing domain-space User.
     *
     * @param user - the domain-space user.
     * @return the populated UserGateway instance.
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
     * Creates a new instance of the UserGateway from a ResultSet resulting from a
     * select query.
     *
     * @param row - the row returned from the select query.
     * @return the populated UserGateway instance.
     * @throws SQLException on error reading data the row.
     */
    public static UserGateway create(ResultSet row) throws SQLException{
    	UserGateway userGateway = new UserGateway();

    	userGateway.userId = row.getLong(1);
    	userGateway.handle = row.getString(2);
    	userGateway.firstName = row.getString(3);
    	userGateway.lastName = row.getString(4);
    	userGateway.email = row.getString(5);
    	userGateway.password = row.getString(6);
    	userGateway.rating = row.getFloat(7);
    	userGateway.notificationPreference = row.getBoolean(8);

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
    public void update() throws SQLException {
    	Statement statement = connection.createStatement();

        statement.execute("UPDATE USERS SET HANDLE = '" + handle + "', FIRST_NAME = '" + firstName + "', LAST_NAME = '" + 
    			           lastName + "', PASSWORD = '" + password + "', EMAIL = '" + email + "', RATING = " + rating +
    			           ", NOTIFICATION_PREFERENCE = " + ((Boolean)notificationPreference).toString() + " WHERE USER_ID = " + userId);
    }

    /** 
     * Delete this record of the user from the database. 
     */
    public void delete()  throws SQLException { 
    	Statement statement = connection.createStatement();
   	 statement.execute("DELETE FROM USERS WHERE USER_ID = " + userId);
        statement.close() ;

    }
}
