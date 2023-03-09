package com.example.akkar2.controllers;


import com.example.akkar2.entities.ChatMessage;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.ChatMessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chat")
public class ChatController {
@Autowired
    UserRepository userRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

    //@MessageMapping("/chat.sendMessage")
    @PostMapping("/chat/sendMessage")
    @ResponseBody
    public void sendMessage(@RequestBody ChatMessage chatMessage) {
        User user=userRepository.findUserById((long) Math.toIntExact(chatMessage.getRecipientId()));

        chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(user.getEmail(), "/queue/messages", chatMessage);
    }

    @MessageMapping("/chat.getChatHistory")
    @GetMapping("/chat/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable("senderId") Long senderId, @PathVariable("recipientId") Long recipientId) {
        List<ChatMessage> messages = chatMessageService.getChatHistory(senderId, recipientId);
        return ResponseEntity.ok(messages);
    }

}
