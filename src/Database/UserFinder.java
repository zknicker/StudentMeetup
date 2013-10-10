package Database;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Finds rows of users in the database for different critera. In all cases, the row object model,
 * UserGateway, is returned either as a single object or a list.
 */
public class UserFinder {

    /**
     * Finds a user by unique user ID.
     *
     * @param userId - the user ID of the user to find.
     * @return the user corresponding to the supplied user ID.
     * @throws SQLException on error querying the database.
     */
	public UserGateway findUser(long userId) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE USER_ID = " + userId) ;
        results.next();
       
        UserGateway userGateway = UserGateway.create(results);
        statement.close();
   
		return userGateway;
	}

    /**
     * Finds a user by their notification preference. For example, one could find all users with
     * a notification preference set to "true", where said users would be electing to receive
     * email notifications.
     *
     * @param notificationPreference - the notification preference desired.
     * @return users with said notification preference as a list.
     * @throws SQLException on error querying the database.
     */
	public List<UserGateway> findUsersByNotificationPreference(boolean notificationPreference) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        List<UserGateway> result = new ArrayList<UserGateway>();
        ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE NOTIFICATION_PREFERENCE = " +
                notificationPreference) ;
       
        while (results.next())
        {
            UserGateway userGateway = UserGateway.create(results);
            result.add(userGateway);
        }
        statement.close();
   
		return result;
	}
	
}
	
	