package com.flightapp.auth;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightapp.entities.UsersProfile;
import com.flightapp.repository.UsersProfileRepository;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	private UsersProfileRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// List<? extends GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		//Getting User details from DB:
		UsersProfile userDetails = userRepo.getUserByEmailID(username);
		if (userDetails != null) {
			return new User(userDetails.getEmailId(), userDetails.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

}
