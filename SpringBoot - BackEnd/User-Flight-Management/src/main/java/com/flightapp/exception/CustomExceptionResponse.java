package com.flightapp.exception;

import java.time.LocalDateTime;

public class CustomExceptionResponse {
	
	private String message;
	private String type;
	private int statusCode;
	private LocalDateTime currentTime;
	
	public CustomExceptionResponse(String message, String type, int statusCode, LocalDateTime currentTime) {
		this.message = message;
		this.type = type;
		this.statusCode = statusCode;
		this.currentTime = currentTime;
	}
	
	public CustomExceptionResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "CustomExceptionResponse [message=" + message + ", type=" + type + ", statusCode=" + statusCode
				+ ", currentTime=" + currentTime + "]";
	}
}
