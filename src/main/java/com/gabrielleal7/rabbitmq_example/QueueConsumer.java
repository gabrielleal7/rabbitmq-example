package com.gabrielleal7.rabbitmq_example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload Message message)  {
        System.out.println("Message " + message + "  " + LocalDateTime.now());

        if (message.getPayload() instanceof String) {
            System.out.println(" Mesage received - " + message.getPayload());
        }

        String ultima = String.valueOf(message.getHeaders().get("test"));
        if(ultima.equals("test")) {
            System.out.println(ultima);
        }
    }

}
