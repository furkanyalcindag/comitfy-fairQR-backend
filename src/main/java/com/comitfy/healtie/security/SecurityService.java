package com.comitfy.healtie.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import com.comitfy.healtie.entity.User;
import com.comitfy.healtie.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isEmpty())
			throw new UsernameNotFoundException("Could not findUser with email = " + email);

		return new org.springframework.security.core.userdetails.User(
				email,
				user.get().getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

	}
}

