package com.flightapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flightapp.entities.Discounts;
import com.flightapp.entities.Ticket;
import com.flightapp.service.FlightService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0")
public class FlightController {

	@Autowired
	private FlightService service;

	// 1. Get All Discounts:
	@ApiOperation(value = "Get All Discounts", notes = "This method will return all the discounts available in FMS", response = Discounts.class)
	@GetMapping(value = "/get-discounts")
	public List<Discounts> getAllDiscounts() {
		return service.getDiscounts();
	}

	// 2. Get All Booked Tickets
	@ApiOperation(value = "Get All Tickets", notes = "This method will return all Tickets booked by Customers", response = Discounts.class)
	@GetMapping(value = "/getAll-tickets")
	public List<Ticket> getAllBookedTickets() {
		return service.getAllTickets();
	}
}
