package com.example.monitoringapp.Services;

import com.example.monitoringapp.Entites.Measurement;
import com.example.monitoringapp.Repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository){
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findMeasurementsByDeviceId(Long deviceId){
        return measurementRepository.findAllByDeviceId(deviceId);
    }


}
