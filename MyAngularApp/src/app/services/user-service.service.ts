import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/Interfaces/User';
import { UserLogin } from 'src/Interfaces/UserLogin';
import { UserRegister } from 'src/Interfaces/UserRegister';
import { UserUpdate } from 'src/Interfaces/UserUpdate';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/user';
  constructor( private http:HttpClient,private authService:AuthService,private router:Router) { }



  getAllUsers(): Observable<any>{
    var token = this.authService.getToken();
    console.log(token);
    var headers = new HttpHeaders({
      Authorization: 'Bearer ' + token?.toString()
    });
    console.log(headers);
    console.log(this.authService.getToken());
    return this.http.get<any>(`${this.apiUrl}/all`,{headers});
 }

 getUserById(id:number): Observable<User>{
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.authService.getToken()}`
  });
   return this.http.get<User>(`${this.apiUrl}/getUser?id=${id}`,{headers});
 }

 register(user:UserRegister):void{
   this.http.post<any>(`${this.apiUrl}/register`,user).subscribe(res => {
    const token = res.token;
    this.authService.setToken(token);
    this.router.navigate(["/homeClient"]);
 } );
 }

 login(user:UserLogin):void{
     this.http.post<any>(`${this.apiUrl}/login`,user).subscribe(res => {
        const token = res.token;
        this.authService.setToken(token);
        console.log('in login');
        if(this.authService.getRole()==="ADMIN"){
          this.router.navigate(["/homeAdmin"]);
        }
        else{
          this.router.navigate(["/homeClient"]);
        }
     },
     error =>{
      if (error.status === 403) {
        // Just using alert for simplicity's sake. 
        alert("Invalid email or password");
      }
     });
 }

 updateUser(user:UserUpdate,id:number):Observable<any>{
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.authService.getToken()}`
  })
   return this.http.put<any>(`${this.apiUrl}/update?id=${id}`,user,{headers});
 }

 deleteUser(id:number):Observable<any>{
  const headers = new HttpHeaders({
    'Authorization': `Bearer ${this.authService.getToken()}`
  });
     return this.http.delete<any>(`${this.apiUrl}/delete?id=${id}`,{headers});
 }
}
