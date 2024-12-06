package com.gabrielleal7.rabbitmq_example.web;

import com.gabrielleal7.rabbitmq_example.QueueSender;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/send-message")
public class Controller {

    private final AmqpTemplate queueSender;

    private final QueueSender sender;

    @GetMapping("/with-header")
    public String sendWithHeader(){
        String mensagem = "message send";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("test", "test");
        Message message = new Message(mensagem.getBytes(), messageProperties);
        queueSender.convertAndSend("exchange-teste", "routing-key-teste", message);
        return "ok";
    }

    @GetMapping
    public String send(){
        sender.send("test message");
        return "ok. done";
    }

}
