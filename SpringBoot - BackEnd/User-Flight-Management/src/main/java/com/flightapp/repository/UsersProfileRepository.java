package com.flightapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.flightapp.entities.UsersProfile;

public interface UsersProfileRepository extends JpaRepository<UsersProfile, Integer> {

	@Query("SELECT u FROM UsersProfile u WHERE u.emailId = ?1")
	public UsersProfile getUserByEmailID(String emailId);
}
