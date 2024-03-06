package com.example.deviceapp.Entities;

public class Builder {

    public static Device toDevice(DeviceDTO deviceDTO){
        return new Device(deviceDTO.getDescription(),deviceDTO.getAddress(),deviceDTO.getMaxHourConsumtion(),deviceDTO.getUserId());
    }

}
