package com.flightapp.helperclasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flightapp.beans.BookingRequest;
import com.flightapp.beans.PassengerRequest;
import com.flightapp.controllers.UsersAPIController;
import com.flightapp.dto.BookTicketSuccessDTO;
import com.flightapp.dto.FlightScheduleDTO;
import com.flightapp.dto.PassengerDTO;
import com.flightapp.entities.Ticket;
import com.flightapp.service.UsersService;

@Component
public class HelperClass {

	@Autowired
	private UsersService service;

	@Autowired
	private UsersAPIController apiController;

	public BookTicketSuccessDTO bookTicketForOneWay(BookingRequest req) {
		BookTicketSuccessDTO retunDto = new BookTicketSuccessDTO(req.getName(), req.getEmail(),
				req.getBillingAmmount());
		List<PassengerDTO> passengerDto = new ArrayList<PassengerDTO>();

		List<Ticket> ticketsList = new ArrayList<Ticket>();
		FlightScheduleDTO fDeatils = getFlightDeatils(req.getOnwardScheduleId());

		for (PassengerRequest pass : req.getPassengersList()) {
			String pnr = generatePNRNumber();
			Ticket ticket = new Ticket(pnr, true, req.getEmail(), fDeatils.getFlightNumber(), fDeatils.getAirlineName(),
					fDeatils.getFrom(), fDeatils.getTo(), fDeatils.getDepatureDate(), fDeatils.getdTime(),
					fDeatils.getaTime(), req.getBillingAmmount(), pass.getpName(), pass.getpGender(), pass.getAge(),
					pass.getOnwardSeat(), pass.getOptMeal());
			ticketsList.add(ticket);
			passengerDto.add(new PassengerDTO(pass.getpName(),"On Ward", pass.getOnwardSeat(), pnr));
		}
		boolean flag  = service.bookTicket(ticketsList);
		if(flag)
		retunDto.setPassengersList(passengerDto);
		return retunDto;
	}

	public BookTicketSuccessDTO bookTicketForRoundTrip(BookingRequest req) {

		BookTicketSuccessDTO retunDto = new BookTicketSuccessDTO(req.getName(), req.getEmail(),
				req.getBillingAmmount());
		List<PassengerDTO> passengerDto = new ArrayList<PassengerDTO>();

		// 1 .One way
		List<Ticket> ticketsList = new ArrayList<Ticket>();
		FlightScheduleDTO fDeatils1 = getFlightDeatils(req.getOnwardScheduleId());

		for (PassengerRequest pass : req.getPassengersList()) {
			String pnr = generatePNRNumber();

			Ticket ticket = new Ticket(pnr, true, req.getEmail(), fDeatils1.getFlightNumber(),
					fDeatils1.getAirlineName(), fDeatils1.getFrom(), fDeatils1.getTo(), fDeatils1.getDepatureDate(),
					fDeatils1.getdTime(), fDeatils1.getaTime(), req.getBillingAmmount(), pass.getpName(),
					pass.getpGender(), pass.getAge(), pass.getOnwardSeat(), pass.getOptMeal());

			ticketsList.add(ticket);
			passengerDto.add(new PassengerDTO(pass.getpName(),"On Ward", pass.getOnwardSeat(), pnr));
		}

		// 2. Return:
		FlightScheduleDTO fDeatils2 = getFlightDeatils(req.getRoundScheduleId());

		for (PassengerRequest pass : req.getPassengersList()) {
			String pnr = generatePNRNumber();
			Ticket ticket = new Ticket(pnr, true, req.getEmail(), fDeatils2.getFlightNumber(),
					fDeatils2.getAirlineName(), fDeatils2.getFrom(), fDeatils2.getTo(), fDeatils2.getDepatureDate(),
					fDeatils2.getdTime(), fDeatils2.getaTime(), req.getBillingAmmount(), pass.getpName(),
					pass.getpGender(), pass.getAge(), pass.getReturnSeat(), pass.getOptMeal());

			ticketsList.add(ticket);
			passengerDto.add(new PassengerDTO(pass.getpName(),"Return", pass.getReturnSeat(), pnr));
		}
		boolean flag = service.bookTicket(ticketsList);
		if(flag)
		retunDto.setPassengersList(passengerDto);
		return retunDto;
	}

	// Get Flight Details:
	public FlightScheduleDTO getFlightDeatils(Long scheduleId) {
		List<FlightScheduleDTO> allScheduledFlights = apiController.getAllScheduledFlights();
		FlightScheduleDTO flightDeatils = null;
		for (FlightScheduleDTO flight : allScheduledFlights) {
			if (flight.getScheduleId().equals(scheduleId)) {
				flightDeatils = flight;
			}
		}
		return flightDeatils;
	}

	// GeneratePNR
	public String generatePNRNumber() {
		StringBuffer pnr = new StringBuffer("PNR");
		String txt = new String(Math.random() + "");
		pnr.append(txt.substring(2, 10));
		return pnr.toString();
	}
}
