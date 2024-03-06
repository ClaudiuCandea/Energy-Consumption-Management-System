import { Component, OnInit } from '@angular/core';
import { Measurement } from 'src/Interfaces/Measurement';
import { MeasurementService } from 'src/app/services/measurement.service';
import Chart from 'chart.js/auto'
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-consumption',
  templateUrl: './view-consumption.component.html',
  styleUrls: ['./view-consumption.component.css']
})
export class ViewConsumptionComponent implements OnInit {

    selectedDate: Date = new Date();
    measurements: Measurement[] = [] ;
    filtredMeasurements: Measurement[] = [];
    deviceId:number=0;
    public chart?: Chart;
    constructor(private measurementService:MeasurementService,private route:ActivatedRoute){

    }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.deviceId = +params['numberData']; // Retrieve the number from route parameters
    });
    console.log(this.route.snapshot?.data['numberData']);
    console.log(this.deviceId);
    this.measurementService.generateDeviceSensor(this.deviceId).subscribe(data =>{
      this.measurements=data.map(item =>{
        return {
          ...item,
          timestamp: new Date(item.timestamp)
        }
      });
      console.log(this.measurements)});
  }

    displayChart(){
      if(this.chart){
        this.chart.destroy();
      }
      console.log(this.selectedDate);
      this.filtredMeasurements = this.measurements?.filter(obj =>{
          return obj.timestamp.getFullYear() === this.selectedDate.getFullYear() &&
            obj.timestamp.getMonth() === this.selectedDate.getMonth() &&
            obj.timestamp.getDate() === this.selectedDate.getDate();
        })
      console.log(this.filtredMeasurements);
      this.createChart();
    }

    createChart(){
      const labels: any[] = this.filtredMeasurements.map(item =>{
        return item.timestamp.getHours();
      });
      const dataset: any[] = this.filtredMeasurements.map(item =>{
        return item.measurementValue;
      })
      this.chart = new Chart("MyChart", {
        type: 'bar', //this denotes tha type of chart
  
        data: {// values on X-Axis
          labels: labels , 
           datasets: [
            {
              label: "Consumption",
              data: dataset,
              backgroundColor: 'blue'
            },
          ]
        },
        options: {
          aspectRatio:2.5,
          scales: {
            y: {
              title: {
                display: true,
                text: 'kWh'
              }
            },
            x:{
              title: {
                display: true,
                text: 'Hour'
              }
            }
          }   
        }
        
      });
    }
}
