import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8083/message'

  constructor(private http : HttpClient) { }

  generateDeviceSensor(id:number): Observable<any>{
    console.log("aici in sezor")
     return this.http.get<any>(`${this.apiUrl}/send?deviceId=${id}`);
  }
}
