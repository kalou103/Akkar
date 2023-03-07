package com.example.akkar2.repository;


import com.example.akkar2.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
   // @Query("SELECT cm FROM ChatMessage cm WHERE (cm.sender = :sender AND cm.recipient = :recipient) OR (cm.sender = :recipient AND cm.recipient = :sender) ORDER BY cm.timestamp ASC")
   // List<ChatMessage> findChatMessages(@Param("sender") String sender, @Param("recipient") String recipient);
    List<ChatMessage> findChatMessagesBySenderIdAndRecipientIdOrRecipientIdAndSenderIdOrderByCreatedAtAsc(Long senderId, Long recipientId, Long recipientId2, Long senderId2);
}
