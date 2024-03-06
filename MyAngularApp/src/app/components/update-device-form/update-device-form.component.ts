import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/Interfaces/User';
import { DeviceService } from 'src/app/services/device-service.service';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-update-device-form',
  templateUrl: './update-device-form.component.html',
  styleUrls: ['./update-device-form.component.css']
})
export class UpdateDeviceFormComponent{
  user!:User;
  showMessage:boolean=false;
  constructor(private userService:UserService ,private deviceService:DeviceService,
    public dialogRef: MatDialogRef<UpdateDeviceFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {id : number,userId:number,description:string,address:string,maxHourConsumtion:number},
  ){

  }

  onSubmit(createForm:any){
    this.showMessage=false;
    console.log(createForm.value);
    console.log(this.data.id);
    this.userService.getUserById(createForm.value.userId).subscribe(data =>{
        this.user=data;
        if(this.user!=null){
          this.deviceService.updateDevice(createForm.value,this.data.id).subscribe(res =>{
            console.log(res);
            this.dialogRef.close();
          })
        }
    },
    error =>{
      this.showMessage=true;
    });
    
  }
}
