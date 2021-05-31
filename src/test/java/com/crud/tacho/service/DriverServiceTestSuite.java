package com.crud.tacho.service;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.exception.DriverNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTestSuite {

    @Autowired
    DriverService driverService;

    @Test
    void shouldCreateDriver() {

        //Given
        Driver driver = new Driver();

        //When
        Driver savedDriver = driverService.createDriver(driver);
        Long driverId = savedDriver.getDriverId();

        //Then
        assertEquals(1, driverService.getDrivers().size());

        //CleanUp
        driverService.deleteDriver(driverId);

    }

    @Test
    void shouldFindDriverById() throws DriverNotFoundException {

        //Given
        Driver driver = new Driver();

        //When
        Driver savedDriver = driverService.createDriver(driver);
        Long driverId = savedDriver.getDriverId();

        Driver retrievedDriver = driverService.getDriverById(driverId);

        //Then
        assertEquals(savedDriver.getDriverId(), retrievedDriver.getDriverId());

        //CleanUp
        driverService.deleteDriver(driverId);

    }

    @Test
    void shouldFindAllDrivers() {
        //Given
        Driver driver = new Driver();

        //When
        Driver savedDriver = driverService.createDriver(driver);
        Long driverId = savedDriver.getDriverId();

        int drivers = driverService.getDrivers().size();

        //Then
        assertEquals(1, drivers);

        //CleanUp
        driverService.deleteDriver(driverId);

    }




}
