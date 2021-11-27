package com.flightapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flightapp.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
