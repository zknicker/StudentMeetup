package User;

/**
 * A user in the application.  Also persisted in a database; this can be managed using
 * the ORM classes UserGateway and UserFinder.
 */
public class User {
	
	/** Unique User ID */
    public long userId;

    /** The user's handle. */
    public String handle;

    /** The user's first name. */
    public String firstName;

    /** The user's last name. */
    public String lastName;

    /** The user's password. */
    public String password;

    /** The user's email address. */
    public String email;

    /** The user's rating. */
    public double rating;

    /** The user's notification preference. */
    public boolean notificationPreference;
}
