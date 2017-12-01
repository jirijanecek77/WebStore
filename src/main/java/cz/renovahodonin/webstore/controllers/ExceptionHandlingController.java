package cz.renovahodonin.webstore.controllers;

import cz.renovahodonin.webstore.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController
{

	static final String ERROR_PAGE_URL = "error";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormat(Exception exception){

		return handleException(exception);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception){

		return handleException(exception);
	}

	private ModelAndView handleException(Exception exception)
	{
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(ERROR_PAGE_URL);
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
