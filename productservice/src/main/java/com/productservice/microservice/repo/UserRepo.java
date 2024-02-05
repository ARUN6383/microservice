package com.productservice.microservice.repo;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productservice.microservice.model.UserDetails;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long> {
	
	Optional<UserDetails> findByName(String userName); 

}
