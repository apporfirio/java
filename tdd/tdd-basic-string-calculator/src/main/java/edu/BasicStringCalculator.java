package edu;

public class BasicStringCalculator {
	
	public Double add(String input) {
		Double sum = 0.0;
		
		for (String singleValueInput : input.split("[,\\n]")) {
			sum += parseSingleValue(singleValueInput);
		}
		
		return sum;
	}

	private Double parseSingleValue(String input) {
		String singleValue = "0";
		
		for (int i = 0; i < input.length(); i++) {
			String nextChar = "" + input.charAt(i);
			
			if (!isValidCharacter(nextChar)) {
				throw new InvalidCharacterException("Invalid character '" + nextChar + "' in input");
			}
			
			singleValue += nextChar;
		}
		
		if (!isValidNumber(singleValue)) {
			throw new InvalidNumberException("Invalid number '" + singleValue + "' in input");
		}
		
		return Double.valueOf(singleValue);
	}
	
	
	private boolean isValidCharacter(String character) {
		return character != null && character.matches("[0-9,\\.\\n]");
	}
	
	private boolean isValidNumber(String number) {
		return number != null && number.matches("\\d+(\\.\\d+)?");
	}
	
	
	public static class InvalidCharacterException extends RuntimeException {
		private static final long serialVersionUID = 6999556875794321114L;

		public InvalidCharacterException(String message) {
			super(message);
		}
	}
	
	public static class InvalidNumberException extends RuntimeException {
		private static final long serialVersionUID = 6959783905609435676L;

		public InvalidNumberException(String message) {
			super(message);
		}
	}
}
