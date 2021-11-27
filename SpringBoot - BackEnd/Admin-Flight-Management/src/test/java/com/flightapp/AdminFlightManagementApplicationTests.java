package com.flightapp;

import java.util.TreeSet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.flightapp.customexception.AirLineAlreadyExistException;
import com.flightapp.dto.AirLine;
import com.flightapp.entities.Ticket;
import com.flightapp.repository.TicketRepository;
import com.flightapp.service.AdminService;

@SpringBootTest
class AdminFlightManagementApplicationTests {

	@Autowired
	private AdminService service;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@Test
	public void addAnAirline() {
		
		// 1. Airline Details:
		AirLine airline = new AirLine();
		airline.setAirlineName("SpiceJet");
		
		// 2. Raise Exception if Airline Already Available
		Assertions.assertThrows(AirLineAlreadyExistException.class, ()->{
			service.addNewAirline(airline);
		});
	}
	
	@Test
	public void scheduleFlight() {
		
		Ticket ticket = new Ticket();
		
		// 1. TicketRepo --ticketRepo return true
		Mockito.when(ticketRepository.save(ticket)).thenReturn(null);
		
		// Get Data from Kafka and Save into DB
		service.getDataFromKafkaAndPutItIntoDB(ticket);
	}
	
	@Test
	public void getAllAirportLocations() {
		
		// 1. Get All Airports locations
		TreeSet<String> allAirportLocations = service.getAllAirportLocations();
		
		//Condition
		MatcherAssert.assertThat(allAirportLocations.size(), Matchers.greaterThan(0));
	}

}
