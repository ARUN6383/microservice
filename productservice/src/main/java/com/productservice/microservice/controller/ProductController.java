package com.productservice.microservice.controller;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
 
import com.productservice.microservice.dto.ProductRequest;
import com.productservice.microservice.dto.ProductResponse;
import com.productservice.microservice.model.UserDetails;
import com.productservice.microservice.service.ProductService;
import com.productservice.microservice.service.UserServiveCon;

import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
 
    private final ProductService productService;
 
    @PostMapping("/add")
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
	UserServiveCon userDetailService = new UserServiveCon()	;
//
//	@PostMapping("/add-new-user")
//	public ResponseEntity<String> addUserController(@RequestBody UserDetails userDetails)
//	{
//		userDetailService.addUser(userDetails);
//		return ResponseEntity.ok("Item Added Successfully");
//	}
    @GetMapping("/getreq")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/say-good")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getSayGoodBye() {
		return ResponseEntity.ok("hello all");
    	
    }
    
    @GetMapping("/say-nice")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getSayGoodnice() {
		return ResponseEntity.ok("say nice to meet you");
    	
    }
}