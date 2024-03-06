import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatRoom } from 'src/Interfaces/ChatRoom';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ChatMessageService {

  private apiUrl = 'http://localhost:8084/message'

  constructor(private http : HttpClient,private authService:AuthService) { }

  getAllRooms(): Observable<ChatRoom[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
     return this.http.get<ChatRoom[]>(`${this.apiUrl}/all`,{headers});
  }

  getMessagesForClient(id:number): Observable<ChatRoom>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.get<ChatRoom>(`${this.apiUrl}/messageForClient?clientId=${id}`,{headers});
  }
}
