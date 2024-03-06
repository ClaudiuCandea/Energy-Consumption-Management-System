import { Component, OnInit } from '@angular/core';
import  Stomp from 'stompjs';
import  SockJS from 'sockjs-client';
import { ChatMessage } from 'src/Interfaces/ChatMessage';
import { AuthService } from 'src/app/services/auth.service';
import { ChatMessageService } from 'src/app/services/chat-message.service';
import { ChatRoom } from 'src/Interfaces/ChatRoom';
import { TypingMessage } from 'src/Interfaces/TypingMessage';

@Component({
  selector: 'app-client-chat',
  templateUrl: './client-chat.component.html',
  styleUrls: ['./client-chat.component.css']
})
export class ClientChatComponent implements OnInit {
  messages:ChatMessage[] = [];
  url = 'http://localhost:8084/chat';
  client: any;
  newMessage = '';
  isTyping:boolean=false;
  typingTimeout:any;

  constructor(private authService:AuthService,private chatMessageService:ChatMessageService){
    
  }

  currentId():number{
    return this.authService.getId();
  }

  ngOnInit(): void {
    let chatRoom:ChatRoom;
    this.chatMessageService.getMessagesForClient(this.currentId()).subscribe(data =>{
      chatRoom = data;
      console.log(chatRoom.messages);
      this.messages = chatRoom.messages;
    })
    this.connection();
  }


  setSeenMessage(mess:ChatMessage){
    if(mess.clientId===this.messages[0].clientId){
      this.messages.forEach(message => {
        if (message.id === 0) {
          console.log("aici in seen");
          message.seen = true;
          console.log(message);
        }
      });
    }
  }


  connection(){
    let ws =  new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that: any = this;
	
    this.client.connect({}, function(frame: any) {
      that.client.subscribe("/topic/client", (message: any) => {
       const mess = JSON.parse(message.body);
       var id=that.authService.getId();
       console.log(mess)
      console.log(id);
       if(id===mess.clientId){
        console.log("in if");
        that.messages.push(mess);
       }
      });
      that.client.subscribe("/topic/typing/admin",(message: any) => {
        const mess = JSON.parse(message.body);
        console.log(mess)
        if(mess.clientId===that.messages[0].clientId){
          console.log("in if");
          that.isTyping=mess.typing;
        }
       });
       that.client.subscribe("/topic/seen/client",(message: any) => {
        const mess = JSON.parse(message.body);
        console.log(mess)
        that.setSeenMessage(mess);
        });
    });
  }

  onTyping(){
    clearTimeout(this.typingTimeout);

    this.typingTimeout = setTimeout(() => {
      let mess:TypingMessage = {isTyping:false,clientId:this.messages[0].clientId}
      this.client.send('/app/typing/client',{},JSON.stringify(mess));
    }, 3000);


    let mess:TypingMessage = {isTyping:true,clientId:this.messages[0].clientId}
    this.client.send('/app/typing/client',{},JSON.stringify(mess));

  }


  sendMessage():void{
    if (this.newMessage.trim() !== ''){
      console.log(this.newMessage);
      let mess:ChatMessage={id:0,content:this.newMessage,clientId:this.currentId(),sender:'CLIENT',seen:false,username:this.authService.getUsername()};
      this.messages.push({id:0,content:this.newMessage,clientId:this.currentId(),sender:'CLIENT',seen:false,username:this.authService.getUsername()})
      this.client.send('/app/admin',{},JSON.stringify(mess));
      this.newMessage='';
    }
  }
}
