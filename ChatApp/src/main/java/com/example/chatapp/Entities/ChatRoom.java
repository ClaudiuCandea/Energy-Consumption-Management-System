package com.example.chatapp.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatRoom {

    private Long clientId;
    private List<Message> messages;
    private long idCounter;
    private String username;


    public ChatRoom(Long clientId, String username){
        this.clientId = clientId;
        this.username = username;
        this.messages = new ArrayList<>();
        this.idCounter = 1;
    }

    public Message addMessage(Message message){
        message.setId(idCounter);
        idCounter++;
        messages.add(message);
        return message;
    }

    public Message findMessage(Long id){
        Optional<Message> foundMessage = messages.stream()
                .filter(message-> message.getId().equals(id)).findFirst();
        if(foundMessage.isPresent()){
            return foundMessage.get();
        }
        else{
            return null;
        }
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public long getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(long idCounter) {
        this.idCounter = idCounter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
