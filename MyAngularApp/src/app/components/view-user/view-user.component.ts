import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/Interfaces/User';
import { UserService } from 'src/app/services/user-service.service';
import { UpdateUserFormComponent } from '../update-user-form/update-user-form.component';
import { DeviceService } from 'src/app/services/device-service.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  users = new MatTableDataSource<User>()
 /* users:User[]=[
    {id:1,email:'clau',password:'adasdas',name:'Claudiu',role:'admin'},
    {id:12,email:'clauaasdasd',password:'adasdas',name:'Claudiu Candea',role:'client'}
  ];
  */

  displayedColumns: String[] =['id','name','email','role','edit','delete'];

  searchId:number = 0;

  constructor(private userService: UserService, public dialog:MatDialog,private deviceService:DeviceService){

  }

  ngOnInit():void{
    this.refresh();
  }

  refresh(){
    this.userService.getAllUsers().subscribe(data =>{
      console.log("suntem aici");
      console.log(data);
      this.users.data = data;
    })
  }


  remove(user:User){
    console.log(user);
    this.userService.deleteUser(user.id).subscribe(data=>{
      this.deviceService.deleteDeviceByUserId(user.id).subscribe();
      this.refresh();
    });
  }

  editUser(user:User){
    console.log(user);
    const dialogRef = this.dialog.open(UpdateUserFormComponent,{
      data : {id: user.id,
              role: user.role,
              email: user.email,
              password:user.password,
              name:user.name},
    })

    dialogRef.afterClosed().subscribe(res => {
      this.refresh();
    });
  }

  searchById(){
    console.log(this.searchId);
    this.userService.getUserById(this.searchId).subscribe(data =>
      this.users.data = new Array(data));
  }
}
