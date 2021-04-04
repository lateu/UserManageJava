package com.lateu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lateu.entity.User;
import com.lateu.repository.UserRepository;

@Controller
public class UserController {
@Autowired
private UserRepository userRepository;
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
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		User u=userRepository.save(user);
		
		return "savesuccess";
		
	}

}
