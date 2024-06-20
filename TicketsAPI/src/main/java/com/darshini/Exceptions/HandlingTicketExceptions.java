package com.darshini.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class HandlingTicketExceptions {
	
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

}
