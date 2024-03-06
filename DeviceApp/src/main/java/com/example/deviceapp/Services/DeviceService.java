package com.example.deviceapp.Services;

import com.example.deviceapp.Entities.Builder;
import com.example.deviceapp.Entities.Device;
import com.example.deviceapp.Entities.DeviceDTO;
import com.example.deviceapp.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAllDevices(){
        return this.deviceRepository.findAll();
    }

    public Device findDeviceById(Long id){
        return this.deviceRepository.findDeviceById(id);
    }

    public Device addDevice(DeviceDTO deviceDTO){
        Device device = Builder.toDevice(deviceDTO);
        return this.deviceRepository.save(device);
    }

    public Device updateDevice(DeviceDTO deviceDTO,Long id){
        Device device = this.deviceRepository.findDeviceById(id);
        System.out.println(device);
        device.setAddress(deviceDTO.getAddress());
        device.setDescription(deviceDTO.getDescription());
        device.setMaxHourConsumtion(deviceDTO.getMaxHourConsumtion());
        device.setUserId(deviceDTO.getUserId());
        return this.deviceRepository.save(device);
    }

    public List<Device> findDeviceByUserId(Long userId){
        return this.deviceRepository.findDeviceByUserId(userId);
    }

    public void deleteDevice(Long id){
        this.deviceRepository.deleteById(id);
    }

    public void deleteByUserId(Long userId){
        this.deviceRepository.deleteDeviceByUserId(userId);
    }
}
