package com.microservices.notificationservice.services;

import com.microservices.notificationservice.dtos.Order;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    public void send(Order orederId){
        System.out.println("Preparing to place order");
        System.out.println("OderId received is: "+ orederId);
        System.out.println("Your order has been placed successful");
    }
}
