package com.productservice.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.microservice.model.UserDetails;
import com.productservice.microservice.service.UserServiveCon;

@RestController
@RequestMapping("v1/user")
public class UserDetailController {
	
	
	UserServiveCon userDetailService = new UserServiveCon()	;

	@PostMapping("/add-new-user")
	public ResponseEntity<String> addUserController(@RequestBody UserDetails userDetails)
	{
		userDetailService.addUser(userDetails);
		return ResponseEntity.ok("Item Added Successfully");
	}
}
