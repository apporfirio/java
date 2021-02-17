package edu;

public class InvalidDMYDate extends RuntimeException {
	
	private static final long serialVersionUID = 1472042361511692470L;

	public InvalidDMYDate(String message) {
		super(message);
	}
}
