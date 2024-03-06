import { Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  decodedToken!: { [key: string]: string };

  constructor() {
  }


  decodeToken(token:string|null) {
    if (token) {
    this.decodedToken = jwtDecode(token);
    console.log(this.decodedToken);
    }
  }

  getId(token:string|null) {
    this.decodeToken(token);
    return this.decodedToken['id'];
  }

  getUsername(token:string|null){
    this.decodeToken(token);
    return this.decodedToken['sub'];
  }


  getRole(token:string|null) {
    this.decodeToken(token);
    return this.decodedToken['role'];
  }


  getExpiryTime(token:string|null) {
    this.decodeToken(token);
    return this.decodedToken['exp']; 
  }

  isTokenExpired(token:string|null): boolean {
    const expiryTime: number =Number( this.getExpiryTime(token));
    if (expiryTime) {
      return ((1000 * expiryTime) - (new Date()).getTime()) < 5000;
    } else {
      return false;
    }
  }
}
