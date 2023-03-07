package com.example.akkar2.services;


import com.example.akkar2.entities.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    void save(ChatMessage chatMessage);
    List<ChatMessage> getChatHistory (Long senderId, Long recipientId);
}
