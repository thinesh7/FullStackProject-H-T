package com.flightapp.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightapp.entities.FlightsSchedule;

public interface FlightsScheduleRepository extends JpaRepository<FlightsSchedule, Long> {

	@Query("SELECT s FROM FlightsSchedule s WHERE s.flightNumber = ?1 and s.sourcePlace = ?2 and s.destinationPlace =?3 and s.depatureDate =?4 and s.depatureTime=?5 and s.arrivalDate=?6 and arrivalTime=?7 and totalSeats=?8")
	public FlightsSchedule getScheduleFlightIfAlreadyAvailable(String fNumber, String sPlace, String dPlace,
			LocalDate dDate, String dTime, LocalDate aDate, String aTime, Integer totalSeats);
	
}
