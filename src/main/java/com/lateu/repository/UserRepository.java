package com.lateu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lateu.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT  u FROM User u WHERE u.email=?1")
    User  findUserByEmail(String email);
	
	

}
