package details;

import java.sql.Timestamp;

/**
 * Details that describe a given event. This is a stand-alone, no strings attached
 * data structure class for the client-side to read information about events.
 */
public class EventDetails {
	private Long id;
	private String name;
	private String description;
	private Timestamp startTime;	
	private Timestamp endTime;
	private String location;
	private String category;
	private String threshold;

	public Long getId() {
		return id;
	}

	public void setId(Long newID) {
		this.id = newID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartime(Timestamp time) {
		this.startTime = time;
	}

	public void setStarttime(String year, String month, String day, String hour, String minute) {
		this.startTime = getTimestamp(year, month, day, hour, minute);
	}
	
	public Timestamp getStarttime() {
		return startTime;
	}

	public void setEndtime(Timestamp time) {
		this.endTime = time;
	}

	public void setEndtime(String year, String month, String day, String hour, String minute) {
		this.endTime = getTimestamp(year, month, day, hour, minute);
	}

	public Timestamp getEndtime() {
		return endTime;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getThreshold() {
		return threshold;
	}
	
	/**
	 * Returns a timestamp for the given time information.
	 */
	private Timestamp getTimestamp(String year, String month, String day, String hour, String minute) {
		int convertedYear = Integer.parseInt(year) - 1900;
		int convertedMonth = Integer.parseInt(month) - 1;
		
		return new Timestamp(convertedYear, convertedMonth,
				Integer.parseInt(day), Integer.parseInt(hour),
				Integer.parseInt(minute), 0, 0);
	}
}
