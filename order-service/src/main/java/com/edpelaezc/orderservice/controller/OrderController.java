package com.edpelaezc.orderservice.controller;

import com.edpelaezc.orderservice.dto.Order;
import com.edpelaezc.orderservice.dto.OrderEvent;
import com.edpelaezc.orderservice.publisher.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        producer.sendMessage(new OrderEvent("PENDING", "Order is in pending status", order));
        return ResponseEntity.ok("Order sent to RabbitMQ");
    }
}
