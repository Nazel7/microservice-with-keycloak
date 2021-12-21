package com.microservices.productservice.controllers;

import com.microservices.productservice.entities.Product;
import com.microservices.productservice.repositories.IProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    @Value("${health.message}")
    private String healthMessage;

    private final IProductRepo repo;

    @GetMapping(" ")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProducts(){

        return repo.findAll();
    }

    @PostMapping(" ")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){

       return repo.save(product);
    }

    @GetMapping("/health")
    public String checkHealth(){

        return healthMessage;
    }
}
