package com.flightapp.controllers;

import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.customexception.AirLineAlreadyExistException;
import com.flightapp.customexception.DiscountAlreadyExistException;
import com.flightapp.customexception.FlightAlreadyScheduledException;
import com.flightapp.dto.AirLine;
import com.flightapp.dto.DiscountDTO;
import com.flightapp.dto.FlightScheduleDTO;
import com.flightapp.dto.FlightSearchDetailsDTO;
import com.flightapp.entities.Ticket;
import com.flightapp.service.AdminService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// Register New AirLine
	@PostMapping(value = "/register-airline")
	public boolean addNewAirline(@RequestBody AirLine airline) throws AirLineAlreadyExistException {
		boolean flag = false;
		flag = adminService.addNewAirline(airline);
		return flag;
	}

	// Get All AirLines
	@GetMapping(value = "/getAirlines")
	public TreeSet<String> getAllAirlines() {
		return adminService.getAllAirlines();
	}

	// Create a Schedule for a Flight
	@PostMapping(value = "/schedule-flight")
	public boolean scheduleFlight(@RequestBody FlightScheduleDTO flight) throws FlightAlreadyScheduledException {
		return adminService.scheduleFlight(flight);
	}

	// Get All Scheduled Flights
	@GetMapping(value = "/getAll-scheduleFlights")
	public List<FlightScheduleDTO> getAllScheduleFlights() {
		return adminService.getAllScheduleFlights();
	}

	// Cancel Scheduled Flights
	@PutMapping(value = "/cancel-scheduleFlights")
	public boolean cancelScheduledFlight(@RequestBody FlightScheduleDTO flight) {
		return adminService.cancelScheduledFlight(flight);
	}

	// Add Discount:
	@PostMapping(value = "/add-discounts")
	public boolean addDiscount(@RequestBody DiscountDTO discountDto) throws DiscountAlreadyExistException {
		boolean flag = false;
		flag = adminService.addDiscount(discountDto);
		return flag;
	}

	// Get Discounts:
	@Cacheable
	@GetMapping(value = "/get-discounts")
	public List<DiscountDTO> getAllDiscounts() {
		return adminService.getDiscounts();
	}

	// API RESPONSE:
	@Cacheable
	@GetMapping(value = "/getairportLocations")
	public TreeSet<String> getAllAirportLocations() {
		return adminService.getAllAirportLocations();
	}

	@Cacheable
	@GetMapping(value = "/flight-search-details")
	public List<FlightSearchDetailsDTO> getSearchFlightDetails() {
		return adminService.getSearchFlightDetails();
	}

	@GetMapping(value = "/getAll-tickets")
	public List<Ticket> getAllBookedTickets() {
		return adminService.getAllTickets();
	}

	// Cancel Ticket:
	@DeleteMapping("/cancel-ticket/{pnr}")
	public void cancelBookedTicket(@PathVariable("pnr") String pnrNumber) {
		adminService.cancelTicket(pnrNumber);
	}
}
