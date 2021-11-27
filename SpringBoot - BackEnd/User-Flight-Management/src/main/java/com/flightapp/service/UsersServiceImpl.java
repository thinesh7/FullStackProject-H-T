package com.flightapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.flightapp.akafka.KafkaController;
import com.flightapp.dto.TicketDTO;
import com.flightapp.entities.Ticket;
import com.flightapp.entities.UsersProfile;
import com.flightapp.exception.UserAlreadyRegisteredException;
import com.flightapp.repository.TicketRepository;
import com.flightapp.repository.UsersProfileRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private KafkaController kafkaCon;

	@Autowired
	private UsersProfileRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean bookTicket(List<Ticket> ticketList) {

		boolean WrittenFlag = kafkaCon.writeTicketDetailsToKafka(ticketList);

		int ticketSize = ticketList.size();
		int count = 0;

		for (Ticket ticket : ticketList) {
			ticketRepo.save(ticket);
			count++;
		}
		return ((ticketSize == count) && WrittenFlag);
	}

	@Override
	public List<TicketDTO> getAllTickets(String emailId) {

		List<TicketDTO> ticketsDto = new ArrayList<TicketDTO>();

		List<Ticket> ticketsList = ticketRepo.getTickets(emailId);
		for (Ticket t : ticketsList) {
			TicketDTO ticket = new TicketDTO(t.getTicketId(), t.getPnrNumber(), t.isStatus(), t.getEmailId(),
					t.getFlightId(), t.getFlightName(), t.getSourcePlace(), t.getDestinationPlace(),
					t.getDepatureDate(), t.getDepartTime(), t.getArrivaltime(), t.getTicketAmount(),
					t.getPassengerName(), t.getPassengerGender(), t.getPassengerAge(), t.getSeatNumber(),
					t.getOptMeal());
			ticket.setOkToCancel(checkAllowCancel(t.getDepatureDate()));
			ticketsDto.add(ticket);
		}
		return ticketsDto;
	}

	private boolean checkAllowCancel(LocalDate departureDate) {
		boolean flag = false;
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = departureDate;
		LocalDate date3 = date2.minusDays(1);
		if (date2.equals(date1) || date3.equals(date1))
			flag = true;
		return flag;
	}

	@Override
	public void cancelTicket(String pnrNumber) {
		ticketRepo.cancelTicket(pnrNumber);
	}

	@Override
	public boolean registerUser(UsersProfile user) throws UserAlreadyRegisteredException {
		UsersProfile isExist = userRepo.getUserByEmailID(user.getEmailId());
		if (isExist != null)
			throw new UserAlreadyRegisteredException(
					"User " + user.getName() + " " + user.getEmailId() + " is Already Registered");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UsersProfile userSaved = userRepo.save(user);
		return userSaved != null;
	}

}
