import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from 'src/Interfaces/Device';
import { DeviceAdd } from 'src/Interfaces/DeviceAdd';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  private apiUrl = 'http://localhost:8081/device'

  constructor(private http : HttpClient,private authService:AuthService) { }

  getAllDevices(): Observable<Device[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
     return this.http.get<Device[]>(`${this.apiUrl}/all`,{headers});
  }

  getDeviceById(id:number): Observable<Device>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.get<Device>(`${this.apiUrl}/getDevice?id=${id}`,{headers});
  }


  getDeviceByUserId(userId:number):Observable<Device[]>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.get<Device[]>(`${this.apiUrl}/getDeviceByUserId?id=${userId}`,{headers});
  }

  addDevice(device:DeviceAdd):Observable<Device>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.post<Device>(`${this.apiUrl}/save`,device,{headers});
  }

  updateDevice(device:DeviceAdd,id:number):Observable<Device>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.put<Device>(`${this.apiUrl}/update?id=${id}`,device,{headers});
  }

  deleteDevice(id:number):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
      return this.http.delete<any>(`${this.apiUrl}/delete?id=${id}`,{headers});
  }

  deleteDeviceByUserId(userId:number):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
    return this.http.delete<any>(`${this.apiUrl}/deleteByUser?userId=${userId}`,{headers});
  }
}
