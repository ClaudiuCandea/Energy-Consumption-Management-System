package com.example.sensorapp.Controllers;

import com.example.sensorapp.Services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( path = "/message")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private ProducerService producerService;

    public MessageController(ProducerService producerService){
        this.producerService = producerService;
    }

    @GetMapping("/send")
    public void sendMessage(@RequestParam(value = "deviceId") Long deviceId){
        System.out.println("aici");

        producerService.readCSV(deviceId);
    }


}
