package com.example.chatapp.Entities;

public class TypingMessage {

    private boolean isTyping;
    private Long clientId;

    public TypingMessage(boolean isTyping, Long clientId) {
        this.isTyping = isTyping;
        this.clientId = clientId;
    }

    public boolean isTyping() {
        return isTyping;
    }

    public void setTyping(boolean typing) {
        isTyping = typing;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
