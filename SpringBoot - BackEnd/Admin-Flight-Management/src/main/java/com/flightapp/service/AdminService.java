package com.flightapp.service;

import java.util.List;
import java.util.TreeSet;

import com.flightapp.customexception.AirLineAlreadyExistException;
import com.flightapp.customexception.DiscountAlreadyExistException;
import com.flightapp.customexception.FlightAlreadyScheduledException;
import com.flightapp.dto.AirLine;
import com.flightapp.dto.DiscountDTO;
import com.flightapp.dto.FlightScheduleDTO;
import com.flightapp.dto.FlightSearchDetailsDTO;
import com.flightapp.entities.Ticket;

public interface AdminService {
	
	public boolean addNewAirline(AirLine airline) throws AirLineAlreadyExistException;

	public TreeSet<String> getAllAirlines();

	public boolean scheduleFlight(FlightScheduleDTO flight) throws FlightAlreadyScheduledException;

	public List<FlightScheduleDTO> getAllScheduleFlights();

	public boolean cancelScheduledFlight(FlightScheduleDTO flight);

	public boolean addDiscount(DiscountDTO discountDto) throws DiscountAlreadyExistException;

	public List<DiscountDTO> getDiscounts();

	public TreeSet<String> getAllAirportLocations();

	public List<FlightSearchDetailsDTO> getSearchFlightDetails();
	
	public void getDataFromKafkaAndPutItIntoDB(Ticket ticket);

	public List<Ticket> getAllTickets();

	public void cancelTicket(String pnrNumber);

}
