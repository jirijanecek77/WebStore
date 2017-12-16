package cz.renovahodonin.webstore.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class IllegalValueException extends RuntimeException
{
	public List<ObjectError> getErrors()
	{
		return errors;
	}

	private List<ObjectError> errors;

	public IllegalValueException(List<ObjectError> errors)
	{
		this.errors = errors;
	}
}
