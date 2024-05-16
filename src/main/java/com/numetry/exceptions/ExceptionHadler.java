package com.numetry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.numetry.response.ApiResponse;

@RestControllerAdvice
public class ExceptionHadler {

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> userNotFoundHandler(UserNotFoundException ex){
		ApiResponse response= new ApiResponse();
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.NOT_FOUND);
		response.setSuccess(true);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
