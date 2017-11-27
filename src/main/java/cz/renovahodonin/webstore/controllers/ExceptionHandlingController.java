package cz.renovahodonin.webstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController
{

	static final String ERROR_PAGE = "/error";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormat(Exception exception){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName(ERROR_PAGE);
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
