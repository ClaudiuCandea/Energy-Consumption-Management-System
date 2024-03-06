import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{


  constructor(private router:Router,public authService:AuthService){
  }

  toUsers(){
    this.router.navigate(['/user/display'])
  }

  toDevices(){
    this.router.navigate(['/device/display'])
  }

  logOut(){
    this.authService.removeToken();
    this.router.navigate(['']);
  }

  createDevice(){
    this.router.navigate(['/device/create']);
  }

  toSensorGenerator(){
    this.router.navigate(['/sensorGenerator'])
  }

  goToChat(){
    if(this.authService.getRole()==="ADMIN"){
      this.router.navigate(['/adminChat']);
    }
    else{
      this.router.navigate(['/clientChat']);
    }
  }

  
}
