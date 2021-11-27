package com.flightapp.exceptionhandler;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightapp.customexception.AirLineAlreadyExistException;
import com.flightapp.customexception.DiscountAlreadyExistException;
import com.flightapp.customexception.FlightAlreadyScheduledException;
import com.flightapp.customexceptionresponse.CustomExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AirLineAlreadyExistException.class)
	public ResponseEntity<CustomExceptionResponse> raiseExpForAirlineAlreadyExist(AirLineAlreadyExistException exp){
		return ResponseEntity.ok(new CustomExceptionResponse(exp.getMessage(),exp.fillInStackTrace().toString(),500,LocalDateTime.now()));
	}
	
	@ExceptionHandler(DiscountAlreadyExistException.class)
	public ResponseEntity<CustomExceptionResponse> raiseExpForDiscountAlreadyExist(Exception exp){
		return ResponseEntity.ok(new CustomExceptionResponse(exp.getMessage(),exp.fillInStackTrace().toString(),500,LocalDateTime.now()));
	}
	
	@ExceptionHandler(FlightAlreadyScheduledException.class)
	public ResponseEntity<CustomExceptionResponse> raiseExpForFlightAlreadyScheduledException(Exception exp){
		return ResponseEntity.ok(new CustomExceptionResponse(exp.getMessage(),exp.fillInStackTrace().toString(),500,LocalDateTime.now()));
	}
}
