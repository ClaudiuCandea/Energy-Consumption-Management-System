package com.example.monitoringapp.Entites;

public class Message {
    private long userId;
    private String messageToSent;

    public Message(long userId, String messageToSent) {
        this.userId = userId;
        this.messageToSent = messageToSent;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessageToSent() {
        return messageToSent;
    }

    public void setMessageToSent(String messageToSent) {
        this.messageToSent = messageToSent;
    }
}
