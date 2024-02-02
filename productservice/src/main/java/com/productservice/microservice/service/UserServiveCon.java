package com.productservice.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.productservice.microservice.model.UserDetails;
import com.productservice.microservice.repo.UserRepo;

public class UserServiveCon {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String addUser(UserDetails userDetails)
	{
		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		userRepo.save(userDetails);
		return "User add successfully";
	}
}
