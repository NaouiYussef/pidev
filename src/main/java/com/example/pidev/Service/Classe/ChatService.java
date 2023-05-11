package com.example.pidev.Service.Classe;
import com.example.pidev.DAO.Entities.Chat;
import com.example.pidev.DAO.Repositories.ChatRepository;
import com.example.pidev.Service.Interface.IChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class ChatService implements IChatService {
    ChatRepository chatRepository;

    @Override
    public List<Chat> retrieveAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat addChat(Chat a) {
        chatRepository.save(a);
        return a;
    }

    @Override
    public Chat updateChat(Chat a) {
        chatRepository.save(a);
        return a;
    }

    @Override
    public Chat retrieveChat(Long id) {
        return chatRepository.findById(id).get();

    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);

    }

}
