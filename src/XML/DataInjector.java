package xml;
import ORM.*;
import User.User;
import Event.Event;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.parsers.*;

public class DataInjector {
	
	public DataInjector()
	{	
	}
		
	public User ParseUserXML(String UserXMLDocName)

	{
		//  XML parser for User documents
			
			User user = new User();
		
		try{
					Document theDocument = FileRootDocumentGet(UserXMLDocName);						
					NodeList nodeList = theDocument.getElementsByTagName("user");
					
					for (int i = 0; i < nodeList.getLength(); i++) {
						 
						Node nNode = nodeList.item(i);
				 		 
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				 
							Element eElement = (Element) nNode;
				 
							user.userId = Long.parseLong(eElement.getAttribute("id"));
							user.handle = eElement.getElementsByTagName("username").item(0).getTextContent();							
							user.firstName = eElement.getElementsByTagName("firstname").item(0).getTextContent();
							user.lastName = eElement.getElementsByTagName("lastname").item(0).getTextContent();
							user.password = eElement.getElementsByTagName("password").item(0).getTextContent();
							user.email = eElement.getElementsByTagName("email").item(0).getTextContent();
							user.rating = Float.parseFloat(eElement.getElementsByTagName("rating").item(0).getTextContent());
							
							NodeList preferences = eElement.getElementsByTagName("preferences");
							
							   for ( i = 0; i < preferences.getLength(); i++) {
							  
								  Node pNode = preferences.item(i);
								
							  	if (pNode.getNodeType() == Node.ELEMENT_NODE) {
									Element eElement1 = (Element) pNode;
									user.notificationPreference =  Boolean.parseBoolean(eElement1.getElementsByTagName("emailNotifications").item(0).getTextContent());
							   	}
							}

						}
						
						
						}
									
					System.out.println("Parsing complete");
				} catch (Exception E){
					System.out.println("Error in parsing");
				}
		      return user;
			}


public Event ParseEventXML(String EventXMLDocName)

{
	//  XML parser for Event documents
	Event event = new Event();
	
	
	try{
		Document theDocument = FileRootDocumentGet(EventXMLDocName);	
		NodeList nodeList = theDocument.getElementsByTagName("event");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			 
			Node nNode = nodeList.item(i);
	 		 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	 
				event.eventId = Long.parseLong(eElement.getAttribute("id"));
				event.startTime = eElement.getAttribute("startTime");							
				event.endTime = eElement.getAttribute("endTime");
				event.title = eElement.getElementsByTagName("title").item(0).getTextContent();
				event.eventDescription = eElement.getElementsByTagName("description").item(0).getTextContent();							
				event.location = eElement.getElementsByTagName("location").item(0).getTextContent();
				event.category = eElement.getElementsByTagName("category").item(0).getTextContent();
				event.confirmationStatus = Integer.parseInt(eElement.getElementsByTagName("confirmationStatus").item(0).getTextContent());
				event.thresholdNumber = Integer.parseInt(eElement.getElementsByTagName("thresholdNumber").item(0).getTextContent());
			}
		}
			System.out.println("Parsing complete");
			} catch (Exception E){
				System.out.println("Error in parsing");
			}
	
	return event;

}



		    public static Document FileRootDocumentGet(String FileName) 
		    	      throws Exception{
		    		Document TheDocument=null;
		    		DocumentBuilderFactory factory =
		    				    DocumentBuilderFactory.newInstance();
		    		DocumentBuilder parser = factory.newDocumentBuilder();
		    		TheDocument = parser.parse(FileName);
		    		return(TheDocument);
		    }
		    
		    public static Node DocumentNodeGet(Document TheDocument){
		    	Node theNode=null;
		    	theNode = TheDocument.getDocumentElement();
		    	return(theNode);
		    }
		    
		    public static Node[] NodeElementSelectManyGivenName(Node TheNode,
		    		String TheName){
		    	int count = 0;
		    	Node[] SelectedNodes=null;

		    	NodeList children = TheNode.getChildNodes();

		    	if (children != null) {
		    		int len = children.getLength();
		    		for (int i = 0; i < len; i++) {
		    			short nodeType=children.item(i).getNodeType();
		    			Node oneChild=children.item(i);
		    			if (nodeType == Node.ELEMENT_NODE){
		    				String nodeName = oneChild.getNodeName();
		    				if(nodeName.equals(TheName)){
		    					count++;
		    				}
		    			}
		    		}

		    		SelectedNodes = new Node[count];
		    		count = 0;
		    		for (int i = 0; i < len; i++) {
		    			short nodeType=children.item(i).getNodeType();
		    			Node oneChild=children.item(i);
		    			if (nodeType == Node.ELEMENT_NODE){
		    				String nodeName = oneChild.getNodeName();
		    				if(nodeName.equals(TheName)){
		    					SelectedNodes[count] = oneChild; count++;
		    				}
		    			}
		    		}

		    	}

		    	return(SelectedNodes);
		    }

		    public static String NodeAttributeValueGet(Node TheNode, 
		    		String AttributeName){
		    	String theValue = null;
		    	NamedNodeMap attrs = TheNode.getAttributes();
		    	Attr nameAttribute = (Attr)attrs.getNamedItem(AttributeName);
		    	if(nameAttribute != null) theValue = nameAttribute.getValue();
		    	return theValue;
		    }

		    public static Node[] NodeSelectManyGivenAttributeValue(Node[] Nodes, 
		    		String AttributeName, 
		    		String Value){
		    	Node[] selectedNodes = null;
		    	ArrayList<Node> selectedNodesList = new ArrayList<Node>();

		    	int len = Nodes.length;
		    	for(int i = 0; i < len; i++){
		    		String AttributeValue = NodeAttributeValueGet(Nodes[i],
		    				AttributeName);
		    		if(AttributeValue.equals(Value)){
		    			selectedNodesList.add(Nodes[i]);
		    		}
		    	}
		    	selectedNodes = new Node[len = selectedNodesList.size()];

		    	for(int i = 0; i < len; i++){
		    		selectedNodes[i] = (Node)selectedNodesList.get(i);
		    	}

		    	return selectedNodes;
		    }
	

}

