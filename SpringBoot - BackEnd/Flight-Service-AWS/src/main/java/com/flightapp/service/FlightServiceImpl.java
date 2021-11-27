package com.flightapp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flightapp.entities.Discounts;
import com.flightapp.entities.Ticket;
import com.flightapp.repository.DiscountRepository;
import com.flightapp.repository.TicketRepository;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public List<Discounts> getDiscounts() {
		return discountRepository.findAll();
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

}
