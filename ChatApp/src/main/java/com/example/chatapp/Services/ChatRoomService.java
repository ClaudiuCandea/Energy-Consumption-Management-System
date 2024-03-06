package com.example.chatapp.Services;

import com.example.chatapp.Entities.ChatRoom;
import com.example.chatapp.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ChatRoomService {

   private List<ChatRoom> rooms;
   private long messageId;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

   public ChatRoomService(SimpMessagingTemplate simpMessagingTemplate){
       this.rooms = new ArrayList<>();
       this.simpMessagingTemplate = simpMessagingTemplate;
   }

   public Message addChatRoom(Long clientId, Message message){
       ChatRoom room = new ChatRoom(clientId, message.getUsername());
       simpMessagingTemplate.convertAndSend("/topic/newRoom",room);
       this.rooms.add(room);
       return room.addMessage(message);
   }

   public List<ChatRoom> findAllChatRooms(){
       return this.rooms;
   }

   public ChatRoom findChatRoomByClientId(Long clientId){
       Optional<ChatRoom> findRoom = rooms.stream()
               .filter(room -> room.getClientId().equals(clientId)).findFirst();
       if(findRoom.isPresent()){
           return findRoom.get();
       }
       else{
           return null;
       }
   }
}
