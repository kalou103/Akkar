package com.example.akkar2.controllers;

import com.example.akkar2.Model.MessageModel;
import com.example.akkar2.entities.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {
    Expert expert = new Expert();
    @Autowired
            private SimpMessagingTemplate simpMessagingTemplate; //permet denvoyer des messages over websocket
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {//extract the value of the {to} path variable and pass it as an argument to the sendMessage method.
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists =expert.getFirstname().contains(to);//checks if the recipient exists
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}
