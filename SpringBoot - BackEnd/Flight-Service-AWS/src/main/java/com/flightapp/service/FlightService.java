package com.flightapp.service;

import java.util.List;
import com.flightapp.entities.Discounts;
import com.flightapp.entities.Ticket;

public interface FlightService {
	
	public List<Discounts> getDiscounts();
	public List<Ticket> getAllTickets();

}
