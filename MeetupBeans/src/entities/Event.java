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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STARTTIME")
	private Long startTime;

	@Column(name = "ENDTIME")
	private Long endTime;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "THRESHOLD")
	private int threshold;

	public Long getId() {
		return id;
	}

	public void setId(Long newID) {
		this.id = newID;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	public void setStartime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStarttime() {
		return startTime;
	}

	public void setEndtime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndtime() {
		return endTime;
	}

	public void setLocation(String newLocation) {
		location = newLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setCategory(String newCategory) {
		category = newCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setThreshold(int newThreshold) {
		threshold = newThreshold;
	}

	public int getThreshold() {
		return threshold;
	}
}
