package XML;

import Event.Event;
import User.User;
import Database.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

/**
 * Provides functionality to inject data from an XML document into the
 * application.
 */

public class DataInjector {

	public DataInjector() {}

	public void ParseUserXML(String UserXMLDocName) {
		// XML parser for User documents
		try {
			Document theDocument = FileRootDocumentGet(UserXMLDocName);
			NodeList nodeList = theDocument.getElementsByTagName("user");

			for (int i = 0; i < nodeList.getLength(); i++) {

				User user = new User();
				Node nNode = nodeList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					user.userId = Long.parseLong(eElement.getAttribute("id"));
					user.handle = eElement.getElementsByTagName("username")
							.item(0).getTextContent();
					user.firstName = eElement.getElementsByTagName("firstName")
							.item(0).getTextContent();
					user.lastName = eElement.getElementsByTagName("lastName")
							.item(0).getTextContent();
					user.password = eElement.getElementsByTagName("password")
							.item(0).getTextContent();
					user.email = eElement.getElementsByTagName("email").item(0)
							.getTextContent();
					user.rating = Float.parseFloat(eElement
							.getElementsByTagName("rating").item(0)
							.getTextContent());

					NodeList preferences = eElement
							.getElementsByTagName("preferences");

					for (int j = 0; j < preferences.getLength(); j++) {

						Node pNode = preferences.item(j);

						if (pNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement1 = (Element) pNode;
							user.notificationPreference = Boolean
									.parseBoolean(eElement1
											.getElementsByTagName(
													"emailNotifications")
											.item(0).getTextContent());
						}
					}

				}
				UserGateway userGateway = UserGateway.create(user);
				userGateway.insert();
			}

			System.out.println("Parsing complete");
		} catch (Exception E) {
			System.out.println("Error in parsing");
		}
	}

	public void ParseEventXML(String EventXMLDocName) {
		// XML parser for Event documents
		Event event = new Event();

		try {
			Document theDocument = FileRootDocumentGet(EventXMLDocName);
			NodeList nodeList = theDocument.getElementsByTagName("event");

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node nNode = nodeList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					event.eventId = Long.parseLong(eElement.getAttribute("id"));
					event.startTime = eElement.getAttribute("startTime");
					event.endTime = eElement.getAttribute("endTime");
					event.title = eElement.getElementsByTagName("title")
							.item(0).getTextContent();
					event.eventDescription = eElement
							.getElementsByTagName("description").item(0)
							.getTextContent();
					event.location = eElement.getElementsByTagName("location")
							.item(0).getTextContent();
					event.category = eElement.getElementsByTagName("category")
							.item(0).getTextContent();
					event.status = Integer.parseInt(eElement
							.getElementsByTagName("status").item(0)
							.getTextContent());
					event.threshold = Integer.parseInt(eElement
							.getElementsByTagName("threshold").item(0)
							.getTextContent());
				}

				EventGateway eventGateway = EventGateway.create(event);
				eventGateway.insert();
			}
			System.out.println("Parsing complete");
		} catch (Exception E) {
			System.out.println("Error in parsing");
		}

	}

	public static Document FileRootDocumentGet(String FileName)
			throws Exception {
		Document TheDocument = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		TheDocument = parser.parse(FileName);
		return (TheDocument);
	}

	public static Node DocumentNodeGet(Document TheDocument) {
		Node theNode = null;
		theNode = TheDocument.getDocumentElement();
		return (theNode);
	}

	public static Node[] NodeElementSelectManyGivenName(Node TheNode,
			String TheName) {
		int count = 0;
		Node[] SelectedNodes = null;

		NodeList children = TheNode.getChildNodes();

		if (children != null) {
			int len = children.getLength();
			for (int i = 0; i < len; i++) {
				short nodeType = children.item(i).getNodeType();
				Node oneChild = children.item(i);
				if (nodeType == Node.ELEMENT_NODE) {
					String nodeName = oneChild.getNodeName();
					if (nodeName.equals(TheName)) {
						count++;
					}
				}
			}

			SelectedNodes = new Node[count];
			count = 0;
			for (int i = 0; i < len; i++) {
				short nodeType = children.item(i).getNodeType();
				Node oneChild = children.item(i);
				if (nodeType == Node.ELEMENT_NODE) {
					String nodeName = oneChild.getNodeName();
					if (nodeName.equals(TheName)) {
						SelectedNodes[count] = oneChild;
						count++;
					}
				}
			}

		}

		return (SelectedNodes);
	}
}