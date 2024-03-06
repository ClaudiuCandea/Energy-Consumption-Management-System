import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Device } from 'src/Interfaces/Device';
import { DeviceService, } from 'src/app/services/device-service.service';
import { UpdateDeviceFormComponent } from '../update-device-form/update-device-form.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-device',
  templateUrl: './view-device.component.html',
  styleUrls: ['./view-device.component.css']
})
export class ViewDeviceComponent implements OnInit {

  devices = new MatTableDataSource<Device>();//Device[] = [
    //{id:1,description:'dadad',address:'aadsa',maxHourConsumtion:4,userId:2},
    //{id:2,description:'dadadaa',address:'aadsaaaa',maxHourConsumtion:10,userId:3}

 
  displayedColumns: String[] =['id','description','address','maxHourConsumption','userId','edit','delete','consumption'];


  constructor(private deviceService: DeviceService, public dialog:MatDialog,private router:Router){

  }

  ngOnInit():void{
    this.refresh();
  }

  refresh(){
    this.deviceService.getAllDevices().subscribe(data =>{
      this.devices.data = data;
    })
  }

 
  searchId:number = 0;

  removeDevice(device:Device){
    console.log(device);
    this.deviceService.deleteDevice(device.id).subscribe(data=>
      this.refresh());
  }

  editDevice(device:Device){
    const dialogRef = this.dialog.open(UpdateDeviceFormComponent,{
      data : {id: device.id,userId:device.userId,description:device.description,address:device.address,maxHourConsumtion:device.maxHourConsumtion},
    })

    dialogRef.afterClosed().subscribe(res => {
      this.refresh();
    });
  }

  viewConsumption(device:Device){
    this.router.navigate(["viewConsumption", device.id]);
  }

  searchById(){
    console.log(this.searchId);
    this.deviceService.getDeviceById(this.searchId).subscribe(data =>
      this.devices.data = new Array(data));
  }




}
