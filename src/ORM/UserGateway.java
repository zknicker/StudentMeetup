package ORM;

import java.sql.*;

/**
 * Gateway methods for interacting with a user in the database.
 *
 * @author Zach Knickerbocker
 */
public class UserGateway {

    /** A connection to the database this gateway references. */
    Connection dbConnection;

    /**
     * Creates a new instance of the UserGateway.
     */
    public UserGateway() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        dbConnection = connectionFactory.getDbConnection();
    }

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
    public float rating;

    /** The user's notification preference. */
    public boolean notificationPreference;

    /** Insert's the user into the database. */
    public void insert() {

    }

    /** Updates the record in the database for this user. */
    public void update() {

    }

    /** Delete this record of the user from the database. */
    public void delete() {

    }

        // add application code here
	// Execute the query
	ResultSet rs = stmt.executeQuery( "SELECT table_name FROM information_schema.tables" ) ;

	// Loop through the result set
	while( rs.next() ) System.out.println( rs.getString(1) );

	// Close the result set, statement and the connection
	rs.close() ;
	stmt.execute("CREATE TABLE IF NOT EXISTS CUSTOMERS(ID INT PRIMARY KEY,NAME VARCHAR(255))");
	stmt.execute("CREATE USER IF NOT EXISTS TestDB PASSWORD 'abcd'");
	stmt.execute("GRANT ALL ON CUSTOMERS TO TestDB");
	stmt.close() ;
        conn.close();
}
