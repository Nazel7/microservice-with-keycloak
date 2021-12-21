package com.microservice.inventoryservice.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventory")
@Access(AccessType.FIELD)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer stock;

}
