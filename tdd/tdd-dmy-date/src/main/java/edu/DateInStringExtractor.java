package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateInStringExtractor {
	private CalendarDay day;
	private CalendarMonth month;
	private CalendarYear year;
	
	public DateInStringExtractor(String dmyDate) throws InvalidDMYDate {
		Pattern pattern = Pattern.compile("(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})");
		Matcher matcher = pattern.matcher(dmyDate);
		
		if (!matcher.matches()) {
			throw new InvalidDMYDate(
					"date '" + dmyDate + 
					"' does not matches the format dd/mm/yyyy" + 
					" where dd is the day with 1 or 2 digits, mm is the month with 1 or 2 digits and yyyy is the year with 4 digits"
			);
		}
		
		day = new CalendarDay(Integer.valueOf(matcher.group(1)));
		month = new CalendarMonth(Integer.valueOf(matcher.group(2)));
		year = new CalendarYear(Integer.valueOf(matcher.group(3)));
	}
	
	public CalendarDay getDay() {
		return day;
	}
	
	public CalendarMonth getMonth() {
		return month;
	}
	
	public CalendarYear getYear() {
		return year;
	}
}
