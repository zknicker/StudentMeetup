package XML;

/**
 * Exception thrown when XML is determined to be invalid.
 */
public class InvalidXMLException extends Exception {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2676324315337310299L;

	/**
	 * Throws an InvalidXMLException with a message.
	 * 
	 * @param message - the message to throw with the exception.
	 */
	public InvalidXMLException(String message) {
        super(message);
    }

	/**
	 * Throws an InvalidXMLException with a message and a {@link Throwable}.
	 * 
	 * @param message - the message to throw with the exception.
	 * @param throwable - execution stack and other data to throw with the exception.
	 */
    public InvalidXMLException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
