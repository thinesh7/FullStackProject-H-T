package com.flightapp.akafka;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.flightapp.entities.Ticket;

@Component
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, Ticket> kafkaTemplate;
	
	private static final String TOPIC = "Topic_Ticket";

	public boolean writeTicketDetailsToKafka(List<Ticket> ticketList) {
		System.out.println("Sending Data to Kafka..!");
		int ticketSize = ticketList.size();
		int count = 0;
		
		for (Ticket ticket : ticketList) {
			kafkaTemplate.send(TOPIC, ticket);
			count++;
		}
		return ticketSize == count;
	}

}
