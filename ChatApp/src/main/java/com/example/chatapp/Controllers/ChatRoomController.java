package com.example.chatapp.Controllers;

import com.example.chatapp.Entities.ChatRoom;
import com.example.chatapp.Entities.Message;
import com.example.chatapp.Entities.TypingMessage;
import com.example.chatapp.Services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/message")
@CrossOrigin(origins = "*")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    ChatRoomController(ChatRoomService chatRoomService){
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/admin")
    @SendTo("/topic/admin")
    public Message sendToAdmin(@Payload Message message){
        ChatRoom chatRoom = this.chatRoomService.findChatRoomByClientId(message.getClientId());
        Message returnedMessage;
        if(chatRoom==null){
            returnedMessage=this.chatRoomService.addChatRoom(message.getClientId(),message);
        }
        else{
            returnedMessage=chatRoom.addMessage(message);
        }
        return returnedMessage;
    }

    @MessageMapping("typing/client")
    @SendTo("/topic/typing/client")
    public TypingMessage sendToAdminTypingMessage(@Payload TypingMessage typingMessage){
        return typingMessage;
    }

    @MessageMapping("typing/admin")
    @SendTo("/topic/typing/admin")
    public TypingMessage sendToClientTypingMessage(@Payload TypingMessage typingMessage){
        return typingMessage;
    }

    @MessageMapping("/client")
    @SendTo("/topic/client")
    public Message sendToSpecificUser(@Payload Message message){
        ChatRoom chatRoom = this.chatRoomService.findChatRoomByClientId(message.getClientId());
        return chatRoom.addMessage(message);
    }

    @MessageMapping("/seen/client")
    @SendTo("/topic/seen/admin")
    public Message sendSeenMessageToAdmin(@Payload Message message){
        ChatRoom chatRoom = this.chatRoomService.findChatRoomByClientId(message.getClientId());
        Message foundMessage = chatRoom.findMessage(message.getId());
        foundMessage.setSeen(true);
        return foundMessage;
    }

    @MessageMapping("/seen/admin")
    @SendTo("/topic/seen/client")
    public Message sendSeenMessageToClient(@Payload Message message){
        ChatRoom chatRoom = this.chatRoomService.findChatRoomByClientId(message.getClientId());
        Message foundMessage = chatRoom.findMessage(message.getId());
        foundMessage.setSeen(true);
        return foundMessage;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ChatRoom>> getAllRooms(){
        return ResponseEntity.ok(this.chatRoomService.findAllChatRooms());
    }

    @GetMapping("/messageForClient")
    public ResponseEntity<ChatRoom> getMessageForClient(@RequestParam(value = "clientId") Long clientId){
        return ResponseEntity.ok(this.chatRoomService.findChatRoomByClientId(clientId));
    }

}
