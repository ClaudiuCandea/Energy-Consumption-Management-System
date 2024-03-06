import { Component } from '@angular/core';
import {  Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service.service';


@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  
  constructor(private userService: UserService, private router:Router){

  }

  onSubmit(loginForm:any){
    console.log(loginForm.value);
    this.userService.login(loginForm.value);
  }

  navigateToRegister(){
    this.router.navigate(["register"]);
  }
}
