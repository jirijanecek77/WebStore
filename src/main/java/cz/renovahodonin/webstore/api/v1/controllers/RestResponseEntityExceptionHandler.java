package cz.renovahodonin.webstore.api.v1.controllers;

import cz.renovahodonin.webstore.api.v1.dto.ExceptionResponseDto;
import cz.renovahodonin.webstore.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundException(Exception exception, WebRequest request)
    {
        ExceptionResponseDto response = new ExceptionResponseDto(
                "Not found",
                exception.getMessage());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception)
    {
        ExceptionResponseDto response = new ExceptionResponseDto(
                "Validation Error",
                "Zadejte správné údaje.",
                exception.getBindingResult().getAllErrors().stream()
                        .map(e -> e.getDefaultMessage()).collect(Collectors.toList()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}