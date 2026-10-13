package com.logistics.logistics.service;

import com.logistics.logistics.entity.Driver;
import com.logistics.logistics.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));
    }

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Long id, Driver driver) {

        Driver existing = getDriverById(id);

        existing.setName(driver.getName());
        existing.setPhone(driver.getPhone());
        existing.setEmail(driver.getEmail());
        existing.setLicenseNumber(driver.getLicenseNumber());
        existing.setStatus(driver.getStatus());

        return driverRepository.save(existing);
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}