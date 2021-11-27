package com.flightapp;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.flightapp.akafka.KafkaController;
import com.flightapp.dto.TicketDTO;
import com.flightapp.entities.Ticket;
import com.flightapp.entities.UsersProfile;
import com.flightapp.exception.UserAlreadyRegisteredException;
import com.flightapp.repository.TicketRepository;
import com.flightapp.service.UsersService;

@SpringBootTest
class UserFlightManagementApplicationTests {

	@Autowired
	private UsersService service;

	@MockBean
	private KafkaController kafkaController;

	@MockBean
	private TicketRepository ticketRepo;

	@Test
	public void kakfkaProducerTesting() {

		List<Ticket> ticketList = new ArrayList<Ticket>();

		// 1. Kafka controller ---> return true
		Mockito.when(kafkaController.writeTicketDetailsToKafka(ticketList)).thenReturn(true);

		// 2. TicketRepo --ticketRepo> return true
		Ticket ticket = null;
		Mockito.when(ticketRepo.save(ticket)).thenReturn(null);

		// 3. Execute Service Method:
		boolean bookTicket = service.bookTicket(ticketList);
		
		//4. Test it
		Assertions.assertSame(bookTicket, true);
	}
	
	@Test
	public void registerUser() {
		// 1. User Details:		
		UsersProfile user = new UsersProfile();
		user.setEmailId("Thinesh@cts.com");
		
		// 2. Raise Exception if User Already Available
		Assertions.assertThrows(UserAlreadyRegisteredException.class, ()->{
			boolean registerUser = service.registerUser(user);
			System.out.println(registerUser);
		});
	}
	
	@Test
	public void getAllTicketsByEmail() {
		
		String emailToEcheck = "Thinesh@cts.com";
		//Getting All Books By Email ID:
		List<TicketDTO> allTickets = service.getAllTickets(emailToEcheck);
		
		//Condition
		MatcherAssert.assertThat(allTickets.size(), Matchers.greaterThan(0));
	}
}
