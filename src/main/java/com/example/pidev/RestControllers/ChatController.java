package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Chat;
import com.example.pidev.DAO.Entities.ChatMessage;
import com.example.pidev.DAO.Repositories.ChatMessageRepository;
import com.example.pidev.DAO.Repositories.ChatRepository;
import com.example.pidev.Service.Interface.IChatMessageService;
import com.example.pidev.Service.Interface.IChatService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Chat")
public class ChatController {
    private SimpMessagingTemplate simpMessagingTemplate;
    private IChatService iChatService;
    private IChatMessageService iChatMessageService;

    private ChatRepository chat;

    private ChatMessageRepository messaged;

    @GetMapping("/getChats")
    public List<Chat> getChats(){
        return iChatService.retrieveAllChats();
    }
    @PostMapping("/addChatMessage")
    public void ajoutChatMessage(@RequestBody ChatMessage chatMessage){
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"+chatMessage.getChat().getChat_id()+chatMessage.getChat().getName()+chatMessage.getContent()+chatMessage.getSender()+chatMessage.getId());
        messaged.save(chatMessage);
    }

    //returns an empty list if the chat doesn't exist
    @GetMapping("/getMessages")
    public List<ChatMessage> getMessage(@RequestBody String chatt) {
        Chat ce = chat.findByName(chatt);

        if(ce != null) {
            return messaged.findAllByChat(ce.getChat_id());
        }
        else{
            return new ArrayList<ChatMessage>();
        }
    }
    @GetMapping("/getAllMessages")
    public List<ChatMessage> getMessagee() {
        return messaged.findAll();
    }

    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way
    @GetMapping("/showAllChats/{name}")
    private Long createAndOrGetChat(@PathVariable String name) {

        Chat ce = chat.findByName(name);

        if (ce != null) {
            return ce.getChat_id();
        }
        else {
            Chat newChat = new Chat(name);
            return chat.save(newChat).getChat_id();
        }
    }


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setT_stamp(generateTimeStamp());
        Chat newC = new Chat(createAndOrGetChat("public"),"public");
        chatMessage.setChat(newC);
        chatMessage = messaged.save(chatMessage);
        return chatMessage;
    }
    /*    @MessageMapping("/chat.sendChatname")
        public void sendChatname(@Payload ChatMessage chatMessage) {
            simpMessagingTemplate.convertAndSend("/topic/public/" , chatMessage);
        }*/
    @MessageMapping("/chat.addUser")

    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getSender(),"/private",chatMessage);
        return chatMessage;
    }
    @MessageMapping("/private-message")
    public ChatMessage recMessage(@Payload ChatMessage message){
        simpMessagingTemplate.convertAndSendToUser(message.getSender(),"/private",message);
        Chat newC = new Chat(createAndOrGetChat(message.getSender()),message.getSender());
        chat.save(newC);
        message.setChat(newC);
        message.setT_stamp(generateTimeStamp());
        messaged.save(message);
        System.out.println(message.toString());
        return message;
    }
    private String generateTimeStamp() {
        Instant i = Instant.now();
        String date = i.toString();
        System.out.println("Source: " + i.toString());
        int endRange = date.indexOf('T');
        date = date.substring(0, endRange);
        date = date.replace('-', '/');
        System.out.println("Date extracted: " + date);
        String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
        time += ":";

        int minutes = i.atZone(ZoneOffset.UTC).getMinute();
        if (minutes > 9) {
            time += Integer.toString(minutes);
        } else {
            time += "0" + Integer.toString(minutes);
        }

        System.out.println("Time extracted: " + time);
        String timeStamp = date + "-" + time;
        return timeStamp;
    }
}
