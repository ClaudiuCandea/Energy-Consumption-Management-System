import { Injectable } from '@angular/core';
import { JwtService } from './jwt.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'auth_token'; // Key for storing the token in localStorage
  private roleKey = 'user_role';
  private idKey = 'user_id';
  private usernameKey = 'username';

  constructor(private jwtService:JwtService) { }

  getToken(): string|null{
    return sessionStorage.getItem(this.tokenKey);
  }

  setToken(token: string): void {
    sessionStorage.setItem(this.tokenKey, token);
    var role = this.jwtService.getRole(token);
    sessionStorage.setItem(this.roleKey,role);
    var id = this.jwtService.getId(token);
    sessionStorage.setItem(this.idKey,id);
    var username = this.jwtService.getUsername(token);
    sessionStorage.setItem(this.usernameKey,username);

  }

  getId():number{
    var id = sessionStorage.getItem(this.idKey);
    if(id!=null){
      return +id;
    }
    else{
      return 0;
    }
  }

  getRole():string|null{
    return sessionStorage.getItem(this.roleKey);
  }

  getUsername():string{
    var username = sessionStorage.getItem(this.usernameKey);
    if(username!=null){
      return username;
    }
    else return "";
  }

  removeToken(): void {
    sessionStorage.removeItem(this.tokenKey);
    sessionStorage.removeItem(this.idKey);
    sessionStorage.removeItem(this.roleKey);
    sessionStorage.removeItem(this.usernameKey);
  }

  isAuthenticated(): boolean{
    if(this.getToken()==null){
      return false;
    }
    else{
      return !this.jwtService.isTokenExpired(this.getToken());
    }
  }
}
