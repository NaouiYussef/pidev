package com.example.pidev.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private MessageType type;
    private String content;
    private String sender;
    private String t_stamp;
    private String receiver;
    @ManyToOne
    @JoinColumn(nullable=false,name="chat_id")
    private Chat chat;



    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }



    public ChatMessage() {
    }

    public ChatMessage( MessageType type, String content, String sender,Long id) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    public ChatMessage(MessageType type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;

    }

    public ChatMessage(Long id, MessageType type, String content, String sender, String receiver) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public ChatMessage( MessageType type, String content, String sender, String t_stamp, String receiver) {

        this.type = type;
        this.content = content;
        this.sender = sender;
        this.t_stamp = t_stamp;
        this.receiver = receiver;
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getT_stamp() {
        return t_stamp;
    }

    public void setT_stamp(String t_stamp) {
        this.t_stamp = t_stamp;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
