package com.microservices.notificationservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long id;
    private String orderNumber;
    private List<Object> orderLineItems;
}