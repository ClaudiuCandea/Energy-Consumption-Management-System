import { Component, OnInit } from '@angular/core';
import  Stomp from 'stompjs';
import  SockJS from 'sockjs-client';
import { ChatMessage } from 'src/Interfaces/ChatMessage';
import { AuthService } from 'src/app/services/auth.service';
import { ChatMessageService } from 'src/app/services/chat-message.service';
import { ChatRoom } from 'src/Interfaces/ChatRoom';
import { TypingMessage } from 'src/Interfaces/TypingMessage';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-admin-chat',
  templateUrl: './admin-chat.component.html',
  styleUrls: ['./admin-chat.component.css']
})
export class AdminChatComponent implements OnInit{
  messages:ChatMessage[] = [];
  chatRooms:ChatRoom[]=[];
  currentRoom:ChatRoom={clientId: 0, messages: [], username: ''};
  url = 'http://localhost:8084/chat';
  client: any;
  newMessage = '';
  isTyping:boolean=false;
  typingTimeout:any;
  displayMess:boolean=false;
  

  constructor(private authService:AuthService,private chatMessageService:ChatMessageService){
    
  }



  currentId():number{
    return this.authService.getId();
  }

  ngOnInit(): void {
    this.chatMessageService.getAllRooms().subscribe(data =>{
      this.chatRooms = data;
    })
    console.log(this.isTyping);
    this.connection();
  }

  setSeenMessage(mess:ChatMessage){
    const chatRoom:ChatRoom = this.chatRooms.find(room => room.clientId === mess.clientId)!;
    chatRoom.messages.forEach(message => {
      if (message.id === 0) {
        message.seen = true;
      }
    });
  }

  setChatRoomMessage(mess:ChatMessage){
    const chatRoom:ChatRoom = this.chatRooms.find(room => room.clientId === mess.clientId)!;
    chatRoom.messages.push(mess);
  }


  connection(){
    let ws =  new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that: any = this;
    this.client.connect({}, function(frame: any) {
      that.client.subscribe("/topic/admin", (message: any) => {
       const mess = JSON.parse(message.body);
       console.log(mess)
       that.setChatRoomMessage(mess);
      });
      that.client.subscribe("/topic/typing/client",(message: any) => {
        const mess = JSON.parse(message.body);
        console.log(mess)
        if(mess.clientId===that.currentRoom.clientId){
          that.isTyping=mess.typing;
        }
       });
       that.client.subscribe("/topic/seen/admin",(message: any) => {
        const mess = JSON.parse(message.body);
        console.log(mess)
        that.setSeenMessage(mess);
        });
        that.client.subscribe("/topic/newRoom",(message: any) => {
          const mess = JSON.parse(message.body);
          console.log(mess);
          that.chatRooms.push(mess);
          })
    });
  }

  onTyping(){
    clearTimeout(this.typingTimeout);

    this.typingTimeout = setTimeout(() => {
      let mess:TypingMessage = {isTyping:false,clientId:this.messages[0].clientId}
      this.client.send('/app/typing/admin',{},JSON.stringify(mess));
    }, 3000);


    let mess:TypingMessage = {isTyping:true,clientId:this.messages[0].clientId}
    this.client.send('/app/typing/admin',{},JSON.stringify(mess));
  }


  sendMessage():void{
    if (this.newMessage.trim() !== ''){
      console.log(this.newMessage);
      let mess:ChatMessage={id:0,content:this.newMessage,clientId:this.messages[0].clientId,sender:'ADMIN',seen:false,username:this.messages[0].username};
      this.messages.push({id:0,content:this.newMessage,clientId:this.messages[0].clientId,sender:'ADMIN',seen:false,username:this.messages[0].username})
      this.client.send('/app/client',{},JSON.stringify(mess));
      this.newMessage='';
    }
  }

  setMessages(room:ChatRoom){
    console.log("in set message")
    this.displayMess=true;
    this.currentRoom=room;
    this.messages = room.messages;
  }
}
