package com.lateu.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lateu.email.EmailSender;
import com.lateu.entity.ConfirmationToken;
import com.lateu.entity.User;
import com.lateu.repository.ConfirmationTokenRepository;
import com.lateu.repository.UserRepository;
import com.lateu.service.ConfirmationTokenService;
import com.lateu.service.EmailService;

@Controller
public class UserController {
@Autowired
private UserRepository userRepository;

@Autowired
private EmailSender emailSender;

@Autowired
private EmailService emailService;

@Autowired
private ConfirmationTokenService tokenService;

@Autowired
private ConfirmationTokenRepository confirmationTokenRepository;
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(path = "/")
	public String viewHomePage(){
		return "index";
	}
	
	@GetMapping(path = "/register")
	public String showSignUpForm(Model model){
		User user=new User();
		model.addAttribute(user);
		return "signUpForm";
	}
	
	@GetMapping(path = "/users")
	public String showListUsers(Model model){
		model.addAttribute("users",userRepository.findAll());
		return "users";
	}
	
	@PostMapping("/process_registration")
	public String doSaveUser(User user){
		String token=UUID.randomUUID().toString();
        String link = "http://localhost:8080/confirm?token=" + token;
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		User u=userRepository.save(user);
		
		
		
		ConfirmationToken cft=new ConfirmationToken();
		cft.setToken(token);
		cft.setCreateAt(LocalDateTime.now());
		cft.setExpireAt(LocalDateTime.now().plusHours(24));
		cft.setUser(u);
		confirmationTokenRepository.save(cft);
		emailSender.send(u.getEmail(), emailService.buildEmail(u.getFirstName(), link));
		return "savesuccess";
		
	}
	@GetMapping("/confirm")
	public String confirmToken(@RequestParam(name = "token") String token) {
		tokenService.confirmToken(token);
		return "savesuccess";
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

}
