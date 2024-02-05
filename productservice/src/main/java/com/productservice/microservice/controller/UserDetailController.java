package com.productservice.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.productservice.microservice.dto.AuthRequest;
import com.productservice.microservice.model.UserDetails;
import com.productservice.microservice.secureconfig.JwtService;
import com.productservice.microservice.service.UserServiveCon;

@RestController
@RequestMapping("v1/user")
public class UserDetailController {
	
	
	@Autowired
	UserServiveCon userDetailService;
	
	@Autowired
	JwtService jwtService;

	@PostMapping("/add-new-user")
	public ResponseEntity<String> addUserController(@RequestBody UserDetails userDetails)
	{
		userDetailService.addUser(userDetails);
		return ResponseEntity.ok("Item Added Successfully");
	}
	
	@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
