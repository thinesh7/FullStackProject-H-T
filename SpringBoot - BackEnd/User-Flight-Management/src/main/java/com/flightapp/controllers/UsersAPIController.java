package com.flightapp.controllers;

import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.flightapp.dto.DiscountDTO;
import com.flightapp.dto.FlightScheduleDTO;
import com.flightapp.dto.FlightSearchDetailsDTO;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/v1.0/users")
public class UsersAPIController { // http://localhost:8082/api/v1.0/users/airports-location-list

	// REST API Call to Admin MicroService:
	@Autowired
	private RestTemplate restTemp;
	private String adminMSurl = "http://admin-service/api/v1.0";

	// 1. Get All Locations:
	@Cacheable(value = "airports-locations")
	@GetMapping(value = "/airports-location-list")
	public TreeSet<String> getAllAirportLocations() {
		ResponseEntity<TreeSet<String>> locationResult = restTemp.exchange(this.adminMSurl + "/getairportLocations",
				HttpMethod.GET, null, new ParameterizedTypeReference<TreeSet<String>>() {
				});
		return locationResult.getBody();
	}

	// 2. GetAllScheduledFlights From Admin MC: URL:
	// http://localhost:8082/api/v1.0/users/getAllSchedulesFlights
	public List<FlightScheduleDTO> getAllScheduledFlights() {
		ResponseEntity<List<FlightScheduleDTO>> allScheduledFlights = restTemp.exchange(
				this.adminMSurl + "/getAll-scheduleFlights", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<FlightScheduleDTO>>() {
				});
		return allScheduledFlights.getBody();
	}

	// 3. GetAllSearchDetails From Admin MC
	public List<FlightSearchDetailsDTO> getSearchFlightDetails() {
		ResponseEntity<List<FlightSearchDetailsDTO>> allSearchDetails = restTemp.exchange(
				this.adminMSurl + "/flight-search-details", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<FlightSearchDetailsDTO>>() {
				});
		System.out.println("Done");
		return allSearchDetails.getBody();
	}

	// 4. Get Discounts
	@GetMapping("/get-discounts")
	public List<DiscountDTO> getAllDiscounts() {
		ResponseEntity<List<DiscountDTO>> disocuntList = restTemp.exchange(this.adminMSurl + "/get-discounts",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<DiscountDTO>>() {
				});
		return disocuntList.getBody();
	}
	
	// 5. Cancel Ticket
	public void cancelTicket(String pnr) {
		restTemp.exchange(this.adminMSurl + "/cancel-ticket/"+pnr,
				HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
				});
	}

}
