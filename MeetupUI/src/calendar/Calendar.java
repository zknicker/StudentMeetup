package calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

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
    private static List<String> hours = new ArrayList<String>();

    /** Day and month params. */
	private String daySelection = "";
	private String monthSelection = "";
    
    static {
        for (int i = 1; i <= 31; i++) days.add(new SelectItem(String.valueOf(i)));
        for (int i = 1; i <= 12; i++) months.add(new SelectItem(String.valueOf(i)));
        for (int i = 0; i <= 23; i++) hours.add(String.format("%02d", i));
    }

    public List<SelectItem> getDays() {
        return days;
    }

    public List<SelectItem> getMonths() {
        return months;
    }
    public List<String> getHours() {
        return hours;
    }

    public void setMonthSelection(String newMonthToView) {
    	monthSelection = newMonthToView;
	}
	public String getMonthSelection() {
		if (monthSelection.isEmpty()) {
		    java.util.Calendar calendar = java.util.Calendar.getInstance();
		    calendar.setTime(new Date());
		    int month = calendar.get(java.util.Calendar.MONTH);
		    return Integer.toString(month + 1);
		}
		return monthSelection;
	}
	
	public void setDaySelection(String newDayToView) {
		daySelection = newDayToView;
	}
	public String getDaySelection() {
		if (daySelection.isEmpty()) {
		    java.util.Calendar calendar = java.util.Calendar.getInstance();
		    calendar.setTime(new Date());
		    int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
		    return Integer.toString(day + 1);
		}
		return daySelection;
	}
    
	public List<String> getEventsByDateTime(String year, String month, String day, String hour) {
		return calendarService.getEventsByDateTime(year, month, day, hour);
	}
}

