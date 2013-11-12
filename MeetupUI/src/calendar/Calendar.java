package calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import details.EventDetails;
import services.*;

/**
 * Returns Events For Calendar Display.
 */
public class Calendar
{
	@EJB 
	private CalendarService calendarService;
	
	/** Arrays of days and months for the select boxes. */
    private static List<SelectItem> days = new ArrayList<SelectItem>();
    private static List<SelectItem> months = new ArrayList<SelectItem>();

    /** Day and month params. */
	private String daySelection = "";
	private String monthSelection = "";
    
	/** Static initializer for lists of days and months. */
    static {
        for (int i = 1; i <= 31; i++) days.add(new SelectItem(String.valueOf(i)));
        for (int i = 1; i <= 12; i++) months.add(new SelectItem(String.valueOf(i)));
    }

    /**
     * Returns a list of days.
     */
    public List<SelectItem> getDays() {
        return days;
    }

    /**
     * Returns a list of months.
     */
    public List<SelectItem> getMonths() {
        return months;
    }

    /**
     * Sets the user's desired month to view.
     */
    public void setMonthSelection(String newMonthToView) {
    	monthSelection = newMonthToView;
	}
    
    /**
     * Get's the user's desired month to view.
     */
	public String getMonthSelection() {
		if (monthSelection.isEmpty()) {
		    java.util.Calendar calendar = java.util.Calendar.getInstance();
		    calendar.setTime(new Date());
		    int month = calendar.get(java.util.Calendar.MONTH);
		    return Integer.toString(month + 1);
		}
		return monthSelection;
	}
	
	/**
	 * Sets the user's desired day to view.
	 */
	public void setDaySelection(String newDayToView) {
		daySelection = newDayToView;
	}
	
	/**
	 * Gets the user's desired day to view.
	 */
	public String getDaySelection() {
		if (daySelection.isEmpty()) {
		    java.util.Calendar calendar = java.util.Calendar.getInstance();
		    calendar.setTime(new Date());
		    int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
		    return Integer.toString(day + 1);
		}
		return daySelection;
	}
    
	/**
	 * Get's a list of events for a given year, month, and day. 
	 * @throws ParseException if the year, month, or day is invalid.
	 */
	public List<EventDetails> getEventsByDateTime(String year, String month, String day) throws ParseException {
		return calendarService.getEvents(year, month, day);
	}
	
	/**
	 * Returns a pretty representation of a unix timestamp. Useful when
	 * display event information using an {@link EventDetails} object.
	 */
	@SuppressWarnings("deprecation")
	public String getPrettyTime(Long timestamp) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		Date date = new Date(timestamp * 1000);
		calendar.setTime(date);
		
		int hour = calendar.get(java.util.Calendar.HOUR);
		int minute = calendar.get(java.util.Calendar.MINUTE);
		int amPm = calendar.get(java.util.Calendar.AM_PM);
		
		return String.format("%d:%s%s", hour, String.format("%02d", minute), (amPm == 0 ? "AM" : "PM"));
	}
}

