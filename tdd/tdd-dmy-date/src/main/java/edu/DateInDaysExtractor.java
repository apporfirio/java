package edu;

public class DateInDaysExtractor {
	
	private int remainingDays;
	private CalendarDay day;
	private CalendarMonth month;
	private CalendarYear year;
	
	public DateInDaysExtractor(int dateInDays) throws InvalidDMYDate {
		remainingDays = dateInDays;
		
		extractYear();
		extractMonth();
		extractDay();
	}
	
	public void extractYear() throws InvalidDMYDate {
		year = new CalendarYear();
		
		while (remainingDays > 365) {
			if (!year.isLeapYear()) {
				remainingDays -= 365;
				year = year.next();
			}
			else if (remainingDays > 366) {
				remainingDays -= 366;	
				year = year.next();
			}
			else {
				break;
			}
		}
	}
	
	public void extractMonth() throws InvalidDMYDate {
		month = new CalendarMonth();
		
		while (month.getValue() < 12 && remainingDays > 28) {
			
			if (month.hasMax31Days() && remainingDays > 31) {
				remainingDays -= 31;
				month = month.next();
			}
			else if (month.hasMax30Days() && remainingDays > 30) {
				remainingDays -= 30;
				month = month.next();
			}
			else if (month.isFebruary() && year.isLeapYear() && remainingDays > 29) {
				remainingDays -= 29;
				month = month.next();					
			}
			else if (month.isFebruary() && !year.isLeapYear()) {
				remainingDays -= 28;
				month = month.next();		
			}
			else {
				break;
			}
		}
	}
	
	public void extractDay() throws InvalidDMYDate {
		day = new CalendarDay(remainingDays);
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
