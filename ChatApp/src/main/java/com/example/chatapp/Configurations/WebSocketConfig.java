package com.example.chatapp.Configurations;

import com.example.chatapp.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker


public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private JwtService jwtService;

    WebSocketConfig(JwtService jwtService){
        this.jwtService =jwtService;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry){
        messageBrokerRegistry.enableSimpleBroker("/topic");
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
    }


    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry){
        stompEndpointRegistry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200").withSockJS();
    }

}
