package com.productservice.microservice.repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.productservice.microservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
}
