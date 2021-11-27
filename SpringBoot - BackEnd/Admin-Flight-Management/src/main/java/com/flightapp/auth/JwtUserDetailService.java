package com.flightapp.auth;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// List<? extends GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		if ("Thinesh@admin.com".equals(username)) {
			return new User("Thinesh@admin.com", "$2a$10$BqTibqeSAn1xF3iP./WE1unHBRG.ZUpwTxJ47n6lizHu/2ADPakA2",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

}
