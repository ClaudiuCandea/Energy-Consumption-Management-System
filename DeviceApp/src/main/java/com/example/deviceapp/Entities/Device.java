package com.example.deviceapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String address;
    private double maxHourConsumtion;
    private Long userId;

    public Device( String description, String address, double maxHourConsumtion,Long userId ) {
        this.description = description;
        this.address = address;
        this.maxHourConsumtion = maxHourConsumtion;
        this.userId = userId;
    }

    public Device() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMaxHourConsumtion() {
        return maxHourConsumtion;
    }

    public void setMaxHourConsumtion(double maxHourConsumtion) {
        this.maxHourConsumtion = maxHourConsumtion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
