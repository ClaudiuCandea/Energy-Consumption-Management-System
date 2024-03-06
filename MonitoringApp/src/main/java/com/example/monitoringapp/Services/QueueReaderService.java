package com.example.monitoringapp.Services;

import com.example.monitoringapp.Entites.Device;
import com.example.monitoringapp.Entites.Measurement;
import com.example.monitoringapp.Entites.Message;
import com.example.monitoringapp.Repositories.DeviceRepository;
import com.example.monitoringapp.Repositories.MeasurementRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueueReaderService {

    @Autowired
    private DeviceRepository deviceRepository;

    private Map<Long, List<Measurement>> lastHourValues;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private WebSocketService webSocketService;

    QueueReaderService(DeviceRepository deviceRepository,MeasurementRepository measurementRepository,WebSocketService webSocketService){
        this.deviceRepository=deviceRepository;
        this.lastHourValues = new HashMap<>();
        this.measurementRepository= measurementRepository;
        this.webSocketService = webSocketService;
    }


    @RabbitListener(queues = "SD_Project")
    public void consume(String message){
        Measurement measurement = parseMessage(message);
        System.out.println(measurement);
        if(lastHourValues.containsKey(measurement.getDeviceId())){
            List<Measurement> list =lastHourValues.get(measurement.getDeviceId());
            if(list.size()==5){
                list.add(measurement);
                double sum = 0.0;
                for(Measurement m:  list){
                    sum+=m.getMeasurementValue();
                }
                Timestamp time = list.get(0).getTimestamp();
                Measurement computedVal = new Measurement(time,measurement.getDeviceId(),sum);
                measurementRepository.save(computedVal);
                Device device = deviceRepository.findDeviceById(measurement.getDeviceId());
                System.out.println(device.toString());
                if(device.getMaxHourConsumtion()<computedVal.getMeasurementValue()){
                    Message messageForSocket = new Message(device.getUserId(),"Device " + measurement.getDeviceId() + " consumption is higher that max hour consumption");
                        System.out.println(messageForSocket);
                        webSocketService.sendMessage(messageForSocket);
                }
                System.out.println("\nTotal"+sum);
                list.clear();
            }
            else{
                list.add(measurement);
            }
        }
        else{
            List<Measurement> list = new ArrayList<>();
            list.add(measurement);
            lastHourValues.put(measurement.getDeviceId(),list);
        }

    }

    public Measurement parseMessage(String message){
        String[] lines = message.split("\n");
        lines[1]=lines[1].replaceAll(",","");
        String[] timestampStrings = lines[1].split(": ");
        long milis = Long.parseLong(timestampStrings[1]);
        Timestamp timestamp = new Timestamp(milis);
        lines[2]=lines[2].replaceAll(",","");
        String[] deviceIdStrings = lines[2].split(": ");
        Long deviceId = Long.parseLong(deviceIdStrings[1]);
        String[] valueStrings = lines[3].split(": ");
        double value = Double.parseDouble(valueStrings[1]);
        Measurement measurement = new Measurement(timestamp,deviceId,value);
        return measurement;
    }



    @RabbitListener(queues = "Device_queue")
    public void consumeDevice(String deviceJson){
        System.out.println(deviceJson);
        if(deviceJson.contains("deleteID")){
            String[] substrs = deviceJson.split(":");
            Long id = Long.parseLong(substrs[1]);
            deviceRepository.deleteById(id);
            return;

        }
        String[] lines = deviceJson.split("\n");
        Device device = new Device();
        String[] deviceId = lines[1].split(": ");
        deviceId[1] = deviceId[1].replaceAll(",","");
        long id = Long.parseLong(deviceId[1]);
        device.setId(id);
        String[] description = lines[2].split(": ");
        String desc = description[1].replaceAll("\"|,","");
        device.setDescription(desc);
        String[] address = lines[3].split(": ");
        String addr = address[1].replaceAll("\"|,","");
        device.setAddress(addr);
        String[] maxHourConsumption = lines[4].split(": ");
        maxHourConsumption[1]=maxHourConsumption[1].replaceAll(",","");
        double mhc = Double.parseDouble(maxHourConsumption[1]);
        String[] userId = lines[5].split(": ");
        userId[1] = userId[1].replaceAll(",","");
        long uid = Integer.parseInt(userId[1]);
        device.setMaxHourConsumtion(mhc);
        device.setUserId(uid);
        System.out.println(device);
        deviceRepository.save(device);
    }
}
