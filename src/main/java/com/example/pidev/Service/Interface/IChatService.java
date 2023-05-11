package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Chat;

import java.util.List;

public interface IChatService {
    List<Chat> retrieveAllChats();

    Chat addChat(Chat a);

    Chat updateChat (Chat a);

    Chat retrieveChat (Long id);

    void deleteChat(Long id);
}
