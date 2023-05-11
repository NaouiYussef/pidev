package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    ChatMessage findChatMessageBySender(String Sender);
    List<ChatMessage> findAllByChat(Long id);
}