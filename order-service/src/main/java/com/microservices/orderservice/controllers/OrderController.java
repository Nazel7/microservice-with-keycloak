package com.microservices.orderservice.controllers;

import com.microservices.orderservice.dtos.OrderDto;
import com.microservices.orderservice.entities.Order;
import com.microservices.orderservice.pipelines.services.InventoryPipelineService;
import com.microservices.orderservice.repositories.OrderRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final OrderRepo orderRepo;
    private final InventoryPipelineService inventoryPipelineService;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;
    private final ExecutorService traceableExecutorService;

    @PostMapping(" ")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDto orderDto){
        // To enable distributed tracing to read log on the current-thread
        circuitBreakerFactory.configureExecutorService(traceableExecutorService);

        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory_circuitBreaker");
         Supplier<Boolean> circuitBreakerSupplier = () -> orderDto.getOrderLineItems().stream()
                .allMatch(orderLineItem -> inventoryPipelineService.isInStock(orderLineItem.getSkuOrder()));
        boolean isAllProductInSotck = circuitBreaker.run(circuitBreakerSupplier, throwable ->
                handleErrorCase());

        if (!isAllProductInSotck){
           throw new RuntimeException("Order failed, please try again");
        }

        Order order = new Order();
        order.setOrderLineItems(orderDto.getOrderLineItems());
        order.setOrderNumber(UUID.randomUUID().toString());
       final Order orderSaved = orderRepo.save(order);
       streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(orderSaved).build());

       return new ResponseEntity<>(orderSaved, HttpStatus.CREATED);

    }

    // not working as expected
    @CircuitBreaker(name = "inventory_circuitBreaker", fallbackMethod = "handleErrorCaseAnnotation")
    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrderAnnotationImpl(@RequestBody OrderDto orderDto){
        boolean isAllProductInSotck =  orderDto.getOrderLineItems().stream()
                .allMatch(orderLineItem -> inventoryPipelineService.isInStock(orderLineItem.getSkuOrder()));

        if (!isAllProductInSotck){
            throw new RuntimeException("Order failed, please try again");
        }

        Order order = new Order();
        order.setOrderLineItems(orderDto.getOrderLineItems());
        order.setOrderNumber(UUID.randomUUID().toString());
        final Order orderSaved = orderRepo.save(order);

        return new ResponseEntity<>(orderSaved, HttpStatus.CREATED);

    }

    private Boolean handleErrorCase() {

        return false;
    }

    private ResponseEntity<Exception> handleErrorCaseAnnotation(Exception ex) throws NotFoundException {

        throw new NotFoundException("Item not found please try again later");
    }
}
