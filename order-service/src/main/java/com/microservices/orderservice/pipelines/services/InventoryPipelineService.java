package com.microservices.orderservice.pipelines.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryPipelineService {

    @GetMapping("/api/inventories/{skuCode}")
    boolean isInStock(@PathVariable("skuCode") String skuCode);
}
