package com.microservice.inventoryservice.repositories;

import com.microservice.inventoryservice.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findInventoryBySkuCode(String skuCode);
}
