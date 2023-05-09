package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.ChatMessage;

import java.util.List;

public interface IChatMessageService {
    List<ChatMessage> retrieveAllChatMessages();

    ChatMessage addChatMessage(ChatMessage a);

    ChatMessage updateChatMessage (ChatMessage a);

    ChatMessage retrieveChatMessage (Long id);

    void deleteChatMessage( Long id);
}
