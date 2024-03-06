package com.example.monitoringapp.Repositories;

import com.example.monitoringapp.Entites.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {

    public Device findDeviceById(Long id);
}
