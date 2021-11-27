package com.flightapp.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flightapp.beans.BookingRequest;
import com.flightapp.dto.BookTicketSuccessDTO;
import com.flightapp.dto.FlightSearchDetailsDTO;
import com.flightapp.dto.SearchRequestDTO;
import com.flightapp.dto.SearchResultDTO;
import com.flightapp.dto.TicketDTO;
import com.flightapp.entities.UsersProfile;
import com.flightapp.exception.UserAlreadyRegisteredException;
import com.flightapp.helperclasses.HelperClass;
import com.flightapp.service.UsersService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") 
@RequestMapping(value = "api/v1.0/users")
public class UserController {

	@Autowired
	private UsersAPIController apiController;
	
	@Autowired
	private UsersService userService;

	@Autowired
	private HelperClass helper;
	
	// Search Request:
	@PostMapping("/search-flights")
	public List<SearchResultDTO> getFlightSearchResult(@RequestBody SearchRequestDTO searchReqDto) {
		List<SearchResultDTO> searchResults = new ArrayList<SearchResultDTO>();
		List<FlightSearchDetailsDTO> allScheduledFlights = apiController.getSearchFlightDetails();

		for (FlightSearchDetailsDTO flight : allScheduledFlights) {
			if (searchReqDto.getSourcePlace().equals(flight.getFrom())
					&& searchReqDto.getDestinationPlace().equals(flight.getTo())
					&& searchReqDto.getDepartureDate().equals(flight.getDepatureDate())) {
				
				searchResults.add(new SearchResultDTO(flight.getAirlineName(), flight.getLogoUrl(),
						flight.getFlightNumber(), flight.getPrice(), flight.getdTime(), flight.getDepatureDate(),
						flight.getaTime(), flight.getScheduleId()));
			}
		}
		return searchResults;
	}

	// Book Ticket:
	@PostMapping(value = "/book-ticket")
	public BookTicketSuccessDTO bookTicket(@RequestBody BookingRequest bookReq) {
		System.out.println(bookReq);

		if (bookReq.getTripType().equalsIgnoreCase("oneway")) {
			return helper.bookTicketForOneWay(bookReq);
		} else {
			return helper.bookTicketForRoundTrip(bookReq);
		}
	}
	
	// Get All Booked tickets:
	@GetMapping(value = "/get-tickets/{emailId}")
	public List<TicketDTO> getAllBookedTickets(@PathVariable() String emailId){
		return userService.getAllTickets(emailId);
	}
	
	// Cancel Ticket:
	@DeleteMapping("/cancel-ticket/{pnr}")
	public void cancelBookedTicket(@PathVariable("pnr") String pnrNumber){
		apiController.cancelTicket(pnrNumber);
		userService.cancelTicket(pnrNumber);
	}
	
	// Register New User:
	@PostMapping("/register-user")
	public boolean registerNewUser(@RequestBody UsersProfile user) throws UserAlreadyRegisteredException {
		boolean flag = false;
		flag = userService.registerUser(user);
		return flag;
	}
}
