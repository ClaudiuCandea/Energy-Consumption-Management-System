package com.example.sensorapp.Services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ProducerService {
    private final static String QUEUE_NAME = "SD_Project";
    private ConnectionFactory factory;

    public ProducerService(){
        factory = new ConnectionFactory();

    }

    public void readCSV(Long deviceId){
        boolean flag = false;
        long addedMillis = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("sensor.csv").getInputStream()));
            factory.setHost("rabbitmq");
            String line;
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            while((line = br.readLine()) != null){
                System.out.println(line);
                String message = createMessage(line,deviceId,addedMillis,flag);
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                addedMillis+=1;
                Thread.sleep(5000);
                flag = true;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createMessage(String line,Long deviceId,Long addedMillis,boolean flag){
        Long millis = System.currentTimeMillis();
        Long finalMillis;
        if(flag){
             finalMillis = millis + addedMillis*1000*60*10;
        }
        else{
             finalMillis = millis;
        }
        String message = "{\n\"timestamp\": " + finalMillis + ",\n" +
                "\"device_id\": " + deviceId +",\n"+
                "\"measurement_value\": " + line + "\n}";
        return message;
    }

}
