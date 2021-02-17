package edu;

public class DMYDate {

	private DMYDateState date;
	
	public DMYDate(int day, int month, int year) throws InvalidDMYDate {
		this.date = new DMYDateState(day, month, year);
	}
	
	public DMYDate(String date) throws InvalidDMYDate {
		this.date = new DMYDateState(date);
	}
	
	private DMYDate(int dateInDays) throws InvalidDMYDate {
		this.date = new DMYDateState(dateInDays);
	}

	public DMYDate plusDays(int daysToAdd) {
		if (daysToAdd < 0) {
			return minusDays(-daysToAdd);
		}
		
		return new DMYDate(date.getElapsedDays() + daysToAdd);
	}
	
	public DMYDate minusDays(int daysToSubtract) {
		int elapsedDays = date.getElapsedDays();
		
		if (daysToSubtract >= elapsedDays) {
			throw new InvalidDMYDate("cannot subtract " + daysToSubtract + " days from date " + toString() + ": date is not long enough");
		}
		
		return new DMYDate(elapsedDays - daysToSubtract);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (other instanceof DMYDate) {
			return toString().equals(((DMYDate) other).toString());
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return date.toString();
	}
	
	private static class DMYDateState {
		
		private CalendarDay day;
		private CalendarMonth month;
		private CalendarYear year;
		
		public DMYDateState(int dayValue, int monthValue, int yearValue) throws InvalidDMYDate {
			day = new CalendarDay(dayValue);
			month = new CalendarMonth(monthValue);
			year = new CalendarYear(yearValue);
			
			validate();
		}

		public DMYDateState(String date) throws InvalidDMYDate {
			DateInStringExtractor dateInStringExtractor = new DateInStringExtractor(date);
			
			day = dateInStringExtractor.getDay(); 
			month = dateInStringExtractor.getMonth();
			year = dateInStringExtractor.getYear();

			validate();
		}
		
		
		public DMYDateState(int totalDays) throws InvalidDMYDate {
			DateInDaysExtractor dateInDaysExtractor = new DateInDaysExtractor(totalDays);
			
			day = dateInDaysExtractor.getDay(); 
			month = dateInDaysExtractor.getMonth();
			year = dateInDaysExtractor.getYear();
			
			// a extracao incorpora as regras de validacao
		}
		
		private void validate() throws InvalidDMYDate {
			if (day.equals(31) && !month.hasMax31Days()) {
				throw new InvalidDMYDate(month.getName() + " 31 does not exist");
			}
			
			if ((day.equals(30) || day.equals(31)) && month.isFebruary()) {
				throw new InvalidDMYDate(month.getName() + " 30 does not exist");
			}
			
			if (day.equals(29) && month.isFebruary() && !year.isLeapYear()) {
				throw new InvalidDMYDate(month.getName() + " 29 does not exist in year " + year.getValue());
			}
		}
		
		public int getElapsedDays() {
			return getPreviousYearsElapsedDays() + getCurrentYearElapsedDays();
		}
		
		private int getPreviousYearsElapsedDays() {
			int elapsedDays = 0;
			CalendarYear elapsedYear = new CalendarYear();
			
			while (!elapsedYear.equals(year)) {
				elapsedDays += elapsedYear.getTotalDays();
				elapsedYear = elapsedYear.next();
			}
			
			return elapsedDays;
		}
		
		private int getCurrentYearElapsedDays() {
			int elapsedDays = 0;
			CalendarMonth elapsedMonth = new CalendarMonth();
			
			while (!elapsedMonth.equals(month)) {
				elapsedDays += elapsedMonth.getTotalDays(year.isLeapYear());
				elapsedMonth = elapsedMonth.next();
			}
			
			elapsedDays += day.getValue();

			return elapsedDays;
		}
		
		@Override
		public String toString() {
			return day.getValue() + " " + month.getName() + " " + year.getValue();
		}
	}
}
