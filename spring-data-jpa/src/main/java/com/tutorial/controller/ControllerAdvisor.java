package com.tutorial.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.exception.CustomException;
import com.tutorial.exception.ErrorResponse;

@ControllerAdvice
@RestController
public class ControllerAdvisor {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception, HttpServletRequest request) {
		if (exception instanceof CustomException) {
			CustomException customException = (CustomException) exception;
			return new ResponseEntity<>(new ErrorResponse(customException.getMessage(),
					customException.getLocalizedMessage(), request.getRequestURI()), 
					customException.getHttpStatus());
		}
		return new ResponseEntity<>(
				new ErrorResponse(exception.getMessage(), exception.getLocalizedMessage(), request.getRequestURI()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
