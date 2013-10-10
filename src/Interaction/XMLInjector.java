package Interaction;

import ORM.*;
import User.User;
import xml.DataInjector;
import Event.Event;

import java.sql.Connection;
import java.sql.Statement;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class XMLInjector {
	
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
    	
       for (int i = 0; i < args.length; i++)	
        {

	     DataInjector dataInjector = new DataInjector();
	     
		try{
		Document theDocument = DataInjector.FileRootDocumentGet(args[i]);
		Node documentNode = DataInjector.DocumentNodeGet(theDocument);
		
		if ("user".equals(documentNode.getNodeName()))
		{
			 User user = new User();
			 user = dataInjector.ParseUserXML(args[i]);
			 UserGateway userGateway = UserGateway.create(user);
		     userGateway.insert();
		}
		
		else if ("event".equals(documentNode.getNodeName()))
		{
			 Event event = new Event();
			 event = dataInjector.ParseEventXML(args[i]);
			 EventGateway eventGateway = EventGateway.create(event);
			 eventGateway.insert();
			}
		else 
			System.out.println("Unknown XML document.");
		
	} catch (Exception E){
		System.out.println("Error in parsing");
	}
        }
       statement.close() ;
       connection.close();
}
    
}
