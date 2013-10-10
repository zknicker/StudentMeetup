package XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Validates XML against a schema and DTD.
 */
public class XMLValidator {
	
	/**
	 * Returns true if the inputted XML file is valid, false otherwise.
	 * Validates against DTD and Schema, as well as core XML syntax.
	 * 
	 * @param xmlFile The XML file to validate.
	 * @return true if valid, false otherwise.
	 * @throws ParserConfigurationException if the parser cannot be setup.
	 * @throws IOException on failure reading XML file to parse.
	 * @throws SAXException on generic error XML file during parsing.
	 * @throws InvalidXMLException if the inputed XML is invalid.
	 */
	private static boolean xmlIsValid(File xmlFile) throws ParserConfigurationException, 
			SAXException, IOException, InvalidXMLException {
		
		// parse an XML document into a DOM tree
		DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
		parserFactory.setValidating(true);
		DocumentBuilder parser = parserFactory.newDocumentBuilder();
	    Document document = parser.parse(xmlFile);
	
	    // create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	    // load a WXS schema, represented by a Schema instance
	    Source schemaFile = new StreamSource(new File("xml/meetup.xsd"));
	    Schema schema = factory.newSchema(schemaFile);
	
	    // create a validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();
	
	    // validate the DOM tree
	    try {
	        validator.validate(new DOMSource(document));
	    } catch (SAXException e) {
	        throw new InvalidXMLException("Invalid XML!", e);
	    }
	    
	    return true;
	}
	
	/**
	 * Validate user.xml and event.xml for Lab 2.
	 * 
	 * @param args arguments to the main method.
	 * @throws Exception on error validating.
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Validating user.xml ...");
		if(xmlIsValid(new File("xml/user.xml"))) {
			System.out.println("user.xml is valid!");
		}
		
		System.out.println("Validating event.xml ...");
		if(xmlIsValid(new File("xml/event.xml"))) {
			System.out.println("event.xml is valid!");
		}
	}
}
