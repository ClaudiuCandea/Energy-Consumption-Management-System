package com.example.deviceapp.Entities;

public class DeviceDTO {
    private String description;
    private String address;
    private double maxHourConsumtion;
    private long userId;

    public DeviceDTO(String description, String address, double maxHourConsumtion, long userId) {
        this.description = description;
        this.address = address;
        this.maxHourConsumtion = maxHourConsumtion;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
