package cz.renovahodonin.webstore.exceptions;

public class IllegalValueException extends RuntimeException
{
	public IllegalValueException() {
		super();
	}

	public IllegalValueException(String message) {
		super(message);
	}

	public IllegalValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
