import { Component, Input, OnInit } from '@angular/core';
import { ChatMessage } from 'src/Interfaces/ChatMessage';
import  Stomp from 'stompjs';
import  SockJS from 'sockjs-client';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-received-message',
  templateUrl: './received-message.component.html',
  styleUrls: ['./received-message.component.css']
})
export class ReceivedMessageComponent implements OnInit{
  @Input() message: ChatMessage = {
    id: 0,
    content: '',
    clientId: 0,
    sender: '',
    seen: false,
    username: ''
  };
  
  url = 'http://localhost:8084/chat';
  client: any;

  constructor(private authService:AuthService){

  }


  ngOnInit(): void {
    let ws =  new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that = this;
    this.client.connect({}, function(frame: any) {
      if(that.authService.getRole()==="ADMIN"){
        that.message.seen=true;
        that.client.send('/app/seen/admin',{},JSON.stringify(that.message));
        console.log(that.message);
      }
      else{
        that.message.seen=true;
        console.log(that.message);
        that.client.send('/app/seen/client',{},JSON.stringify(that.message));
      }
    });
  }


}
