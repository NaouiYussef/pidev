package com.example.pidev.Service.Classe;
import com.example.pidev.DAO.Entities.ChatMessage;
import com.example.pidev.DAO.Repositories.ChatMessageRepository;
import com.example.pidev.Service.Interface.IChatMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChatMessageService implements IChatMessageService {

    ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessage> retrieveAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    @Override
    public ChatMessage addChatMessage(ChatMessage a) {
        chatMessageRepository.save(a);
        return a;
    }

    @Override
    public ChatMessage updateChatMessage(ChatMessage a) {
        chatMessageRepository.save(a);
        return a;
    }

    @Override
    public ChatMessage retrieveChatMessage(Long id) {
        return chatMessageRepository.findById(id).get();

    }

    @Override
    public void deleteChatMessage(Long id) {
        chatMessageRepository.deleteById(id);

    }

}
