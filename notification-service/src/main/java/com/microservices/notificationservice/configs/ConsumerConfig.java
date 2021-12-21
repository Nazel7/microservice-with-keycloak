package com.microservices.notificationservice.configs;

import com.microservices.notificationservice.dtos.Order;
import com.microservices.notificationservice.services.EmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class ConsumerConfig {

    @Bean
    public Consumer<Message<Order>> notificationEventSupplier(){
        return orderPlacement -> new EmailSender().send(orderPlacement.getPayload());
    }
}
