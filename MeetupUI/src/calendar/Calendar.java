package calendar;

import java.util.List;

import javax.ejb.EJB;

import services.*;

/**
 * Returns Events For Calendar Display.
 */
public class Calendar
{
	@EJB 
	private CalendarService calendarService;
	

	public List<String> getEventsByDateTime(String year, String month, String day, String hour) {
		return calendarService.getEventsByDateTime(year, month, day, hour);
	}
}

