package com.lateu.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lateu.entity.ConfirmationToken;
import com.lateu.entity.User;
import com.lateu.repository.ConfirmationTokenRepository;
import com.lateu.repository.UserRepository;

@Service
@Transactional
public class ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository tokenRepository;
	
	@Autowired
	private  UserRepository repository;
	public ConfirmationTokenService() {
		// TODO Auto-generated constructor stub
	}
	
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken=tokenRepository.findByToken(token);
		
		if (confirmationToken.getConfirmAt()!=null){
			throw new IllegalStateException("token already confirm");
			
		}
		
		if (confirmationToken.equals(null)){
			throw new IllegalStateException("token not found");
			
		}
		
		if (confirmationToken.getExpireAt().isBefore(LocalDateTime.now())){
			throw new IllegalStateException("token already expired");
			
		}
		
		User user=confirmationToken.getUser();
		confirmationToken.setConfirmAt(LocalDateTime.now());
		
		tokenRepository.save(confirmationToken);
		
		user.setEnabled(true);
		user.setLocked(false);
		repository.save(user);
		
		return "confirmed";
	}

   
}
