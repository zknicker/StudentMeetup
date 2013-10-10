package XML;

/**
 * Exception thrown on error parsing XML.
 */
public class XMLParsingException extends Exception {

    /** Serial Version UID. */
    private static final long serialVersionUID = -1031581014959650693L;

    /**
     * Throws an XMLParsingException with a message.
     *
     * @param message - the message to throw with the exception.
     */
    public XMLParsingException(String message) {
        super(message);
    }

    /**
     * Throws an XMLParsingException with a message and a {@link Throwable}.
     *
     * @param message - the message to throw with the exception.
     * @param throwable - execution stack and other data to throw with the exception.
     */
    public XMLParsingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
