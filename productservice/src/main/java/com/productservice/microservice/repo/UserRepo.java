package com.productservice.microservice.repo;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productservice.microservice.model.UserDetails;

public interface UserRepo extends JpaRepository<UserDetails, Long> {
	
	Optional<UserDetails> findByName(String userName); 

}
