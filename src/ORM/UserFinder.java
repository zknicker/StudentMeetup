package ORM;

import java.sql.*;
import java.util.*;

import ORM.DatabaseConnection;
import ORM.UserGateway;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UserFinder {
	
	public UserFinder()
	{}
	

	public UserGateway findUser(long userId) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE USER_ID = " + userId) ;
        results.next();
       
        UserGateway userGateway = UserGateway.load(results);
        statement.close();
   
		return userGateway;
	}
	
	
	public List<UserGateway> findUsersByNotificationPreference(boolean notificationPreference) throws SQLException {
		
		Connection connection = DatabaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
  	
        List<UserGateway> result = new ArrayList<UserGateway>();
        ResultSet results = statement.executeQuery("SELECT * FROM USERS WHERE NOTIFICATION_PREFERENCE = " + notificationPreference) ;
       
        while (results.next())
        {
        UserGateway userGateway = UserGateway.load(results);
        result.add(userGateway);
        }
        statement.close();
   
		return result;
	}
	
}
	
	