package com.edpelaezc.emailservice.consumer;

import com.edpelaezc.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.email.queue.name}"})
    public void consume(OrderEvent event){
        LOGGER.info(String.format("[ORDER RETRIEVED FROM RABBITMQ FOR EMAIL]     %s", event.toString()));

        // save order event data in db

    }
}
