import { Component, OnInit } from '@angular/core';
import  Stomp from 'stompjs';
import  SockJS from 'sockjs-client';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  url = 'http://localhost:8082/socket'
  client: any;
  clientId:number = 0;
  title = 'MyAngularApp';

  constructor(private authService:AuthService){
    
  }
  ngOnInit(): void {
    this.connection();
  }


  connection(){
    let ws =  new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that: any = this;
	
    this.client.connect({
      Authorization: 'Bearer ' + this.authService.getToken()
      }, function(frame: any) {
      that.client.subscribe("/topic", (message: any) => {
       const mess = JSON.parse(message.body);
       that.clientId=that.authService.getId();
       console.log(mess)
       console.log(that.clientId)
       if(that.clientId==mess.userId){
        alert(mess.messageToSent);
       }
      });
    });
  }
}
