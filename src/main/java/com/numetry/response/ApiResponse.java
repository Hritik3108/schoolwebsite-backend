package com.numetry.response;

import org.springframework.http.HttpStatus;

//@Data
public class ApiResponse {

	private String message;
	private boolean success;
	private HttpStatus statuscode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatus getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(HttpStatus statuscode) {
		this.statuscode = statuscode;
	} 
	
	
}
