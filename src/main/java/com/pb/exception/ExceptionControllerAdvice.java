package com.pb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
 
	@ExceptionHandler(value= CustomException.class)
	public final ResponseEntity<String> handleCustomeExcepton(CustomException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler(value= AuthenticationFailException.class)
	public final ResponseEntity<String> handleAuthenticationFailException(AuthenticationFailException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler(value= ProductNotFoundException.class)
	public final ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}
