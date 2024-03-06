import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user-service.service';


@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  constructor(private userService:UserService){

  }
  onSubmit(registerForm:any){
      console.log(registerForm.value);
      this.userService.register(registerForm.value);
  }
}
