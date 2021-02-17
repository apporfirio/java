package edu;

public class CalendarDay {
	private int day;
	
	public CalendarDay() {
		this(1);
	}

	public CalendarDay(int day) throws InvalidDMYDate {
		if (day < 1 || day > 31) {
			throw new InvalidDMYDate("day value of " + day + " is not in the range 1, 2, ..., 31");
		}
		
		this.day = day;
	}
	
	public int getValue() {
		return day;
	}
	
	public boolean equals(int day) {
		return this.day == day;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (other instanceof CalendarDay) {
			return day == ((CalendarDay) other).getValue();
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "" + day;
	}
}
