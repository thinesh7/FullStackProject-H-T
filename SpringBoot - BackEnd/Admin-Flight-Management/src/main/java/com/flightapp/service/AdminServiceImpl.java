package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.flightapp.customexception.AirLineAlreadyExistException;
import com.flightapp.customexception.DiscountAlreadyExistException;
import com.flightapp.customexception.FlightAlreadyScheduledException;
import com.flightapp.dto.AirLine;
import com.flightapp.dto.DiscountDTO;
import com.flightapp.dto.FlightScheduleDTO;
import com.flightapp.dto.FlightSearchDetailsDTO;
import com.flightapp.entities.AirlineMaster;
import com.flightapp.entities.Discounts;
import com.flightapp.entities.FlightsSchedule;
import com.flightapp.entities.Ticket;
import com.flightapp.repository.AirlineMasterRepository;
import com.flightapp.repository.DiscountRepository;
import com.flightapp.repository.FlightsScheduleRepository;
import com.flightapp.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {

	private static final String TOPIC = "Topic_Ticket";

	@Autowired
	private AirlineMasterRepository airlineRepository;

	@Autowired
	private FlightsScheduleRepository scheduleRepository;

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public boolean addNewAirline(AirLine airline) throws AirLineAlreadyExistException {

		AirlineMaster air = new AirlineMaster(airline.getAirlineName(), airline.getAirlineLogo(),
				airline.getAirlineContactNumber(), airline.getAirLineAddress());

		boolean isExist = airlineRepository.existsByairlineName(air.getAirlineName());
		if (isExist)
			throw new AirLineAlreadyExistException(
					"Airline :" + airline.getAirlineName() + " is already updated on the system");

		AirlineMaster saveedAirLineData = airlineRepository.save(air);
		return saveedAirLineData != null;
	}

	// Get All Airline List
	@Override
	public TreeSet<String> getAllAirlines() {
		TreeSet<String> airlineList = new TreeSet<String>();
		List<AirlineMaster> airlineMasterList = airlineRepository.findAll();

		for (AirlineMaster airline : airlineMasterList) {
			airlineList.add(airline.getAirlineName());
		}
		return airlineList;
	}

	@Override
	public boolean scheduleFlight(FlightScheduleDTO flight) throws FlightAlreadyScheduledException {

		FlightsSchedule schedule = new FlightsSchedule(flight.getAirlineName(), flight.getFlightNumber(),
				flight.getFrom(), flight.getTo(), flight.getPrice(), flight.getDepatureDate(), flight.getdTime(),
				flight.getArivalDate(), flight.getaTime(), flight.getTotalSeats(), true);

		FlightsSchedule isExist = scheduleRepository.getScheduleFlightIfAlreadyAvailable(schedule.getFlightNumber(),
				schedule.getSourcePlace(), schedule.getDestinationPlace(), schedule.getDepatureDate(),
				schedule.getDepatureTime(), schedule.getArrivalDate(), schedule.getArrivalTime(),
				schedule.getTotalSeats());

		if (isExist != null)
			throw new FlightAlreadyScheduledException(
					"Flight Number: " + flight.getFlightNumber() + " is already scheduled");

		FlightsSchedule savedData = scheduleRepository.save(schedule);

		return savedData != null;
	}

	@Override
	public List<FlightScheduleDTO> getAllScheduleFlights() {

		List<FlightScheduleDTO> schedulFlights = new ArrayList<FlightScheduleDTO>();
		List<FlightsSchedule> allScheduledFlights = scheduleRepository.findAll();

		for (FlightsSchedule flight : allScheduledFlights) {
			FlightScheduleDTO scheduleFlight = new FlightScheduleDTO(flight.getFlightScheduleId(),
					flight.getAirlineName(), flight.getFlightNumber(), flight.getSourcePlace(),
					flight.getDestinationPlace(), flight.getTicketCost(), flight.getDepatureDate(),
					flight.getDepatureTime(), flight.getArrivalDate(), flight.getArrivalTime(), flight.getTotalSeats(),
					flight.isAvailable());
			schedulFlights.add(scheduleFlight);
		}
		return schedulFlights;
	}

	@Override
	public boolean cancelScheduledFlight(FlightScheduleDTO flight) {
		FlightsSchedule schedule = new FlightsSchedule(flight.getAirlineName(), flight.getFlightNumber(),
				flight.getFrom(), flight.getTo(), flight.getPrice(), flight.getDepatureDate(), flight.getdTime(),
				flight.getArivalDate(), flight.getaTime(), flight.getTotalSeats(), flight.isStatus());
		schedule.setFlightScheduleId(flight.getScheduleId());

		FlightsSchedule savedData = scheduleRepository.save(schedule);
		return savedData != null;
	}

	@Override
	public boolean addDiscount(DiscountDTO discountDto) throws DiscountAlreadyExistException {
		boolean isExist = discountRepository.existsBydiscountCode(discountDto.getDiscountCode());
		if (isExist)
			throw new DiscountAlreadyExistException("Discount :" + discountDto.getDiscountCode() + " is already exist");

		Discounts discount = new Discounts(discountDto.getDiscountCode(), discountDto.getDiscountPercentage());
		discount.setDiscountId(0);
		Discounts discountSaved = discountRepository.save(discount);
		return discountSaved != null;
	}

	@Override
	public List<DiscountDTO> getDiscounts() {
		List<DiscountDTO> discountsList = new ArrayList<DiscountDTO>();
		List<Discounts> allDiscounts = discountRepository.findAll();
		for (Discounts d : allDiscounts) {
			DiscountDTO dto = new DiscountDTO(d.getDiscountCode(), d.getDiscountPercentage());
			discountsList.add(dto);
		}
		return discountsList;
	}

	@Override
	public TreeSet<String> getAllAirportLocations() {
		TreeSet<String> allLocations = new TreeSet<String>();
		List<FlightsSchedule> allScheduledFlights = scheduleRepository.findAll();
		for (FlightsSchedule f : allScheduledFlights) {
			allLocations.add(f.getDestinationPlace());
			allLocations.add(f.getSourcePlace());
		}
		return allLocations;
	}

	@Override
	public List<FlightSearchDetailsDTO> getSearchFlightDetails() {
		List<FlightSearchDetailsDTO> searchFlights = new ArrayList<FlightSearchDetailsDTO>();
		List<FlightsSchedule> allScheduledFlights = scheduleRepository.findAll();

		for (FlightsSchedule flight : allScheduledFlights) {
			if (flight.isAvailable()) {
				FlightSearchDetailsDTO flightDetails = new FlightSearchDetailsDTO(flight.getFlightScheduleId(),
						flight.getAirlineName(), flight.getFlightNumber(), flight.getSourcePlace(),
						flight.getDestinationPlace(), flight.getTicketCost(), flight.getDepatureDate(),
						flight.getDepatureTime(), flight.getArrivalDate(), flight.getArrivalTime(),
						flight.getTotalSeats(), flight.isAvailable());

				String logoUrl = airlineRepository.getLogoUrl(flight.getAirlineName());
				flightDetails.setLogoUrl(logoUrl);
				searchFlights.add(flightDetails);
			}
		}
		return searchFlights;
	}

	@KafkaListener(topics = TOPIC, groupId = "group_id", containerFactory = "userKafkaListenerFactory")
	public void getDataFromKafkaAndPutItIntoDB(Ticket ticket) {
		ticketRepo.save(ticket);
		System.out.println("Kafka data saved to DB..!");
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

	@Override
	public void cancelTicket(String pnrNumber) {
		ticketRepo.cancelTicket(pnrNumber);
	}

}
