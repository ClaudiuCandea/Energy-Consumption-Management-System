package com.example.monitoringapp.Controllers;

import com.example.monitoringapp.Entites.Measurement;
import com.example.monitoringapp.Services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "/measurement")
@CrossOrigin(origins = "*")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService){
        this.measurementService = measurementService;
    }

    @GetMapping("/getMeasurementsByDeviceId")
    public ResponseEntity<List<Measurement>> getMeasurementByDeviceId(@RequestParam(value = "deviceId")Long deviceId){
        return ResponseEntity.ok(this.measurementService.findMeasurementsByDeviceId(deviceId));
    }
}
