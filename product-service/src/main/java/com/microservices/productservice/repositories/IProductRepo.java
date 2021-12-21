package com.microservices.productservice.repositories;

import com.microservices.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepo extends MongoRepository<Product, String> {


}
