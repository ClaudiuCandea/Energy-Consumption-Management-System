import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Measurement } from 'src/Interfaces/Measurement';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {

  private apiUrl = 'http://localhost:8082/measurement'

  constructor(private http : HttpClient, private authService:AuthService) { }

  generateDeviceSensor(id:number): Observable<Measurement[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    console.log("aici in sezor")
     return this.http.get<Measurement[]>(`${this.apiUrl}/getMeasurementsByDeviceId?deviceId=${id}`,{headers});
  }
}
