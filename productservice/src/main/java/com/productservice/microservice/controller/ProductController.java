package com.productservice.microservice.controller;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
 
import com.productservice.microservice.dto.ProductRequest;
import com.productservice.microservice.dto.ProductResponse;
import com.productservice.microservice.service.ProductService;
 
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
 
    @GetMapping("/getreq")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/say-good")
    public ResponseEntity<String> getSayGoodBye() {
		return ResponseEntity.ok("hello all");
    	
    }
}