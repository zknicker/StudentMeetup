package entities;
import java.io.*;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * An event object as represented in the database.
 */
@Entity
@Table(name = "Events")
public class Event implements Serializable{
	
	/** Generated Serial Version UID. */
	private static final long serialVersionUID = -6559004705492527656L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name="NAME")
	private String name;
	
	@Column (name="DESCRIPTION")
	private String description;
	
	@Column (name="STARTTIME")
	private Timestamp starttime;
	
	@Column (name="ENDTIME")
	private Timestamp endtime;
	
	@Column (name="LOCATION")
	private String location;
	
	@Column (name="CATEGORY")
	private String category;
	
	@Column (name="THRESHOLD")
	private int threshold;
	
	
	private String month,day,year,starthour,startminute,endhour,endminute;
	private String dayToView,monthToView,yearToView;
	
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
	
	public void setMonth(String newMonth) {
		month = newMonth;
	}
	public String getMonth() {
		return month;
	}
	
	public void setDay(String newDay) {
		day = newDay;
	}
	public String getDay() {
		return day;
	}
	
	public void setYear(String newYear) {
		year = newYear;
	}
	public String getYear() {
		return year;
	}
	
	public void setStartHour(String newStarthour) {
		starthour = newStarthour;
	}
	public String getStartHour() {
		return starthour;
	}
	
	public void setStartMinute(String newStartminute) {
		startminute = newStartminute;
	}
	public String getStartMinute() {
		return startminute;
	}
	
	public void setEndHour(String newEndHour) {
		endhour = newEndHour;
	}
	public String geEndHour() {
		return endhour;
	}
	
	public void setEndMinute(String newEndMinute) {
		endminute = newEndMinute;
	}
	public String getEndMinute() {
		return endminute;
	}
	
	public void setStartime(String newYear, String newMonth, String newDay, String newStartHour, String newStartMinute) {
		int convertedYear = Integer.parseInt(newYear) - 1900;
		int convertedMonth = Integer.parseInt(newMonth) - 1;
		starttime = new Timestamp(convertedYear,convertedMonth,Integer.parseInt(newDay),Integer.parseInt(newStartHour),Integer.parseInt(newStartMinute),0,0);
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	
	public void setEndtime(String newYear, String newMonth, String newDay, String newEndHour, String newEndMinute) {
		int convertedYear = Integer.parseInt(newYear) - 1900;
		int convertedMonth = Integer.parseInt(newMonth) - 1;
		endtime = new Timestamp(convertedYear,convertedMonth,Integer.parseInt(newDay),Integer.parseInt(newEndHour),Integer.parseInt(newEndMinute),0,0);
	}
	public Timestamp getEndtime() {
		return endtime;
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
	
	public void setThreshold(String newThreshold) {
		threshold = Integer.parseInt(newThreshold);
	}
	public int getThreshold() {
		return threshold;
	}
	
	public void setMonthToView(String newMonthToView) {
		monthToView = newMonthToView;
	}
	public String getMonthToView() {
		return monthToView;
	}
	
	public void setDayToView(String newDayToView) {
		dayToView = newDayToView;
	}
	public String getDayToView() {
		return dayToView;
	}
	
	public void setYearToView(String newYearToView) {
		yearToView = newYearToView;
	}
	public String getYearToView() {
		return yearToView;
	}
	
	
}
