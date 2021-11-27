package com.flightapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightapp.entities.AirlineMaster;

public interface AirlineMasterRepository extends JpaRepository<AirlineMaster, Integer>{

	public boolean existsByairlineName(String airlineName);
	
	@Query("SELECT a.airlineLogo FROM AirlineMaster a WHERE a.airlineName = ?1")
	public String getLogoUrl(String airlineName);

}
