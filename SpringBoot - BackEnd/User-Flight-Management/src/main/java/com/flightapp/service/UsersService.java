package com.flightapp.service;

import java.util.List;
import com.flightapp.dto.TicketDTO;
import com.flightapp.entities.Ticket;
import com.flightapp.entities.UsersProfile;
import com.flightapp.exception.UserAlreadyRegisteredException;

public interface UsersService {

	public boolean bookTicket(List<Ticket> ticket);

	public List<TicketDTO> getAllTickets(String emailId);

	public void cancelTicket(String pnrNumber);

	public boolean registerUser(UsersProfile user) throws UserAlreadyRegisteredException;
}
