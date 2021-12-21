package com.microservice.inventoryservice.controllers;

import com.microservice.inventoryservice.entities.Inventory;
import com.microservice.inventoryservice.repositories.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryController {

    private final InventoryRepo inventoryRepo;

    @GetMapping("/{skuCode}")
    public boolean isInStock(@PathVariable("skuCode") String skuCode) {

        Inventory inventory = inventoryRepo.findInventoryBySkuCode(skuCode).orElseThrow(() ->
                new RuntimeException("Cannot find Product by skuCode: " + skuCode));

        return inventory.getStock() > 0;
    }
}
