package details;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Details that describe a given event. This is a stand-alone, no strings attached
 * data structure class for the client-side to read information about events.
 */
public class EventDetails {
	private Long id;
	private String name;
	private String description;
	private Long startTime;	
	private Long endTime;
	private String location;
	private String category;
	private int threshold;
	private int status;

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

	public void setStartime(Long time) {
		this.startTime = time;
	}

	public void setStarttime(String year, String month, String day, String hour, String minute) {
		this.startTime = getTimestamp(year, month, day, hour, minute);
	}
	
	public Long getStarttime() {
		return startTime;
	}

	public void setEndtime(Long time) {
		this.endTime = time;
	}

	public void setEndtime(String year, String month, String day, String hour, String minute) {
		this.endTime = getTimestamp(year, month, day, hour, minute);
	}

	public Long getEndtime() {
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

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
	
	/**
	 * Returns a timestamp for the given time information.
	 */
	private Long getTimestamp(String year, String month, String day, String hour, String minute) {
		
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date date;
		try {
			date = sdf.parse(year + "-" + month + "-" + day + "-" + hour + "-" + minute);
		} catch (ParseException e) {
			return 0L;
		}
		long millisSinceEpoch = date.getTime();
		long secondsSinceEpoch = millisSinceEpoch / 1000;
		return secondsSinceEpoch;
	}
}
