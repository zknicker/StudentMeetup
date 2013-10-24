package Database;

import java.sql.*;

/**
 * Manages providing connections to the database.
 */
public class DatabaseConnection {
	
	/** Instance of a connect to the database. */
	private static Connection instance;
	
    /** URL of the H2 database. */
    private static String URL = "jdbc:h2:tcp://localhost/~/studentmeetup";

    /** Users to authenticate to the database with. */
    private static String USER = "sa";

    /** Password to the authenticate to the database with. */
    private static String PASSWORD = "";

    /**
     * Returns a connection to the database.
     *
     * @return connection to a database.
     */
    public static Connection getDatabaseConnection() {
    	Connection connection = null;
    	
    	if (instance == null) {
	    	try {
	            Class.forName("org.h2.Driver");
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	
	        } catch (final ClassNotFoundException e) {
	            System.out.println("Could not find the h2 driver class: " + e.getMessage());
	        } catch (final SQLException e) {
	            System.out.println("Could not connect to the DB: " + e.getMessage());
	        }
	
	    	DatabaseConnection.instance = connection;
	    	
    	}
    	
    	return instance;
    }
}
