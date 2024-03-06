package com.example.monitoringapp.Repositories;

import com.example.monitoringapp.Entites.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Long> {

    public List<Measurement> findAllByDeviceId(Long deviceId);
}
