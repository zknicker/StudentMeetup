package ORM;

import java.sql.*;

/**
 * Constructs a connection to the database for the caller.
 *
 * @author Zach Knickerbocker
 */
public class ConnectionFactory {

    /** URL of the H2 database. */
    private static String URL = "jdbc:h2:tcp://localhost/~/test";

    /** Users to authenticate to the database with. */
    private static String USER = "sa";

    /** Password to the authenticate to the database with. */
    private static String PASSWORD = "";

    /**
     * Returns a connection to the database.
     *
     * @return connection to a database.
     */
    public Connection getDbConnection() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (final ClassNotFoundException e) {
            System.out.println("Could not find the h2 driver class.");
        } catch (final SQLException e) {
            System.out.println("Could not connect to the DB.");
        }

        return connection;
    }
}
