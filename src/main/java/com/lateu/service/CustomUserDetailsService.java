package com.lateu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lateu.entity.User;
import com.lateu.repository.UserRepository;
import com.lateu.security.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {
@Autowired
private UserRepository userRepository;
	public CustomUserDetailsService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u=userRepository.findUserByEmail(username);
		 if (u==null) {
			 throw new UsernameNotFoundException("USER");
		 }
			
		 return new CustomUserDetails(u);
	
	}

}
