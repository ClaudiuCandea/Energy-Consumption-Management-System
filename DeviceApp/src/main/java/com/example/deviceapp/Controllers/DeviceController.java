package com.example.deviceapp.Controllers;


import com.example.deviceapp.Entities.Device;
import com.example.deviceapp.Entities.DeviceDTO;
import com.example.deviceapp.Services.DeviceService;
import com.example.deviceapp.Services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/device")
@CrossOrigin(origins = "http://localhost:4200/")
public class DeviceController {

    @Autowired
    DeviceService deviceService;
    @Autowired
    QueueService queueService;

    public DeviceController(DeviceService deviceService, QueueService queueService) {
        this.deviceService = deviceService;
        this.queueService = queueService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Device>> getDevices(){
        return ResponseEntity.ok(this.deviceService.findAllDevices());
    }

    @GetMapping("/getDevice")
    public ResponseEntity<Device> getDeviceById(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(this.deviceService.findDeviceById(id));
    }

    @GetMapping("/getDeviceByUserId")
    public ResponseEntity<List<Device>> getDeviceByUserId(@RequestParam(value="id") Long userId){
        return ResponseEntity.ok(this.deviceService.findDeviceByUserId(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<Device> saveDevice(@RequestBody DeviceDTO deviceDTO){
        Device device  = this.deviceService.addDevice(deviceDTO);
        queueService.sentToQueue(device);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/update")
    public ResponseEntity<Device> updateDevice(@RequestBody DeviceDTO deviceDTO,@RequestParam(value = "id")Long id){
        Device device = this.deviceService.updateDevice(deviceDTO,id);
        queueService.sentToQueue(device);
        return ResponseEntity.ok(device);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteDevice(@RequestParam(value = "id") Long id){
        this.deviceService.deleteDevice(id);
        this.queueService.sentToQueueDelete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteByUser")
    public ResponseEntity deleteByUserId(@RequestParam(value = "userId") Long userId){
        this.deviceService.deleteByUserId(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
