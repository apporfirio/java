package edu;

import java.time.LocalTime;

public class Greeter {

	public static String greet(String name, LocalTime greetingTime) {
		String formattedName = formatName(name);
		String finalName = formattedName.isEmpty() ? formattedName : (" " + formattedName);

		if (isMorningTime(greetingTime)) {
			return "Bom dia" + finalName;
		}
		
		if (isNightTime(greetingTime)) {
			return "Boa noite" + finalName;
		}
		
		return "Olá" + finalName;
	}
	
	private static boolean isMorningTime(LocalTime greetingTime) {
		int greetingHour = greetingTime.getHour();
		
		return greetingHour >= 06 && greetingHour < 12;
	}
	
	private static boolean isNightTime(LocalTime greetingTime) {
		int greetingHour = greetingTime.getHour();
		
		return greetingHour >= 18 || greetingHour < 06;
	}
	
	private static String formatName(String name) {
		String trimmedName = (name == null) ? "" : name.trim();
		
		if (trimmedName.length() <= 1) {
			return trimmedName.toUpperCase();
		}
		
		return trimmedName.substring(0, 1).toUpperCase() + trimmedName.substring(1).toLowerCase();
	}
	
}
