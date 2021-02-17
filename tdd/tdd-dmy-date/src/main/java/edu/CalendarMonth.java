package edu;

public class CalendarMonth {
	private int month;
	
	public CalendarMonth() {
		this(1);
	}

	public CalendarMonth(int month) throws InvalidDMYDate {
		if (month < 1 || month > 12) {
			throw new InvalidDMYDate("month value of " + month + " is not in the range 1, 2, ..., 12");
		}

		this.month = month;
	}
	
	public boolean isFebruary() {
		return month == 2;
	}
	
	public boolean hasMax30Days() {
		return (
			month == 4 ||
			month == 6 ||
			month == 9 ||
			month == 11
		);
	}
	
	public boolean hasMax31Days() {
		return 	(
			month == 1  || 
			month == 3  || 
			month == 5  || 
			month == 7  || 
			month == 8  || 
			month == 10 || 
			month == 12
		);
	}

	public int getTotalDays(boolean isLeapYear) {
		if (hasMax31Days())
			return 31;
		
		if (hasMax30Days())
			return 30;
		
		if (isFebruary() && isLeapYear)
			return 29;
		
		return 28;
	}

	public int getValue() {
		return month;
	}
	
	public String getName() {
		switch (month) {
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			default:
				return "UNKNOWN_MONTH";
		}
	}
	
	public CalendarMonth next() {
		return new CalendarMonth(month + 1);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (other instanceof CalendarMonth) {
			return month == ((CalendarMonth) other).getValue();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "" + month;
	}
}
