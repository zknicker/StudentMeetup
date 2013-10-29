package Interaction;

import Event.Event;
import Database.DatabaseConnection;
import Database.EventGateway;
import Database.UserGateway;
import User.User;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import XML.DataInjector;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Takes in command line arguments and then uses the XMLInjector class to inject the
 * XML into domain-space classes, and then persists those objects in the database.
 * 
 * The command line arguments should be relative paths to the XML files.
 */
public class XMLInjector {

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to the Student Meetup application.");

		Connection connection = DatabaseConnection.getDatabaseConnection();
		Statement statement = connection.createStatement();

		// Create the Users table if it does not exist.
		statement.execute("DROP TABLE IF EXISTS USERS");
		statement
				.execute("CREATE TABLE IF NOT EXISTS USERS("
						+ "USER_ID LONG PRIMARY KEY," + "HANDLE VARCHAR(255),"
						+ "FIRST_NAME VARCHAR(255),"
						+ "LAST_NAME VARCHAR(255)," + "PASSWORD VARCHAR(255),"
						+ "EMAIL VARCHAR(255)," + "RATING DOUBLE,"
						+ "NOTIFICATION_PREFERENCE VARCHAR(5)" + ")");

		// Create the Events table if it does not exist.
		statement.execute("DROP TABLE IF EXISTS EVENTS");
		statement.execute("CREATE TABLE IF NOT EXISTS EVENTS("
				+ "EVENT_ID LONG PRIMARY KEY," + "TITLE VARCHAR(255),"
				+ "DESCRIPTION VARCHAR(255)," + "LOCATION VARCHAR(255),"
				+ "CATEGORY VARCHAR(255)," + "START_TIME VARCHAR(255),"
				+ "END_TIME VARCHAR(255)," + "STATUS INT," + "THRESHOLD INT"
				+ ")");

		// Create the Subscriptions table if it does not exist.
		statement.execute("DROP TABLE IF EXISTS SUBSCRIPTIONS");
		statement.execute("CREATE TABLE IF NOT EXISTS SUBSCRIPTIONS("
				+ "USER_ID LONG," + "EVENT_ID LONG," + "EMAIL_NOTIFY BOOLEAN,"
				+ "ATTENDING_STATUS INT,"
				+ "FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),"
				+ "FOREIGN KEY (EVENT_ID) REFERENCES EVENTS(EVENT_ID),"
				+ "PRIMARY KEY (USER_ID, EVENT_ID)" + ")");

		// Create the User Relationships table if it does not exist.
		statement.execute("DROP TABLE IF EXISTS USER_RELATIONSHIPS");
		statement.execute("CREATE TABLE IF NOT EXISTS USER_RELATIONSHIPS("
				+ "USER_ID LONG," + "RELATED_USER_ID LONG," + "STATUS INT,"
				+ "FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),"
				+ "FOREIGN KEY (RELATED_USER_ID) REFERENCES USERS	(USER_ID),"
				+ "PRIMARY KEY (USER_ID, RELATED_USER_ID)" + ")");
		statement.execute("CREATE USER IF NOT EXISTS root PASSWORD 'alpine'");
		statement.execute("GRANT ALL ON USERS TO root");
		statement.execute("GRANT ALL ON EVENTS TO root");

		// Process XML files if included on command line

		for (int i = 0; i < args.length; i++) {
			DataInjector dataInjector = new DataInjector();

			try {
				Document theDocument = DataInjector.FileRootDocumentGet(args[i]);
				Node documentNode = DataInjector.DocumentNodeGet(theDocument);

				if ("users".equals(documentNode.getNodeName())) {
					dataInjector.ParseUserXML(args[i]);
				}
				else if ("events".equals(documentNode.getNodeName())) {
					dataInjector.ParseEventXML(args[i]);
				} else
					System.out.println("Unknown XML document.");

			} catch (Exception E) {
				System.out.println("Error in parsing");
			}
		}
		
		statement.close();
		connection.close();
	}

}
