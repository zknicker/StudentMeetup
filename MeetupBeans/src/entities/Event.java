package entities;

import java.io.*;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * An object representing the Events table in the database.
 */
@Entity
@Table(name = "Events")
public class Event implements Serializable {

	/** Generated Serial Version UID. */
	private static final long serialVersionUID = -6559004705492527656L;

	/** Unique ID of the event. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name of the event. */
	@Column(name = "NAME")
	private String name;

	/** The description for the event. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The start time of the event. */
	@Column(name = "STARTTIME")
	private Long startTime;

	/** The end time of the event. */
	@Column(name = "ENDTIME")
	private Long endTime;

	/** The location of the event. */
	@Column(name = "LOCATION")
	private String location;

	/** The category of the event. */
	@Column(name = "CATEGORY")
	private String category;

	/** 
	 * The threshold for the event. AKA the minimum number of users
	 * required for this event to actually take place.
	 */
	@Column(name = "THRESHOLD")
	private int threshold;

	/** 
	 * The status of the event. Different numbers represent different
	 * states of the event (e.g. event has met threshold, event is
	 * canceled, etc.)
	 */
	@Column(name = "STATUS")
	private int status;

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
	 * Gets the name of the event.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the event.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Gets the description of the event.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the event.
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	/**
	 * Sets the start time of the event.
	 */
	public void setStartime(Long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the start time of the event.
	 */
	public Long getStarttime() {
		return startTime;
	}

	/**
	 * Sets the end time of the event.
	 */
	public void setEndtime(Long endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the end time of the event.
	 */
	public Long getEndtime() {
		return endTime;
	}

	/**
	 * Sets the location of the event.
	 */
	public void setLocation(String newLocation) {
		location = newLocation;
	}

	/**
	 * Gets the location of the event.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the category of the event.
	 * @param newCategory
	 */
	public void setCategory(String newCategory) {
		category = newCategory;
	}

	/**
	 * Gets the category of the event.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the threshold of the event.
	 */
	public void setThreshold(int newThreshold) {
		threshold = newThreshold;
	}

	/**
	 * Gets the threshold of the event.
	 */
	public int getThreshold() {
		return threshold;
	}
	
	/**
	 * Sets the status of the event.
	 */
	public void setStatus(int newStatus) {
		status = newStatus;
	}

	/**
	 * Gets the status of the event.
	 */
	public int getStatus() {
		return status;
	}
}
