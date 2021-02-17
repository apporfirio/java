package edu;

public class LeapYearValidator {

	private static final int GREGORIAN_CALENDAR_STARTER_YEAR = 1582;
	
	public LeapYearValidation validate(int year) {
		if (year < GREGORIAN_CALENDAR_STARTER_YEAR) {
			throw new IllegalArgumentException("year value must be greater or equal than " + GREGORIAN_CALENDAR_STARTER_YEAR);
		}

		if (year % 4 != 0) {
			return new LeapYearValidation(false, "Not leap year: it is not divisible by 4");
		}
		
		if (year % 100 == 0 && year % 400 != 0) {
			return new LeapYearValidation(false, "Not leap year: it is divisible by 4 and by 100 but not by 400");
		}
		
		return new LeapYearValidation(true, "Leap year");
	}
	
	
	public static class LeapYearValidation {
		
		private boolean isLeapYear;
		private String description;
		
		public LeapYearValidation() {
			isLeapYear = false;
			description = "";
		}
		
		public LeapYearValidation(boolean isLeapYear, String description) {
			this.isLeapYear = isLeapYear;
			this.description = description;
		}
		
		public boolean isLeapYear() {
			return isLeapYear;
		}
		public void setLeapYear(boolean isLeapYear) {
			this.isLeapYear = isLeapYear;
		}
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			
			if (other instanceof LeapYearValidation) {
				LeapYearValidation otherValidation = (LeapYearValidation) other;
				return isLeapYear == otherValidation.isLeapYear() && description.equals(otherValidation.getDescription());
			}
			
			return false;
		}
	}
}
