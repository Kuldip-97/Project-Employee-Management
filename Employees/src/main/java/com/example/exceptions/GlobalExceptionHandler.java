package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.config.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException rnfe){
		String message = rnfe.getMessage();
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiResponse api = ApiResponse.builder().message(message).success(true).status(status).build();
		return new ResponseEntity<ApiResponse>(api, status);
	}
}
