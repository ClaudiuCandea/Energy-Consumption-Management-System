import { Component, OnDestroy } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Device } from 'src/Interfaces/Device';
import { DeviceService } from 'src/app/services/device-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-client',
  templateUrl: './home-client.component.html',
  styleUrls: ['./home-client.component.css']
})
export class HomeClientComponent {
  url = 'http://localhost:8082/socket'
  client: any;
  devices = new MatTableDataSource<Device>();
  clientId:number = 0;
  displayedColumns: String[] =['deviceId','description','address','maxHourConsumption','consumption'];
  constructor(private deviceService: DeviceService, private router:Router){
  }

  ngOnInit():void{
    this.refresh();
  }



  refresh(){
    var id = sessionStorage.getItem('user_id');
     var idNumber:number;
    if(id!=null){
      idNumber=Number(id);
      this.deviceService.getDeviceByUserId(idNumber).subscribe(data =>{
        this.devices.data = data;
      })
    }
  }

  viewConsumption(device:Device){
    this.router.navigate(["viewConsumption", device.id]);
  }
}
