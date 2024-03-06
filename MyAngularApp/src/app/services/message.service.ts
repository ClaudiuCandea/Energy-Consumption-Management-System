import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8083/message'

  constructor(private http : HttpClient,private authService:AuthService) { }

  generateDeviceSensor(id:number): Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    console.log("aici in sezor")
     return this.http.get<any>(`${this.apiUrl}/send?deviceId=${id}`,{headers});
  }
}
