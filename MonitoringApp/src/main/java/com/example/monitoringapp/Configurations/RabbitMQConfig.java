package com.example.monitoringapp.Configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue(){
        return new Queue("SD_Project");
    }

    @Bean
    public Queue queueDevice(){
        return new Queue("Device_queue");
    }
}
