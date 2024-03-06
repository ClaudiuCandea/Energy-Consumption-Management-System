import { Component, OnInit } from '@angular/core';
import { Device } from 'src/Interfaces/Device';
import { DeviceService } from 'src/app/services/device-service.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-sensor-generator',
  templateUrl: './sensor-generator.component.html',
  styleUrls: ['./sensor-generator.component.css']
})
export class SensorGeneratorComponent implements OnInit {

    devices: Device[] = [ {id:1,description:'dadad',address:'aadsa',maxHourConsumtion:4,userId:2}];
    constructor(private deviceService:DeviceService, private messageService:MessageService){

    }
  ngOnInit(): void {
    this.deviceService.getAllDevices().subscribe(data =>{
      this.devices = data;
    })
  }

  onSubmit(generatorForm:any){
    console.log(generatorForm.value.deviceId);
    this.messageService.generateDeviceSensor(generatorForm.value.deviceId).subscribe();
  }

    
}
