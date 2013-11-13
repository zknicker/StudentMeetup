package entities;

import java.io.*;

import javax.persistence.*;

/**
 * An object representing the Events table in the database.
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {

	/** Generated Serial Version UID. */
	private static final long serialVersionUID = 3011154584508161646L;

	/** Unique ID of the event. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 
	 * The user id of the user. 
	 * 
	 * Note: this still exists despite the Id attribute above such that
	 * change to our application's handling of User ID's in the future do
	 * not impact the table's health. It's best just to let the ORM have
	 * its own unique IDs, and manage our own separately. 
	 */
	@Column(name = "USER_ID")
	private Long userId;

	/** The user's usernaame. */
	@Column(name = "USERNAME")
	private String username;

	/** The user's first naame. */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/** Theuser's last name. */
	@Column(name = "LAST_NAME")
	private String lastName;

	/** The user's password (oh so terribly stored in plain text). */
	@Column(name = "PASSWORD")
	private String password;

	/** The user's email. */
	@Column(name = "EMAIL")
	private String email;

	/** 
	 * The user's notification preference. If true, the user has elected
	 * to receive email updates for various notifications.
	 */
	@Column(name = "NOTIFICATION_PREFERENCE")
	private boolean notificationPreference;

	/** 
	 * The user's rating.
	 */
	@Column(name = "RATING")
	private float rating;

	/**
	 * Gets the ID of the event.
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * Sets the ID of the event.
	 */
	public void setId(Long newID) {
		this.id = newID;
	}

	/**
	 * Gets the username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the event.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the event.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the notification preference of the user.
	 */
	public boolean getNotificationPreference() {
		return notificationPreference;
	}

	/**
	 * Sets the notification preference of the event.
	 */
	public void setNotificationPreference(boolean notificationPreference) {
		this.notificationPreference = notificationPreference;
	}
	
	/**
	 * Gets the rating of the user.
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * Sets the rating of the event.
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
}
