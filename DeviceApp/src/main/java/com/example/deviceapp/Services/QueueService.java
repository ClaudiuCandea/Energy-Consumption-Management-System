package com.example.deviceapp.Services;

import com.example.deviceapp.Entities.Device;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    RabbitTemplate rabbitTemplate;


    public void sentToQueue(Device device){
        String deviceString = "{\n\"deviceId\": " + device.getId() + ",\n"
                                + "\"description\": \"" + device.getDescription() + "\",\n"
                                + "\"address\": \"" + device.getAddress() + "\",\n"
                                + "\"maxHourConsumption\": " + device.getMaxHourConsumtion() + ",\n"
                                + "\"userId\": " + device.getUserId() + "\n}";
        rabbitTemplate.convertAndSend("Device_queue",deviceString);
        System.out.println(" [x] Sent '" + deviceString + "'");
    }

    public void sentToQueueDelete(Long id){
        String message = "deleteID:"+id;
        rabbitTemplate.convertAndSend("Device_queue",message);
    }
}
