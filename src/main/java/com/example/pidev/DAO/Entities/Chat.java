package com.example.pidev.DAO.Entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CHATS")
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private long chat_id;

    @Column(name = "name")
    private String name;


    public Chat() {}

    public Chat(long chat_id, String name) {
        this.chat_id = chat_id;
        this.name = name;
    }

    public Chat(String name) {
        this.name = name;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
