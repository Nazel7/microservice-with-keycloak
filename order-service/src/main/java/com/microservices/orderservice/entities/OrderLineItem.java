package com.microservices.orderservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
@Data
@Access(AccessType.FIELD)
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuOrder;
    private BigDecimal price;
    private Integer quantity;
}
