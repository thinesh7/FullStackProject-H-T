package com.flightapp.exception;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<CustomExceptionResponse> raiseExpForUserAlreadyRegistered(Exception exp){
		return ResponseEntity.ok(new CustomExceptionResponse(exp.getMessage(),exp.fillInStackTrace().toString(),500,LocalDateTime.now()));
	}
}
