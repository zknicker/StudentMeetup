package XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

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
	 * Validates XML against its DTD and provided Schema. If no exceptions are thrown,
     * the XML should be considered valid.
	 * 
	 * @param xmlFile The XML file to validate.
	 * @throws InvalidXMLException on error validating XML.
     * @throws XMLParsingException on error reading/parsing XML.
	 */
	private void validate(File xmlFile, File schemaFile) throws InvalidXMLException, XMLParsingException {

		Document document = getParsedXMLDocument(xmlFile);
        Schema schema = getSchema(schemaFile);

	    // Create the XML validator using the schema.
	    Validator validator = schema.newValidator();
	
	    // Validate the XML document. XML is valid if no exceptions are thrown.
	    try {
	        validator.validate(new DOMSource(document));

	    } catch (SAXException e) {
	        throw new InvalidXMLException("Invalid XML: " + e.getMessage(), e);
	    } catch (IOException e) {
            throw new XMLParsingException("Error reading the XML document: " + e.getMessage(), e);
        }
	}

    /**
     * Parses an XML document and returns the {@link Document} object.
     *
     * @param xmlFile - the XML file to parse.
     * @throws XMLParsingException on error parsing the XML.
     * @return the document object representing the parsed XML.
     */
    private Document getParsedXMLDocument(File xmlFile) throws XMLParsingException {
        try {
            DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
            parserFactory.setValidating(true);
            DocumentBuilder db = parserFactory.newDocumentBuilder();
            db.setErrorHandler(getErrorHandler());
            return db.parse(xmlFile);

        } catch (final ParserConfigurationException e) {
            throw new XMLParsingException("Error configuring XML parser: " + e.getMessage(), e);

        } catch (final IOException e) {
            throw new XMLParsingException("Error loading the XML file: " + e.getMessage(), e);

        } catch (final SAXException e) {
            throw new XMLParsingException("Error parsing XML document: " + e.getMessage(), e);
        }
    }

    /**
     * Opens and parses an XML schema file into a {@link Schema} object.
     *
     * @param schemaFile - the schema file.
     * @return the schema object representing the given schema.
     * @throws XMLParsingException on error opening and parsing the schema.
     */
    private Schema getSchema(File schemaFile) throws XMLParsingException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaStream = new StreamSource(schemaFile);
            return factory.newSchema(schemaStream);

        } catch (final SAXException e) {
            throw new XMLParsingException("Error loading or parsing XML schema: " + e.getMessage(), e);
        }
    }

    /**
     * Returns a primitive error handler class used in parsing XML documents.
     *
     * <i>This is kind of hacky, but since we're not too concerned about this particular
     * aspect of the XML parsing, at least for the moment, it's going to be ignored.</i>
     *
     * @return the error handler anonymous class.
     */
    private ErrorHandler getErrorHandler() {
        ErrorHandler primitiveErrorHandler = new ErrorHandler()
        {
            public void fatalError(SAXParseException e) throws SAXException {
                System.err.println("Fatal error parsing XML: " + e.getMessage());
            }

            public void error(SAXParseException e) throws SAXException {
                System.err.println("Error parsing XML: " + e.getMessage());
            }

            public void warning(SAXParseException e) throws SAXException {
                System.err.println("XML parser warning: " + e.getMessage());
            }
        };

        return primitiveErrorHandler;
    }

	/**
	 * Validate user.xml and event.xml as a demonstration.
	 * 
	 * @param args arguments to the main method.
	 * @throws Exception on error validating XML, or when XML is invalid.
	 */
	public static void main(String[] args) throws Exception {

        XMLValidator validator = new XMLValidator();
        File schemaFile = new File("xml/meetup.xsd");

		try {
            System.out.println("Validating user.xml ...");
            validator.validate(new File("xml/user.xml"), schemaFile);
            System.out.println("user.xml is valid!");
		} catch (Exception e) {
            System.out.println("user.xml is invalid!\n" + e.getMessage());
        }

        try {
            System.out.println("\nValidating event.xml ...");
            validator.validate(new File("xml/event.xml"), schemaFile);
            System.out.println("event.xml is valid!");
        } catch (Exception e) {
            System.out.println("event.xml is invalid!\n" + e.getMessage());
        }
	}
}
