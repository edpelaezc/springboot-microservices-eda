package com.edpelaezc.orderservice.publisher;

import com.edpelaezc.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Value("${rabbitmq.order.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.email.routing.key}")
    private String emailRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private RabbitTemplate template;

    public OrderProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(OrderEvent orderEvent){

        // send to order queue
        template.convertAndSend(exchange, routingKey, orderEvent);

        // send to email queue
        template.convertAndSend(exchange, emailRoutingKey, orderEvent);

        LOGGER.info(String.format("[ORDER SENT TO RABBITMQ]     %s", orderEvent.toString()));
    }
}
