import { Component } from '@angular/core';
import { DeviceAdd } from 'src/Interfaces/DeviceAdd';
import { User } from 'src/Interfaces/User';
import { DeviceService } from 'src/app/services/device-service.service';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-create-device',
  templateUrl: './create-device.component.html',
  styleUrls: ['./create-device.component.css']
})
export class CreateDeviceComponent {
  user!:User;
  showMessage:boolean=false;
  constructor(private deviceService:DeviceService,private userService:UserService){

  }

  onSubmit(createForm:any){
    this.showMessage = false;
    console.log(createForm.value);
    this.userService.getUserById(createForm.value.userId).subscribe(data =>{
      this.user=data;
      if(this.user !=null && this.user.id==createForm.value.userId){
        this.deviceService.addDevice(createForm.value).subscribe(data =>
          console.log(data));
      }
    },
    error =>{
      this.showMessage = true;
    })
    }
    
}
