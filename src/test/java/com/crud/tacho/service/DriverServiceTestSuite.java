package com.crud.tacho.service;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTestSuite {

    private static final Driver DRIVER = new Driver();
    private static final Long ID = 1L;

    @Mock
    DriverRepository driverRepository;

    @InjectMocks
    DriverService driverService;

    @Test
    void shouldCreateDriver() {

        //Given & When
        when(driverRepository.save(DRIVER)).thenReturn(DRIVER);
        Driver actualDriver = driverService.createDriver(DRIVER);

        //Then
        assertEquals(DRIVER, actualDriver);

    }

    @Test
    void shouldFindDriverById() {

        //Given & When
        when(driverRepository.findById(ID)).thenReturn(Optional.of(DRIVER));
        Driver actualDriver = driverService.getDriverById(ID);

        //Then
        assertEquals(DRIVER, actualDriver);

    }

    @Test
    void shouldFindAllDrivers() {

        //Given & When
        Set<Driver> drivers = Collections.singleton(DRIVER);
        when(driverRepository.findAll()).thenReturn(drivers);

        Set<Driver> actualDrivers = driverService.getDrivers();

        //Then
        assertEquals(drivers, actualDrivers);

    }

    @Test
    void shouldDeleteDriverById() {
        //Given & When
        doNothing().when(driverRepository).deleteById(ID);
        driverService.deleteDriver(ID);

        //Then
        verify(driverRepository,times(1)).deleteById(ID);
    }
}
