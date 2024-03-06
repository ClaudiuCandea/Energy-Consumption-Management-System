import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/Interfaces/User';
import { UserUpdate } from 'src/Interfaces/UserUpdate';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-update-user-form',
  templateUrl: './update-user-form.component.html',
  styleUrls: ['./update-user-form.component.css']
})
export class UpdateUserFormComponent{
  user:UserUpdate = {name:"ilie",role:"ADMIN",password:"123",email:'aur'};

  constructor(private userService:UserService,
    public dialogRef: MatDialogRef<UpdateUserFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {id : number,role:string,email:string,password:string,name:string},
  ){

  }

  onSubmit(updateForm:any){
    console.log(updateForm.value);
    console.log(this.data.id);
    this.user = updateForm.value;
    this.user.role = this.data.role;
    console.log(this.user);
      this.userService.updateUser(this.user,this.data.id).subscribe(res =>{
        console.log(res);
        this.dialogRef.close();
      })
  }
}
