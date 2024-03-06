package com.example.chatapp.Entities;

public class Message {
    private Long id;
    private String content;
    private Long clientId;
    private String sender;
    private boolean seen;
    private String username;


    public Message(String content, Long clientId, String sender, boolean seen, String username) {
        this.content = content;
        this.clientId = clientId;
        this.sender = sender;
        this.seen = seen;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
