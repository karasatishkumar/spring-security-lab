package org.techiekernel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.techiekernel.exception.AccountNotFoundException;

@ControllerAdvice
public class CentralControllerHandler {

	@ExceptionHandler({ AccountNotFoundException.class })
	public ResponseEntity<String> handlePersonNotFound(
			AccountNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleValidationException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
