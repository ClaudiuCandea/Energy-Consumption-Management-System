package com.example.deviceapp.Repository;

import com.example.deviceapp.Entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    Device findDeviceById(Long id);

    List<Device> findDeviceByUserId(long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Device WHERE userId=?1")
    void deleteDeviceByUserId(long userId);
}
