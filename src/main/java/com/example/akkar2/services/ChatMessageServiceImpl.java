package com.example.akkar2.services;



        import com.example.akkar2.entities.ChatMessage;
        import com.example.akkar2.repository.ChatMessageRepository;
        import org.springframework.beans.factory.annotation.Autowired;

        import org.springframework.stereotype.Service;

        import java.util.List;



@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getChatHistory(Long senderId, Long recipientId) {
        return chatMessageRepository.findChatMessagesBySenderIdAndRecipientIdOrRecipientIdAndSenderIdOrderByCreatedAtAsc(senderId, recipientId, recipientId, senderId);
    }
}
