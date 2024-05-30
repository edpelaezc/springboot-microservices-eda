package com.edpelaezc.stockservice.consumer;

import com.edpelaezc.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.order.queue.name}"})
    public void consume(OrderEvent event){
        LOGGER.info(String.format("[ORDER RETRIEVED FROM RABBITMQ]     %s", event.toString()));

        // save order event data in db

    }
}
