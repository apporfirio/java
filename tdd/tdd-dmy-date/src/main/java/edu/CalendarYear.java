package edu;

public class CalendarYear {
	private static final int FIRST_CALENDAR_YEAR = 1583; 

	private int year;
	
	public CalendarYear() {
		this(FIRST_CALENDAR_YEAR);
	}
	
	public CalendarYear(int year) throws InvalidDMYDate {
		if (year < FIRST_CALENDAR_YEAR) {
			throw new InvalidDMYDate("year value of " + year + " must be greater than or equal to " + FIRST_CALENDAR_YEAR);
		}
		
		this.year = year;
	}
	
	public boolean isLeapYear() {
		if (year % 4 != 0) {
			return false;
		}

		if (year % 100 == 0 && year % 400 != 0) {
			return false;
		}
		
		return true;
	}
	
	public int getTotalDays() {
		if (isLeapYear())
			return 366;
		
		return 365;
	}
	
	public int getValue() {
		return year;
	}
	
	public CalendarYear next() {
		return new CalendarYear(year + 1);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (other instanceof CalendarYear) {
			return year == ((CalendarYear) other).getValue();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "" + year;
	}
}
