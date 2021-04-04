package com.lateu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lateu.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
	
	ConfirmationToken findByToken(String token);

}
