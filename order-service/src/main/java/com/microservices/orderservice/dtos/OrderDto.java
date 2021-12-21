package com.microservices.orderservice.dtos;

import com.microservices.orderservice.entities.OrderLineItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

   private List<OrderLineItem> orderLineItems;
}
