package com.example.akkar2.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "chat_message")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "message_content")
    private String content;

    @Column(name = "message_status")
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

 /*_   public String getRecipient() {
        return null;
    }

    public Long getRecipientId() {
        return recipientId;
    }*/
}
