package com.crud.tacho.service;

import com.crud.tacho.controller.DriverController;
import com.crud.tacho.domain.Driver;
import com.crud.tacho.domain.DriverDto;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.mapper.DriverMapper;
import com.crud.tacho.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DriverService {

    //private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver getDriverById(Long id) throws DriverNotFoundException {
        return driverRepository.findById(id).orElseThrow(DriverNotFoundException::new);
    }

    public Set<Driver> getDrivers() {
        return driverRepository.findAll();
    }
}
