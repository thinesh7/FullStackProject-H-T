package com.flightapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flightapp.entities.Discounts;

public interface DiscountRepository extends JpaRepository<Discounts, Integer>{

	public boolean existsBydiscountCode(String airlineName);
}
