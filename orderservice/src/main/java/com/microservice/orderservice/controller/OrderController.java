package com.microservice.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.orderservice.OrderserviceApplication;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

	
	private final OrderService orderService; 

	@PostMapping("/add")
	public ResponseEntity<String> placeOrder(OrderRequest orderRequest)
	{
		orderService.placeOrder(orderRequest);
		return new ResponseEntity<>("successfully added", HttpStatus.OK);
	}
	  
}

