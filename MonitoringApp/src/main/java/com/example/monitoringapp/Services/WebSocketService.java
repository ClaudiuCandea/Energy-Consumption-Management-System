package com.example.monitoringapp.Services;

import com.example.monitoringapp.Entites.Measurement;
import com.example.monitoringapp.Entites.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(Message message){
        simpMessagingTemplate.convertAndSend("/topic",
                message);
    }
}
