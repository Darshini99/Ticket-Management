package com.darshini.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@ControllerAdvice
public class HandlingTicketExceptions {
	
	//Custom Exceptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> validationErrors(MethodArgumentNotValidException exception){
		Map<String,String> errormap= new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
			errormap.put(error.getField(),error.getDefaultMessage());}
		);
		return errormap;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(TicketNotFoundException.class)
	public Map<String,String> notFound(TicketNotFoundException exception) {
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("errorMessage",exception.getMessage());
		return errorMap;
	}
	
	//Global Exceptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchMethodException.class)
	public ResponseEntity<String> handleNoMethodException(NoSuchMethodException exception){
		return new ResponseEntity<String>("No value is presented please check your inputs",HttpStatus.OK);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception){
		return new ResponseEntity<Object>("please check your http request type",HttpStatus.NOT_FOUND);
	}

}
